package com.example.airqualityservice.controller.dto;

import com.example.airqualityservice.service.AirQualityGrade;
import com.example.airqualityservice.service.City;
import com.example.airqualityservice.utils.AirQualityGradeUtility;
import lombok.Builder;
import lombok.Data;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class AirQualityResDto {
    private City city;
    private Double pm10Average;
    private AirQualityGrade pm10AverageGrade;
    private List<AirQualityInfo> elements = new ArrayList<>();

    public AirQualityResDto searchByDistrict(String district) {
        if (district == null) {
            return this;
        }
        AirQualityInfo searchedAirQualityInfo = searchByDistrictAirQualityInfo(district);
        elements = Collections.singletonList(searchedAirQualityInfo);
        return this;
    }

    private AirQualityInfo searchByDistrictAirQualityInfo(String district) {
        return elements.stream()
                .filter(districtAirQualityInfo -> districtAirQualityInfo.getDistrict().equals(district))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(district + "에 해당하는 자치구는 존재하지 않습니다."));
    }

    @Data
    public static class AirQualityInfo {
        private String district;
        private Integer pm25;
        private AirQualityGrade pm25Grade;
        private Integer pm10;
        private AirQualityGrade pm10Grade;
        private Double o3;
        private AirQualityGrade o3Grade;
        private Double no2;
        private AirQualityGrade no2Grade;
        private Double co;
        private AirQualityGrade coGrade;
        private Double so2;
        private AirQualityGrade so2Grade;

        public AirQualityInfo(String district, Integer pm25, Integer pm10, Double o3, Double no2, Double co, Double so2) {
            this.district = district;
            this.pm25 = pm25;
            this.pm10 = pm10;
            this.o3 = o3;
            this.no2 = no2;
            this.co = co;
            this.so2 = so2;

            this.pm25Grade = AirQualityGradeUtility.judgePm25Grade(Double.valueOf(pm25));
            this.pm10Grade = AirQualityGradeUtility.judgePm10Grade(Double.valueOf(pm10));
            this.o3Grade = AirQualityGradeUtility.judgeO3Grade(o3);
            this.no2Grade = AirQualityGradeUtility.judgeNo2Grade(no2);
            this.coGrade = AirQualityGradeUtility.judgeCoGrade(co);
            this.so2Grade = AirQualityGradeUtility.judgeSo2Grade(so2);
        }
    }
}
