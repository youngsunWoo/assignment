package com.nice.assignment.service;

import com.nice.assignment.entity.MonthlyPassengerInfo;
import com.nice.assignment.repository.MonthlyPassengerInfoRepository;
import com.univocity.parsers.common.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PassengerStatisticsService {

    @Value("${subway.file-path.monthly}")
    String monthlyFilePath;

    private final ParseCsvService parseCsvService;
    private final MonthlyPassengerInfoRepository monthlyPassengerInfoRepository;

    @Transactional
    public List<MonthlyPassengerInfo> addMonthlyData(Long year,String fileName) {
        return monthlyPassengerInfoRepository.saveAll(
                convertToPassengerTotal(parseCsvService.parseCsvWithHeader(monthlyFilePath+fileName), year));
    }


    private List<MonthlyPassengerInfo> convertToPassengerTotal (List<Record> Records, Long year){
        List<MonthlyPassengerInfo> list = new ArrayList<>();
        for (Record record : Records) {
            for (int i=1;i<13;i++){
                list.add(MonthlyPassengerInfo.builder()
                        .year(year)
                        .line(record.getString("호선"))
                        .stationName(record.getString("역명"))
                        .stationNo(record.getLong("역번호"))
                        .month((long)i)
                        .passengerCount(
                                Long.parseLong( record.getString(i+"월").replace(",","")))
                        .build());
            }
        }
        return list;
    }
}
