package com.nice.assignment.metro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MonthlyMetroPassengerInfoDto  {

    private Long year;
    private Long month;
    private Long stationNo;
    private String line;
    private String stationName;
    private Long passengerCount;
}
