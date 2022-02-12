package com.example.airqualityservice.controller.dto;

import com.example.airqualityservice.service.City;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirQualityReqDto {
    private City city;
    private String district;

    public AirQualityReqDto(City city, String district) {
        this.city = city;

        if (district != null) {
            this.district = district;
        }
    }
}
