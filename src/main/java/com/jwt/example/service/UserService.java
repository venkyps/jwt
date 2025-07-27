package com.jwt.example.service;


import com.jwt.example.entity.User;
import com.jwt.example.model.UserDTO;
import com.jwt.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO save(UserDTO userDTO) {
        User user = convertToDTO(userDTO);
        return save(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User user = convertToDTO(userDTO);
        return updateUser(user);
    }

    private UserDTO updateUser(User user) {
        return modelMapper.map(userRepository.updateDate(user.getUsername()), UserDTO.class);
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

    private UserDTO save(User user) {
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }


}
