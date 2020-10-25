package com.nice.assignment.metro.controller;


import com.nice.assignment.common.response.ApiResponse;
import com.nice.assignment.metro.dto.ConditionParamDto;
import com.nice.assignment.metro.dto.FileInfo;
import com.nice.assignment.metro.dto.PassengerCountDto;
import com.nice.assignment.metro.service.PassengerStatisticsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "서울교통공사_승하차인원", description = "서울교통공사의 월별 승하차인원(호선, 역번호, 역명) 데이터, (승하차인원 : 승차인원 + 하차인원)")
@RestController
@RequestMapping("/v1/seoul-metro")
@RequiredArgsConstructor
@Slf4j
public class SeoulMetroController {

    @Autowired
    PassengerStatisticsService passengerStatisticsService;

    @PostMapping("/upload/passengers")
    @Parameter(in = ParameterIn.HEADER, name = "X-AUTH-TOKEN")
    public ApiResponse saveMonthlyPassengerInfo(@RequestBody FileInfo metaDataInfo) {
        passengerStatisticsService.saveMonthlyData(metaDataInfo.getYear(), metaDataInfo.getFilename());
        return ApiResponse.SUCCESS;
    }

    @PostMapping("/data/passengers/daily/average")
    public ApiResponse<List<PassengerCountDto>> getAverageDaily(@RequestBody ConditionParamDto conditionParamDto) {
        log.debug("{}\tprams={}", ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), conditionParamDto.toString());
        return ApiResponse.of(passengerStatisticsService.getDailyCountRank(conditionParamDto));
    }

    @PostMapping("/data/passengers/monthly/average")
    public ApiResponse<List<PassengerCountDto>> getAverageMonthly(@RequestBody ConditionParamDto conditionParamDto) {
        log.debug("{}\tprams={}", ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), conditionParamDto.toString());
        return ApiResponse.of(passengerStatisticsService.getMonthlyCountRank(conditionParamDto));
    }

    @PostMapping("/data/passengers/monthly/difference")
    public ApiResponse<List<PassengerCountDto>> getdifference(@RequestBody ConditionParamDto conditionParamDto) {
        log.debug("{}\tprams={}", ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), conditionParamDto.toString());
        return ApiResponse.of(passengerStatisticsService.getMonthlyDiffRank(conditionParamDto));
    }
}
