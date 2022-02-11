package com.example.airqualityservice.controller.dto;

import com.example.airqualityservice.service.AirQualityGrade;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AirQualityResDto {
    private String city;
    private Double pm10Average;
    private AirQualityGrade pm10AverageGrade;
    private String measurementTime;
    private List<AirQualityInfo> elements = new ArrayList<>();

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
    }
}
