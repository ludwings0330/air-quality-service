package com.example.airqualityservice.api.busan;

import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.AirQualityGrade;
import com.example.airqualityservice.service.City;
import com.example.airqualityservice.utils.AirQualityGradeUtility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BusanAirQualityApiCaller {
    private final BusanAirQualityApi busanAirQualityApi;

    public BusanAirQualityApiCaller(@Value("${api.busan.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.busanAirQualityApi = retrofit.create(BusanAirQualityApi.class);
    }

    public AirQualityResDto getAirQuality() {
        try {
            var call = busanAirQualityApi.getAirQuality();
            var response = call.execute().body();

            if (response == null || response.getResult() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResult().isSuccess()) {

                return convertToAirQualityResDto(response);
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다. header=" + response.getResult().getHeader());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }

    private AirQualityResDto convertToAirQualityResDto(BusanAirQualityApiDto.GetAirQualityResponse response) {
        List<BusanAirQualityApiDto.Item> items = response.getResult().getItems();
        Double pm10Average = getPm10Average(items);
        AirQualityGrade pm10AverageGrade = AirQualityGradeUtility.judgePm10Grade(pm10Average);
        List<AirQualityResDto.AirQualityInfo> elements = convertToAirQualityInfoList(items);
        // 부산 평균 미세먼지 정보
        // 부산 평균 미세먼지 정보 판단
        // 시/도 명

        return AirQualityResDto.builder()
                .city(City.부산시)
                .pm10Average(pm10Average)
                .pm10AverageGrade(pm10AverageGrade)
                .elements(elements)
                .build();
    }

    private List<AirQualityResDto.AirQualityInfo> convertToAirQualityInfoList(List<BusanAirQualityApiDto.Item> items) {
        return items.stream()
                .map(item -> new AirQualityResDto.AirQualityInfo(
                        item.getSite(),
                        Integer.valueOf(item.getPm25()),
                        Integer.valueOf(item.getPm10()),
                        Double.valueOf(item.getO3()),
                        Double.valueOf(item.getNo2()),
                        Double.valueOf(item.getCo()),
                        Double.valueOf(item.getSo2())
                )).collect(Collectors.toList());
    }

    private Double getPm10Average(List<BusanAirQualityApiDto.Item> items) {
        return items.stream()
                .map(item -> item.getPm10())
                .mapToDouble(Double::valueOf)
                .average()
                .orElse(Double.NaN);
    }
}
