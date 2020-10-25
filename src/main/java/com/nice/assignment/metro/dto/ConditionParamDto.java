package com.nice.assignment.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ConditionParamDto {
    Long year;
    boolean ascending;
    Long rankNum;
}
