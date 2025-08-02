package com.jwt.example.controller;


import com.jwt.example.model.UserDTO;
import com.jwt.example.service.UserService;
import com.jwt.example.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Date API", description = "Auth login")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "login", description = "login using username and password")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        if ("user".equals(userDTO.getUsername()) && "pass".equals(userDTO.getPassword())) {
            UserDTO userDTOExists = userService.findByUsername(userDTO.getUsername());
            userService.saveORUpdate(userDTO, userDTOExists);
            return ResponseEntity.ok(jwtUtil.generateToken(userDTO.getUsername()));
        }
        throw new RuntimeException("Invalid credentials");
    }

    @PostMapping("/findByUserName")
    @Operation(summary = "findByUserName", description = "find by user name using virtual thread implementation")
    public ResponseEntity<UserDTO> findByUserName(@RequestParam String userName) {
        UserDTO userDTO = userService.findByUsernameUsingVirtualThread(userName);
        return ResponseEntity.ok(userDTO);
    }


    @GetMapping("/getUser")
    @Operation(summary = "getUser", description = "retrieves user details")
    public ResponseEntity<UserDTO> getUser(@RequestParam String userName) {
        UserDTO userDTO = userService.findByUsername(userName);
        return ResponseEntity.ok(userService.findByUsername(userName));
    }

    @GetMapping("/getUserById")
    @Operation(summary = "getUserById", description = "retrieves user details by userID")
    public ResponseEntity<UserDTO> getUserById(@RequestParam String userID) {
        return ResponseEntity.ok(userService.findByUserID(userID));
    }
}
