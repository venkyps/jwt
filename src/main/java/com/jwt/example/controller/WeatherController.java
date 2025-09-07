package com.jwt.example.controller;

import com.jwt.example.model.WeatherResponse;
import com.jwt.example.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Weather report API")
@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/getWeatherReport")
    @Operation(summary = "retrieve weather report", description = "retrieve weather report")
    public ResponseEntity<WeatherResponse> getWeatherReport(@RequestParam("lat") String lat,
                                                            @RequestParam("lon") String lon) throws  Exception{
        WeatherResponse weatherResponse = weatherService.getWeatherByLatAndLon(lat,lon);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherResponse);
    }
}
