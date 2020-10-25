package com.nice.assignment.metro.dto;

import com.nice.assignment.metro.entity.PassengerCount;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PassengerCountDto {

    private String stationName;
    private Long passengerCount;

    public static PassengerCountDto of(PassengerCount passengerCount) {
        if (passengerCount == null) {
            return null;
        }

        return  PassengerCountDto.builder()
                .stationName(passengerCount.getStationName())
                .passengerCount(passengerCount.getPassengerCount())
                .build();
    }

}
