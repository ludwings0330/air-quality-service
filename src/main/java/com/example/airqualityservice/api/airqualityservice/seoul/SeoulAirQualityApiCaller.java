package com.example.airqualityservice.api.airqualityservice.seoul;

import com.example.airqualityservice.api.airqualityservice.AirQualityApi;
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
public class SeoulAirQualityApiCaller implements AirQualityApi {

    private final SeoulAirQualityApi seoulAirQualityApi;

    public SeoulAirQualityApiCaller(@Value("${api.seoul.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.seoulAirQualityApi = retrofit.create(SeoulAirQualityApi.class);
    }

    @Override
    public City getCity() {
        return City.서울시;
    }

    @Override
    public AirQualityResDto getCachedAirQuality(City city) {
        return getAirQuality();
    }

    @Override
    public AirQualityResDto getAirQuality() {
        try {
            var call = seoulAirQualityApi.getAirQuality();
            var response = call.execute().body();

            if (response == null || response.getResult() == null) {
                throw new RuntimeException("getAirQuality 응답값이 존재하지 않습니다");
            }

            if (response.getResult().isSuccess()) {
                return convertToAirQualityResDto(response);
            }

            throw new RuntimeException("getAirQuality 응답이 올바르지 않습니다 header=" + response.getResult().getHeader());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("getAirQuality API error 발생 errorMessage" + e.getMessage());
        }
    }

    private AirQualityResDto convertToAirQualityResDto(SeoulAirQualityApiDto.GetAirQualityResponse response) {
        List<SeoulAirQualityApiDto.Row> rows = response.getResult().getRows();
        Double pm10Average = getPm10Average(rows);
        AirQualityGrade pm10AverageGrade = AirQualityGradeUtility.judgePm10Grade(pm10Average);
        List<AirQualityResDto.AirQualityInfo> elements = convertToAirQualityInfoList(rows);


        return AirQualityResDto.builder()
                .city(City.서울시)
                .pm10Average(pm10Average)
                .pm10AverageGrade(pm10AverageGrade)
                .elements(elements)
                .build();
    }


    private List<AirQualityResDto.AirQualityInfo> convertToAirQualityInfoList(List<SeoulAirQualityApiDto.Row> rows) {
        return rows.stream()
                .map(row -> new AirQualityResDto.AirQualityInfo(
                        row.getDistrict(),
                        row.getPm25().intValue(),
                        row.getPm10().intValue(),
                        row.getO3(),
                        row.getNo2(),
                        row.getCo(),
                        row.getSo2()
                ))
                .collect(Collectors.toList());
    }

    private Double getPm10Average(List<SeoulAirQualityApiDto.Row> rows) {
        return rows.stream()
                .mapToDouble(SeoulAirQualityApiDto.Row::getPm10)
                .average()
                .orElse(Double.NaN);
    }

}
