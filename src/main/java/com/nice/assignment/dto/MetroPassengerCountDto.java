package com.nice.assignment.dto;

import com.nice.assignment.entity.MetroPassengerCount;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MetroPassengerCountDto{

    private String stationName;
    private Long passengerCount;

    public static MetroPassengerCountDto of(MetroPassengerCount metroPassengerCount) {
        if (metroPassengerCount == null) {
            return null;
        }

        return  MetroPassengerCountDto.builder()
                .stationName(metroPassengerCount.getStationName())
                .passengerCount(metroPassengerCount.getPassengerCount())
                .build();
    }

}
