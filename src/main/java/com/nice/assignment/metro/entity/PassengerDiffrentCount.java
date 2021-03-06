package com.nice.assignment.metro.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PassengerDiffrentCount {
    @Id
    private String stationName;
    private Long maxCount;
    private Long minCount;
}
