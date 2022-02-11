package com.example.airqualityservice.controller;

import com.example.airqualityservice.controller.dto.AirQualityReqDto;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final AirQualityService airQualityService;

    @GetMapping("/v1/api/air-qualities")
    public AirQualityResDto getAirQuality(@RequestParam String city, @RequestParam(required = false) String district) {
        return airQualityService.getAirQuality(new AirQualityReqDto(city, district));
    }
}

