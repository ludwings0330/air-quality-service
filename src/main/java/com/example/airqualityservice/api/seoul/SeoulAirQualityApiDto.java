package com.example.airqualityservice.api.seoul;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

public class SeoulAirQualityApiDto {
    @Data
    public static class GetAirQualityResponse {

        @JsonProperty("RealtimeCityAir")
        private Result result;
    }

    @Data
    public static class Result {
        @JsonProperty("list_total_count")
        private Integer totalCount;

        @JsonProperty("RESULT")
        private Header header;

        @JsonProperty("row")
        private List<Row> rows;

        public boolean isSuccess() {
            if (Objects.equals(header.getCode(), "INFO-000")) {
                return true;
            }

            return false;
        }
    }

    @Data
    public static class Header {
        @JsonProperty("CODE")
        private String code;
        @JsonProperty("MESSAGE")
        private String message;
    }

    @Data
    public static class Row {

        @JsonProperty("MSRDT")
        private String measurementTime;
        @JsonProperty("MSRRGN_NM")
        private String regionName;
        @JsonProperty("MSRSTE_NM")
        private String district;
        @JsonProperty("PM10")
        private Double pm10;
        @JsonProperty("PM25")
        private Double pm25;
        @JsonProperty("O3")
        private Double o3;
        @JsonProperty("NO2")
        private Double no2;
        @JsonProperty("CO")
        private Double co;
        @JsonProperty("SO2")
        private Double so2;
    }


}
