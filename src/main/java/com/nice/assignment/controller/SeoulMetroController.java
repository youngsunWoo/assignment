package com.nice.assignment.controller;


import com.nice.assignment.dto.ConditionParamDto;
import com.nice.assignment.dto.MetaDataInfo;
import com.nice.assignment.dto.PassengerCountDto;
import com.nice.assignment.service.PassengerStatisticsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "서울교통공사_승하차인원", description = "서울교통공사의 월별 승하차인원(호선, 역번호, 역명) 데이터, (승하차인원 : 승차인원 + 하차인원)")
@RestController
@RequestMapping("/seoul-metro")
@RequiredArgsConstructor
public class SeoulMetroController {

    @Autowired
    PassengerStatisticsService passengerStatisticsService;

    @PostMapping("/upload/passengers")
    @Parameter(in = ParameterIn.HEADER, name = "X-AUTH-TOKEN")
    public ResponseEntity saveMonthlyPassengerInfo(@RequestBody MetaDataInfo metaDataInfo) {
        passengerStatisticsService.saveMonthlyData(metaDataInfo.getYear(), metaDataInfo.getFilename());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/data/passengers/daily/average")
    public List<PassengerCountDto> getAverageDaily(@RequestBody ConditionParamDto conditionParamDto) {
        return passengerStatisticsService.getDailyCountRank(conditionParamDto);
    }

    @PostMapping("/data/passengers/monthly/average")
    public List<PassengerCountDto> getAverageMonthly(@RequestBody ConditionParamDto conditionParamDto) {
        return passengerStatisticsService.getMonthlyCountRank(conditionParamDto);
    }

    @PostMapping("/data/passengers/monthly/difference")
    public List<PassengerCountDto> getdifference(@RequestBody ConditionParamDto conditionParamDto) {
        return passengerStatisticsService.getMonthlyDiffRank(conditionParamDto);
    }
}
