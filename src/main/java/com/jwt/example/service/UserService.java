package com.jwt.example.service;


import com.jwt.example.entity.Audit;
import com.jwt.example.entity.Product;
import com.jwt.example.entity.User;
import com.jwt.example.model.ProductDTO;
import com.jwt.example.model.UserDTO;
import com.jwt.example.repository.AuditRepository;
import com.jwt.example.repository.ProductRepository;
import com.jwt.example.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ExecutorService virtualThreadExecutor;

    public static final String USER_NAME = "userName";

    public UserDTO save(UserDTO userDTO) {
        User user = convertToDTO(userDTO);
        return save(user);
    }

    public void saveORUpdate(UserDTO userDTO, UserDTO userDTOExists) {
        if (Objects.isNull(userDTOExists)) {
            save(userDTO);
        } else {
            update(userDTO);
        }
    }

    public UserDTO update(UserDTO userDTO) {
        User user = convertToDTO(userDTO);
        return updateUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    private UserDTO updateUser(User user) {

        UserDTO userDTO = modelMapper.map(userRepository.updateDate(user.getUsername()), UserDTO.class);
        Audit audit = Audit.builder().userName(user.getUsername())
                .functionName("update").build();

        saveAudit(audit);
        return userDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    private UserDTO save(User user) {
        UserDTO userDTO = modelMapper.map(userRepository.save(user), UserDTO.class);
        Audit audit = Audit.builder().userName(userDTO.getUsername())
                 .functionName("save").build();
        saveAudit(audit);
        return userDTO;
    }

    @Cacheable(value = "userCache", key = "#username")
    public UserDTO findByUsername(String username) {
        log.info("retreive the data from DB {}", username);
        User user = userRepository.findByUsername(username);
        if (null != user) {
            return modelMapper.map(user, UserDTO.class);
        }
        return null;
    }

    /**
     * propogation levels
     * 1.Required >> join existing transactions or create new one if not exits
     * 2.Required_new >> Always create new transactions
     * 3.Mandatory >> Require existing transaction Else it will throw exception
     * 4.Never >> Ensure method will not run with transaction, if it founds throws exception
     * 5.Not supported >> Execute without transactions. if found it wil suspend the transactions
     * 6.supports >> supports if any active transactions. if not execute without transaction
     * 7.Nester >> execute within nested transactions.allowing nested transactions to rollback with out affectingxc
     * @param productDTO
     * @return
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public ProductDTO addProduct(ProductDTO productDTO) {
        log.info("Inside add product method {}", productDTO);

        Product product = modelMapper.map(productDTO, Product.class);
        Optional<Product> productOptional = productRepository.findById(productDTO.getId());

        if (productOptional.isPresent()) {
            Product productDB = productOptional.get();
            Audit audit = Audit.builder().userName(httpServletRequest.getHeader(USER_NAME))
                    .functionName("Stock already exists")
                    .build();
            productDB.setQuantity(product.getQuantity()+1);
            productRepository.save(productDB);
            saveAudit(audit);
        } else {
            log.info("Persist product in DB {}", productDTO);
            product.setId(null);
            productRepository.save(product);
            Audit audit = Audit.builder().userName(httpServletRequest.getHeader(USER_NAME))
                    .functionName("Stock add")
                    .build();
            saveAudit(audit);
        }
        return productDTO;
    }

    @Transactional(propagation = Propagation.NESTED)
    private Audit saveAudit(Audit audit) {
        //throw new RuntimeException("Exception");
        return auditRepository.save(audit);
    }

    public UserDTO findByUsernameUsingVirtualThread(String username) {
        try {
            Future<UserDTO> future = virtualThreadExecutor.submit(() -> {
                log.info("Retrieve the data from DB for {}", username);
                User user = userRepository.findByUsername(username);
                if (user != null) {
                    return modelMapper.map(user, UserDTO.class);
                }
                return null;
            });

            return future.get(); // Blocking call

        } catch (InterruptedException | ExecutionException e) {
            // Log and handle exception properly
            log.error("Error retrieving user by username", e);
            Thread.currentThread().interrupt(); // Reset interrupt status
            return null;
        }
    }

    /**
     * Check the first level cache
     *
     * @param userID
     * @return
     */

    @Transactional
    public UserDTO findByUserID(String userID) {
        log.info("retreive the data from DB first time {}", userID);
        Optional<User> user = userRepository.findById(UUID.fromString(userID));
        log.info("second time retreival the data from DB {}", userID);
        Optional<User> userSecond = userRepository.findById(UUID.fromString(userID));
        //check first level cache
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDTO.class);
        }
        return null;
    }

    public User convertToDTO(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }




}
