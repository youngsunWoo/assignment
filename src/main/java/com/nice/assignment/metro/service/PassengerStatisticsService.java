package com.nice.assignment.metro.service;

import com.nice.assignment.common.exception.CustomRuntimeException;
import com.nice.assignment.common.response.ApiResponseCode;
import com.nice.assignment.metro.dto.ConditionParamDto;
import com.nice.assignment.metro.dto.PassengerCountDto;
import com.nice.assignment.metro.dto.PassengerDiffrentCountDto;
import com.nice.assignment.metro.entity.MonthlyMetroPassengerInfo;
import com.nice.assignment.metro.entity.PassengerCount;
import com.nice.assignment.metro.entity.PassengerDiffrentCount;
import com.nice.assignment.metro.repository.MonthlyPassengerInfoRepository;
import com.univocity.parsers.common.record.Record;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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
    public List<MonthlyMetroPassengerInfo> saveMonthlyData(Long year, String fileName) {
        return monthlyPassengerInfoRepository.saveAll(
                convertToMonthlyMetroPassengerInfo(parseCsvService.parseCsvWithHeader(monthlyFilePath + fileName), year));
    }

    public List<PassengerCountDto> getDailyCountRank(ConditionParamDto conditionParamDto) {
        List<PassengerCount> list = monthlyPassengerInfoRepository.getYearlyCount(conditionParamDto.getYear());
        if (ObjectUtils.isEmpty(list)) {
            throw new CustomRuntimeException(ApiResponseCode.DATA_NOT_FOUND);
        }

        return list.stream()
                .map(PassengerCountDto::of)
                .sorted(conditionParamDto.isAscending() ?
                        Comparator.comparing(PassengerCountDto::getPassengerCount) :
                        Comparator.comparing(PassengerCountDto::getPassengerCount).reversed())
                .limit(conditionParamDto.getRankNum())
                .map(m -> {
                    m.setPassengerCount(conditionParamDto.getYear() % 4 == 0 ? m.getPassengerCount() / 366 : m.getPassengerCount() / 365);
                    return m;
                })
                .collect(Collectors.toList());
    }

    public List<PassengerCountDto> getMonthlyCountRank(ConditionParamDto conditionParamDto) {
        List<PassengerCount> list = monthlyPassengerInfoRepository.getYearlyCount(conditionParamDto.getYear());
        if (ObjectUtils.isEmpty(list)) {
            throw new CustomRuntimeException(ApiResponseCode.DATA_NOT_FOUND);
        }

        return list.stream()
                .map(PassengerCountDto::of)
                .sorted(conditionParamDto.isAscending() ?
                        Comparator.comparing(PassengerCountDto::getPassengerCount) :
                        Comparator.comparing(PassengerCountDto::getPassengerCount).reversed())
                .limit(conditionParamDto.getRankNum())
                .map(m -> {
                    m.setPassengerCount(m.getPassengerCount() / 12);
                    return m;
                })
                .collect(Collectors.toList());
    }

    public List<PassengerCountDto> getMonthlyDiffRank(ConditionParamDto conditionParamDto) {
        List<PassengerDiffrentCount> list = monthlyPassengerInfoRepository.getDiffMaxAndMin(conditionParamDto.getYear());
        if (ObjectUtils.isEmpty(list)) {
            throw new CustomRuntimeException(ApiResponseCode.DATA_NOT_FOUND);
        }

        return list.stream()
                .map(PassengerDiffrentCountDto::of)
                .sorted(conditionParamDto.isAscending() ?
                        Comparator.comparing(PassengerDiffrentCountDto::getDiff) :
                        Comparator.comparing(PassengerDiffrentCountDto::getDiff).reversed())
                .limit(conditionParamDto.getRankNum())
                .map(m -> {
                            return PassengerCountDto.builder()
                                    .stationName(m.getStationName())
                                    .passengerCount(m.getDiff())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    private List<MonthlyMetroPassengerInfo> convertToMonthlyMetroPassengerInfo(List<Record> Records, Long year) {
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
