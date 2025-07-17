package com.jwt.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello", description = "Hello World")
public class HelloWorldController {

    @GetMapping("/helloWorld")
    @Operation(summary = "hello world", description = "hello world")
    public String helloWorld() {
        return "Hello World";
    }
}
