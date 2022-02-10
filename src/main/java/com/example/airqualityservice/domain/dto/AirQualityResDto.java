package com.example.airqualityservice.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AirQualityResDto {
    private String city;
    private Double pm10Average;
    private String pm10AverageGrade;
    private String measurementTime;
    private List<AirQualityInfo> elements = new ArrayList<>();

    @Data
    public static class AirQualityInfo {
        private String district;
        private Integer pm25;
        private String pm25Grade;
        private Integer pm10;
        private String pm10Grade;
        private Double o3;
        private String o3Grade;
        private Double no2;
        private String no2Grade;
        private Double co;
        private String coGrade;
        private Double so2;
        private String so2Grade;
    }
}
