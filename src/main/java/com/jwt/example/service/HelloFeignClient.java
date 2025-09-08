package com.jwt.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:9090")
public interface HelloFeignClient {
    @GetMapping("/hello")
    ResponseEntity<String> sayHello();
}
