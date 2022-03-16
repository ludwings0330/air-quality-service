package com.example.airqualityservice.api.airqualityservice;

import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.City;
import org.springframework.cache.annotation.Cacheable;

public interface AirQualityApi {
    AirQualityResDto getAirQuality();

    @Cacheable(value = "cache::AirQualityInfo", key = "#city")
    AirQualityResDto getCachedAirQuality(City city);

    City getCity();
}