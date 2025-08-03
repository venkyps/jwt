package com.jwt.example.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        if(ex.getMessage().equals("User not available")){
            return new ResponseEntity<>("Internal server error: " + ex.getMessage(),
                    HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<>("Internal server error: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
