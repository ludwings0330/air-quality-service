package com.example.airqualityservice.api.airqualityservice;

import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.City;

public interface AirQualityApi {
    AirQualityResDto getAirQuality();
    City getCity();
}