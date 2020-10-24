package com.nice.assignment.dto;

import com.nice.assignment.entity.PassengerDiffrentCount;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
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
