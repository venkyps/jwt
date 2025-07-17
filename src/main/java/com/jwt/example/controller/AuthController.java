package com.jwt.example.controller;


import com.jwt.example.model.UserDTO;
import com.jwt.example.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Date API", description = "Auth login")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "login", description = "login using username and password")
    public String login(@RequestBody UserDTO user) {
        if ("user".equals(user.getUsername()) && "pass".equals(user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
