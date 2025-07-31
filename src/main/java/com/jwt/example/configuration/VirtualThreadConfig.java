package com.jwt.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class VirtualThreadConfig {

    @Bean
    public ExecutorService virtualThreadExecutor(){
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
