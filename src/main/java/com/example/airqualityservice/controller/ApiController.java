package com.example.airqualityservice.controller;

import com.example.airqualityservice.domain.dto.AirQualityReqDto;
import com.example.airqualityservice.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final AirQualityService airQualityService;

    @GetMapping("/v1/api/air-qualities")
    public String getAirQuality(@ModelAttribute AirQualityReqDto reqDto) {

        return "ok";
    }
}

