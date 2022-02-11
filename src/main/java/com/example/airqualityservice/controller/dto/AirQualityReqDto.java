package com.example.airqualityservice.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirQualityReqDto {
    private String city;
    private String district;

    public AirQualityReqDto(String city, String district) {
        this.city = city;

        if (district != null) {
            this.district = district;
        }
    }
}
