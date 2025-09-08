package com.jwt.example.controller;

import com.jwt.example.service.HelloFeignClient;
import com.jwt.example.util.CompletableFutureUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello", description = "Hello World")
public class HelloWorldController {

    @Autowired
    CompletableFutureUtility completableFutureUtility;

    @Value("${message}")
    private String value;

    @Autowired
    private HelloFeignClient helloFeignClient;

    @GetMapping("/helloWorld")
    @Operation(summary = "hello world", description = "hello world")
    public String helloWorld() {
        completableFutureUtility.log(value);
        return helloFeignClient.sayHello().getBody();
    }
}
