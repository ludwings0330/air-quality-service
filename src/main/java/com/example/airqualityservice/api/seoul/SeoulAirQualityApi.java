package com.example.airqualityservice.api.seoul;

import retrofit2.Call;
import retrofit2.http.GET;


//http://openAPI.seoul.go.kr:8088/536c58687267757531334b46554b41/json/RealtimeCityAir/1/25
public interface SeoulAirQualityApi {
    String serviceKey = "536c58687267757531334b46554b41";

    @GET(serviceKey + "/json/RealtimeCityAir/1/25")
    Call<SeoulAirQualityApiDto.GetAirQualityResponse> getAirQuality();
}
