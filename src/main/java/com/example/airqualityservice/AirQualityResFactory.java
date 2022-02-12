package com.example.airqualityservice;

import com.example.airqualityservice.api.busan.BusanAirQualityApiCaller;
import com.example.airqualityservice.api.seoul.SeoulAirQualityApiCaller;
import com.example.airqualityservice.controller.dto.AirQualityReqDto;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AirQualityResFactory {
    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;
    private final BusanAirQualityApiCaller busanAirQualityApiCaller;

    public AirQualityResDto getAirQuality(City city) {

        if (city == City.서울시) {
            return seoulAirQualityApiCaller.getAirQuality();
        }

        if (city == City.부산시) {
            return busanAirQualityApiCaller.getAirQuality();
        }

        throw new RuntimeException(city + "시/도 대기질 정보는 준비중입니다.");
    }
}
