package com.example.airqualityservice.service;

import com.example.airqualityservice.api.airqualityservice.AirQualityApiFactory;
import com.example.airqualityservice.api.airqualityservice.AirQualityApi;
import com.example.airqualityservice.controller.dto.AirQualityReqDto;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Service
@Slf4j
public class AirQualityService {
    private final AirQualityApiFactory airQualityApiFactory;
    public AirQualityResDto getAirQualityInfo(AirQualityReqDto reqDto) {
        City reqCity = reqDto.getCity();
        String reqDistrict = reqDto.getDistrict();

        AirQualityApi airQualityApi = airQualityApiFactory.getAirQualityApi(reqCity);

        AirQualityResDto airQuality = airQualityApi.getCachedAirQuality(reqCity);

        if (reqDistrict != null) {
            return airQuality.searchByDistrict(reqDistrict);
        }

        return airQuality;
    }

    public AirQualityResDto getCachedAirQualityInfo(AirQualityApi airQualityApi) {
        return airQualityApi.getAirQuality();
    }
}
