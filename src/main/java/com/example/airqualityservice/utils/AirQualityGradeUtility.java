package com.example.airqualityservice.utils;

import com.example.airqualityservice.service.AirQualityGrade;

import static com.example.airqualityservice.service.AirQualityGrade.*;

public class AirQualityGradeUtility {
    private AirQualityGradeUtility() {

    }

    public static AirQualityGrade judgePm25Grade(Double pm25) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 15d;
        final Double NORMAL_BP_LO = 16d;
        final Double NORMAL_BP_HI = 35d;
        final Double BAD_BP_LO = 36d;
        final Double BAD_BP_HI = 75d;

        if (GOOD_BP_LO <= pm25 && pm25 <= GOOD_BP_HI) {
            return 좋음;
        }

        if (NORMAL_BP_LO <= pm25 && pm25 <= NORMAL_BP_HI) {
            return 보통;
        }

        if (BAD_BP_LO <= pm25 && pm25 <= BAD_BP_HI) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static AirQualityGrade judgePm10Grade(Double pm10) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 30d;
        final Double NORMAL_BP_LO = 31d;
        final Double NORMAL_BP_HI = 80d;
        final Double BAD_BP_LO = 81d;
        final Double BAD_BP_HI = 150d;

        if (GOOD_BP_LO <= pm10 && pm10 <= GOOD_BP_HI) {
            return 좋음;
        }

        if (NORMAL_BP_LO <= pm10 && pm10 <= NORMAL_BP_HI) {
            return 보통;
        }

        if (BAD_BP_LO <= pm10 && pm10 <= BAD_BP_HI) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static AirQualityGrade judgeO3Grade(Double o3) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 0.030d;
        final Double NORMAL_BP_LO = 0.031d;
        final Double NORMAL_BP_HI = 0.090d;
        final Double BAD_BP_LO = 0.091d;
        final Double BAD_BP_HI = 0.150d;

        if (GOOD_BP_LO <= o3 && o3 <= GOOD_BP_HI) {
            return 좋음;
        }

        if (NORMAL_BP_LO <= o3 && o3 <= NORMAL_BP_HI) {
            return 보통;
        }

        if (BAD_BP_LO <= o3 && o3 <= BAD_BP_HI) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static  AirQualityGrade judgeNo2Grade(Double no2) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 0.030d;
        final Double NORMAL_BP_LO = 0.031d;
        final Double NORMAL_BP_HI = 0.060d;
        final Double BAD_BP_LO = 0.061d;
        final Double BAD_BP_HI = 0.200d;

        if (GOOD_BP_LO <= no2 && no2 <= GOOD_BP_HI) {
            return 좋음;
        }

        if (NORMAL_BP_LO <= no2 && no2 <= NORMAL_BP_HI) {
            return 보통;
        }

        if (BAD_BP_LO <= no2 && no2 <= BAD_BP_HI) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static  AirQualityGrade judgeCoGrade(Double co) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 2d;
        final Double NORMAL_BP_LO = 2.01d;
        final Double NORMAL_BP_HI = 9d;
        final Double BAD_BP_LO = 9.01d;
        final Double BAD_BP_HI = 15d;

        if (GOOD_BP_LO <= co && co <= GOOD_BP_HI) {
            return 좋음;
        }

        if (NORMAL_BP_LO <= co && co <= NORMAL_BP_HI) {
            return 보통;
        }

        if (BAD_BP_LO <= co && co <= BAD_BP_HI) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static  AirQualityGrade judgeSo2Grade(Double so2) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 0.020d;
        final Double NORMAL_BP_LO = 0.021d;
        final Double NORMAL_BP_HI = 0.050d;
        final Double BAD_BP_LO = 0.051d;
        final Double BAD_BP_HI = 0.150d;

        if (GOOD_BP_LO <= so2 && so2 <= GOOD_BP_HI) {
            return 좋음;
        }

        if (NORMAL_BP_LO <= so2 && so2 <= NORMAL_BP_HI) {
            return 보통;
        }

        if (BAD_BP_LO <= so2 && so2 <= BAD_BP_HI) {
            return 나쁨;
        }

        return 매우나쁨;
    }

}
