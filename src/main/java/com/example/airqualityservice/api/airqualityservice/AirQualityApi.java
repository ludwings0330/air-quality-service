package com.example.airqualityservice.api.airqualityservice;

import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.City;
import org.springframework.cache.annotation.Cacheable;

public interface AirQualityApi {
    @Cacheable(value = "cache::AirQualityInfo", key = "#city")
    AirQualityResDto getAirQuality(City city);
    City getCity();
}