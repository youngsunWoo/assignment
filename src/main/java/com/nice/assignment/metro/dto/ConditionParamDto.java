package com.nice.assignment.metro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ConditionParamDto {
    Long year;
    boolean ascending;
    Long rankNum;
}
