package com.example.airqualityservice.api.airqualityservice;

import com.example.airqualityservice.api.airqualityservice.AirQualityApi;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class AirQualityApiFactory {

    private final Map<City, AirQualityApi> airQualityApiMap = new HashMap<>();

    public AirQualityApiFactory(List<AirQualityApi> airQualityApiList) {
        for (AirQualityApi airQualityApi :
                airQualityApiList) {
            airQualityApiMap.put(airQualityApi.getCity(), airQualityApi);
        }
    }

    @Cacheable(value = "cache::airQualityInfo")
    public AirQualityApi getAirQualityApi(City city) {
        return Optional.of(airQualityApiMap.get(city))
                .orElseThrow(() -> new RuntimeException(city + "시/도 대기질 정보는 준비중입니다."));
    }
}
