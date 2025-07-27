package com.jwt.example.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private String username;
    private String password;
    private LocalDateTime createdtime;
    private LocalDateTime updatedtime;

}
