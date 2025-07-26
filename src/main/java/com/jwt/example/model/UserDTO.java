package com.jwt.example.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String username;
    private String password;
    private LocalDateTime createdtime;
    private LocalDateTime updatedtime;

}
