package com.example.airqualityservice.service;

import com.example.airqualityservice.api.airqualityservice.AirQualityApiFactory;
import com.example.airqualityservice.api.airqualityservice.AirQualityApi;
import com.example.airqualityservice.controller.dto.AirQualityReqDto;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AirQualityService {
    private final AirQualityApiFactory airQualityApiFactory;
    public AirQualityResDto getAirQualityInfo(AirQualityReqDto reqDto) {
        City reqCity = reqDto.getCity();
        String reqDistrict = reqDto.getDistrict();

        AirQualityApi airQualityApi = airQualityApiFactory.getAirQualityApi(reqCity);

        AirQualityResDto airQuality = airQualityApi.getAirQuality(reqCity);

        if (reqDistrict != null) {
            return airQuality.searchByDistrict(reqDistrict);
        }

        return airQuality;
    }
}
