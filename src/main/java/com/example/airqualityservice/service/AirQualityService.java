package com.example.airqualityservice.service;

import com.example.airqualityservice.AirQualityResFactory;
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
    private final AirQualityResFactory airQualityResFactory;
    public AirQualityResDto getAirQualityInfo(AirQualityReqDto reqDto) {
        City reqCity = reqDto.getCity();
        String reqDistrict = reqDto.getDistrict();

        AirQualityResDto airQuality = airQualityResFactory.getAirQuality(reqCity);

        if (reqDistrict != null) {
            return airQuality.searchByDistrict(reqDistrict);
        }

        return airQuality;
    }
}
