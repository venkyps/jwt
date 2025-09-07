package com.jwt.example.service;


import com.jwt.example.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherByLatAndLon(String lat,String lon) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric") // Celsius
                .toUriString();

        log.info("value of the request URL {} ",url);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}

