package com.nice.assignment.service;

import com.nice.assignment.dto.MetroPassengerCountDto;
import com.nice.assignment.entity.MonthlyMetroPassengerInfo;
import com.nice.assignment.repository.MonthlyPassengerInfoRepository;
import com.univocity.parsers.common.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PassengerStatisticsService {

    @Value("${subway.file-path.monthly}")
    String monthlyFilePath;

    private final ParseCsvService parseCsvService;
    private final MonthlyPassengerInfoRepository monthlyPassengerInfoRepository;

    @Transactional
    public List<MonthlyMetroPassengerInfo> addMonthlyData(Long year, String fileName) {
        return monthlyPassengerInfoRepository.saveAll(
                convertToPassengerTotal(parseCsvService.parseCsvWithHeader(monthlyFilePath + fileName), year));
    }

    public List<MetroPassengerCountDto> getDailyCountRank(Long year, boolean ascending, Long rankNum) {
        monthlyPassengerInfoRepository.getYearlyCount(year);

        return monthlyPassengerInfoRepository.getYearlyCount(year).stream()
                .map(MetroPassengerCountDto::of)
                .sorted(ascending ?
                        Comparator.comparing(MetroPassengerCountDto::getPassengerCount) :
                        Comparator.comparing(MetroPassengerCountDto::getPassengerCount).reversed())
                .limit(rankNum)
                .map(m -> {
                    m.setPassengerCount(year % 4 == 0 ? m.getPassengerCount() / 366 : m.getPassengerCount() / 365);
                    return m; })
                .collect(Collectors.toList());
    }

    public List<MetroPassengerCountDto> getMonthlyCountRank(Long year, boolean ascending, Long rankNum) {
        monthlyPassengerInfoRepository.getYearlyCount(year);

        return monthlyPassengerInfoRepository.getYearlyCount(year).stream()
                .map(MetroPassengerCountDto::of)
                .sorted(ascending ?
                        Comparator.comparing(MetroPassengerCountDto::getPassengerCount) :
                        Comparator.comparing(MetroPassengerCountDto::getPassengerCount).reversed())
                .limit(rankNum)
                .map(m -> {
                    m.setPassengerCount(m.getPassengerCount() / 12);
                    return m;
                })
                .collect(Collectors.toList());
    }

    private List<MonthlyMetroPassengerInfo> convertToPassengerTotal(List<Record> Records, Long year) {
        List<MonthlyMetroPassengerInfo> list = new ArrayList<>();
        for (Record record : Records) {
            for (int i = 1; i < 13; i++) {
                list.add(MonthlyMetroPassengerInfo.builder()
                        .year(year)
                        .line(record.getString("호선"))
                        .stationName(record.getString("역명"))
                        .stationNo(record.getLong("역번호"))
                        .month((long) i)
                        .passengerCount(
                                Long.parseLong(record.getString(i + "월").replace(",", "")))
                        .build());
            }
        }
        return list;
    }
}
