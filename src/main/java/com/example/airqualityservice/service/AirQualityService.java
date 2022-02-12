package com.example.airqualityservice.service;

import com.example.airqualityservice.api.busan.BusanAirQualityApiDto;
import com.example.airqualityservice.controller.dto.AirQualityReqDto;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.api.busan.BusanAirQualityApiCaller;
import com.example.airqualityservice.api.seoul.SeoulAirQualityApiCaller;
import com.example.airqualityservice.api.seoul.SeoulAirQualityApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.airqualityservice.utils.AirQualityGradeUtility.*;

@RequiredArgsConstructor
@Service
public class AirQualityService {
    private final BusanAirQualityApiCaller busanAirQualityApiCaller;
    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityResDto getAirQualityInfo(AirQualityReqDto reqDto) {
        City reqCity = reqDto.getCity();
        String reqDistrict = reqDto.getDistrict();

        if (reqCity == City.서울시) {
            AirQualityResDto airQuality = seoulAirQualityApiCaller.getAirQuality();

            if (reqDistrict != null) {
                return airQuality.searchByDistrict(reqDistrict);
            }

            return airQuality;
        }

        if (reqCity == City.부산시) {
            AirQualityResDto airQuality = busanAirQualityApiCaller.getAirQuality();

            if (reqDistrict != null) {
                return airQuality.searchByDistrict(reqDistrict);
            }

            return airQuality;
        }



        throw new RuntimeException(reqDto.getCity() + "대기질 정보는 준비중 입니다.");
    }
}
