package com.nice.assignment.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MonthlyMetroPassengerInfo{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long seq;
    private Long year;
    private Long month;
    private Long stationNo;
    private String line;
    private String stationName;
    private Long passengerCount;
}
