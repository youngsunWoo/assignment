package com.nice.assignment.metro.dto;

import com.nice.assignment.metro.entity.PassengerDiffrentCount;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PassengerDiffrentCountDto {

    private String stationName;
    private Long maxCount;
    private Long minCount;

    public Long getDiff(){
        return maxCount-minCount;
    }

    public static PassengerDiffrentCountDto of(PassengerDiffrentCount passengerDiffrentCount) {
        if (passengerDiffrentCount == null) {
            return null;
        }
        return  PassengerDiffrentCountDto.builder()
                .stationName(passengerDiffrentCount.getStationName())
                .maxCount(passengerDiffrentCount.getMaxCount())
                .minCount(passengerDiffrentCount.getMinCount())
                .build();
    }

}
