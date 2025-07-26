package com.jwt.example.service;


import com.jwt.example.entity.User;
import com.jwt.example.model.UserDTO;
import com.jwt.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (null != user) {
            return modelMapper.map(user, UserDTO.class);
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
