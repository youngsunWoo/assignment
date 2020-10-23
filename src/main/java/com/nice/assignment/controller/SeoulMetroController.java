package com.nice.assignment.controller;


import com.nice.assignment.dto.MetaDataInfo;
import com.nice.assignment.service.PassengerStatisticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "서울교통공사_승하차인원", description = "서울교통공사의 월별 승하차인원(호선, 역번호, 역명) 데이터, (승하차인원 : 승차인원 + 하차인원)")
@RestController
@RequestMapping("/seoul-metro")
@RequiredArgsConstructor
public class SeoulMetroController {

    @Autowired
    PassengerStatisticsService passengerStatisticsService;

    @PostMapping("/passengers")
    public ResponseEntity addPassengerTotals(@RequestBody MetaDataInfo metaDataInfo) {
        passengerStatisticsService.addMonthlyData(metaDataInfo.getYear(),metaDataInfo.getFilename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/passengers/daily/{year}")
    public ResponseEntity<String> getPassengersDaily(@PathVariable("year")Long year) {
        return ResponseEntity.ok()
                .body("Hello! ");
    }


    @GetMapping("/passengers/monthly/{year}")
    public ResponseEntity<String> getPassengersMonthly(@PathVariable("year")Long year) {
        return ResponseEntity.ok()
                .body("Hello! ");
    }
}
