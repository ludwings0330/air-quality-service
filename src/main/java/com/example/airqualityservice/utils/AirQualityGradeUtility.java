package com.example.airqualityservice.utils;

import com.example.airqualityservice.service.AirQualityGrade;

import static com.example.airqualityservice.service.AirQualityGrade.*;

public class AirQualityGradeUtility {
    private AirQualityGradeUtility() {
    }

    public static AirQualityGrade judgePm25Grade(Double pm25) {
        if (pm25 <= 15) {
            return 좋음;
        }

        if (pm25 <= 35) {
            return 보통;
        }

        if (pm25 <= 75) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static AirQualityGrade judgePm10Grade(Double pm10) {
        if (pm10 <= 30) {
            return 좋음;
        }

        if (pm10 <= 80) {
            return 보통;
        }

        if (pm10 <= 150) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static AirQualityGrade judgeO3Grade(Double o3) {
        if (o3 <= 0.03) {
            return 좋음;
        }

        if (o3 <= 0.09) {
            return 보통;
        }

        if (o3 <= 0.15) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static  AirQualityGrade judgeNo2Grade(Double no2) {
        if (no2 <= 0.03) {
            return 좋음;
        }

        if (no2 <= 0.06) {
            return 보통;
        }

        if (no2 <= 0.2) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static  AirQualityGrade judgeCoGrade(Double co) {
        if (co <= 2) {
            return 좋음;
        }

        if (co <= 9) {
            return 보통;
        }

        if (co <= 15) {
            return 나쁨;
        }

        return 매우나쁨;
    }

    public static AirQualityGrade judgeSo2Grade(Double so2) {
        if (so2 <= 0.02) {
            return 좋음;
        }

        if (so2 <= 0.05) {
            return 보통;
        }

        if (so2 <= 0.15) {
            return 나쁨;
        }

        return 매우나쁨;
    }

}
