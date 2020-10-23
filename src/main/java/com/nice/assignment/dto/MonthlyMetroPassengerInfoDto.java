package com.nice.assignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyMetroPassengerInfoDto  {
    private Long year;
    private Long month;
    private Long stationNo;
    private String line;
    private String stationName;
    private Long passengerCount;
}
