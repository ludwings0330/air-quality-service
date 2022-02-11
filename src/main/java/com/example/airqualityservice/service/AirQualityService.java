package com.example.airqualityservice.service;

import com.example.airqualityservice.controller.dto.AirQualityReqDto;
import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.api.busan.BusanAirQualityApiCaller;
import com.example.airqualityservice.api.seoul.SeoulAirQualityApiCaller;
import com.example.airqualityservice.api.seoul.SeoulAirQualityApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirQualityService {
    private final BusanAirQualityApiCaller busanAirQualityApiCaller;
    private final SeoulAirQualityApiCaller seoulAirQualityApiCaller;

    public AirQualityResDto getAirQuality(AirQualityReqDto reqDto) {
        SeoulAirQualityApiDto.GetAirQualityResponse airQuality = seoulAirQualityApiCaller.getAirQuality();


        return convertAirQualityToResDto(airQuality, reqDto);
    }



    public AirQualityResDto convertAirQualityToResDto(SeoulAirQualityApiDto.GetAirQualityResponse airQuality, AirQualityReqDto reqDto) {
        AirQualityResDto resDto = new AirQualityResDto();

        resDto.setCity(reqDto.getCity());
        resDto.setPm10Average(getPm10Average(airQuality));
        resDto.setMeasurementTime(airQuality.getResult().getRows().get(0).getMeasurementTime());

        fillAirQualityElements(resDto, airQuality, reqDto);

        judgeAirQualityGrade(resDto);

        return resDto;
    }
    public void fillAirQualityElements(AirQualityResDto resDto, SeoulAirQualityApiDto.GetAirQualityResponse airQuality, AirQualityReqDto reqDto) {
        List<SeoulAirQualityApiDto.Row> rows = airQuality.getResult().getRows();

        for (SeoulAirQualityApiDto.Row row :
             rows) {

            if (reqDto.getDistrict() == null || reqDto.getDistrict().equals(row.getDistrict())) {
                AirQualityResDto.AirQualityInfo info = new AirQualityResDto.AirQualityInfo();

                info.setDistrict(row.getDistrict());
                info.setPm25(row.getPm25().intValue());
                info.setPm10(row.getPm10().intValue());
                info.setO3(row.getO3());
                info.setNo2(row.getNo2());
                info.setCo(row.getCo());
                info.setSo2(row.getSo2());

                resDto.getElements().add(info);
            }
        }
    }

    private void judgeAirQualityGrade(AirQualityResDto resDto) {
        resDto.setPm10AverageGrade(judgePm10Grade(resDto.getPm10Average().intValue()));

        for (AirQualityResDto.AirQualityInfo info :
             resDto.getElements()) {
            info.setPm10Grade(judgePm10Grade(info.getPm10()));
            info.setPm25Grade(judgePm25Grade(info.getPm25()));
            info.setO3Grade(judgeO3Grade(info.getO3()));
            info.setNo2Grade(judgeNo2Grade(info.getNo2()));
            info.setCoGrade(judgeCoGrade(info.getCo()));
            info.setSo2Grade(judgeSo2Grade(info.getSo2()));
        }
    }

    private AirQualityGrade judgePm25Grade(Integer pm25) {
        final int GOOD_BP_LO = 0;
        final int GOOD_BP_HI = 15;
        final int NORMAL_BP_LO = 16;
        final int NORMAL_BP_HI = 35;
        final int BAD_BP_LO = 36;
        final int BAD_BP_HI = 75;

        if (GOOD_BP_LO <= pm25 && pm25 <= GOOD_BP_HI) {
            return AirQualityGrade.좋음;
        }

        if (NORMAL_BP_LO <= pm25 && pm25 <= NORMAL_BP_HI) {
            return AirQualityGrade.보통;
        }

        if (BAD_BP_LO <= pm25 && pm25 <= BAD_BP_HI) {
            return AirQualityGrade.나쁨;
        }

        return AirQualityGrade.매우나쁨;
    }

    private AirQualityGrade judgePm10Grade(Integer pm10) {
        final int GOOD_BP_LO = 0;
        final int GOOD_BP_HI = 30;
        final int NORMAL_BP_LO = 31;
        final int NORMAL_BP_HI = 80;
        final int BAD_BP_LO = 81;
        final int BAD_BP_HI = 150;

        if (GOOD_BP_LO <= pm10 && pm10 <= GOOD_BP_HI) {
            return AirQualityGrade.좋음;
        }

        if (NORMAL_BP_LO <= pm10 && pm10 <= NORMAL_BP_HI) {
            return AirQualityGrade.보통;
        }

        if (BAD_BP_LO <= pm10 && pm10 <= BAD_BP_HI) {
            return AirQualityGrade.나쁨;
        }

        return AirQualityGrade.매우나쁨;
    }

    private AirQualityGrade judgeO3Grade(Double o3) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 0.030d;
        final Double NORMAL_BP_LO = 0.031d;
        final Double NORMAL_BP_HI = 0.090d;
        final Double BAD_BP_LO = 0.091d;
        final Double BAD_BP_HI = 0.150d;

        if (GOOD_BP_LO <= o3 && o3 <= GOOD_BP_HI) {
            return AirQualityGrade.좋음;
        }

        if (NORMAL_BP_LO <= o3 && o3 <= NORMAL_BP_HI) {
            return AirQualityGrade.보통;
        }

        if (BAD_BP_LO <= o3 && o3 <= BAD_BP_HI) {
            return AirQualityGrade.나쁨;
        }

        return AirQualityGrade.매우나쁨;
    }

    private AirQualityGrade judgeNo2Grade(Double no2) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 0.030d;
        final Double NORMAL_BP_LO = 0.031d;
        final Double NORMAL_BP_HI = 0.060d;
        final Double BAD_BP_LO = 0.061d;
        final Double BAD_BP_HI = 0.200d;

        if (GOOD_BP_LO <= no2 && no2 <= GOOD_BP_HI) {
            return AirQualityGrade.좋음;
        }

        if (NORMAL_BP_LO <= no2 && no2 <= NORMAL_BP_HI) {
            return AirQualityGrade.보통;
        }

        if (BAD_BP_LO <= no2 && no2 <= BAD_BP_HI) {
            return AirQualityGrade.나쁨;
        }

        return AirQualityGrade.매우나쁨;
    }

    private AirQualityGrade judgeCoGrade(Double co) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 2d;
        final Double NORMAL_BP_LO = 2.01d;
        final Double NORMAL_BP_HI = 9d;
        final Double BAD_BP_LO = 9.01d;
        final Double BAD_BP_HI = 15d;

        if (GOOD_BP_LO <= co && co <= GOOD_BP_HI) {
            return AirQualityGrade.좋음;
        }

        if (NORMAL_BP_LO <= co && co <= NORMAL_BP_HI) {
            return AirQualityGrade.보통;
        }

        if (BAD_BP_LO <= co && co <= BAD_BP_HI) {
            return AirQualityGrade.나쁨;
        }

        return AirQualityGrade.매우나쁨;
    }

    private AirQualityGrade judgeSo2Grade(Double so2) {
        final Double GOOD_BP_LO = 0d;
        final Double GOOD_BP_HI = 0.020d;
        final Double NORMAL_BP_LO = 0.021d;
        final Double NORMAL_BP_HI = 0.050d;
        final Double BAD_BP_LO = 0.051d;
        final Double BAD_BP_HI = 0.150d;

        if (GOOD_BP_LO <= so2 && so2 <= GOOD_BP_HI) {
            return AirQualityGrade.좋음;
        }

        if (NORMAL_BP_LO <= so2 && so2 <= NORMAL_BP_HI) {
            return AirQualityGrade.보통;
        }

        if (BAD_BP_LO <= so2 && so2 <= BAD_BP_HI) {
            return AirQualityGrade.나쁨;
        }

        return AirQualityGrade.매우나쁨;
    }

    private Double getPm10Average(SeoulAirQualityApiDto.GetAirQualityResponse airQuality) {
        List<SeoulAirQualityApiDto.Row> rows = airQuality.getResult().getRows();

        int totalCount = airQuality.getResult().getTotalCount();
        Double totalPm10 = 0d;

        for (SeoulAirQualityApiDto.Row row:
             rows) {
            totalPm10 += row.getPm10();
        }

        return totalPm10/totalCount;
    }
}
