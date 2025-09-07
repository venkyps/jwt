package com.jwt.example.model;

import lombok.Data;

import java.util.Map;

@Data
public class WeatherResponse {

    private Map<String, Object> main;
    private Map<String, Object> wind;
    private Object[] weather;
    private String name;
}
