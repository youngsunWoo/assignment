package com.nice.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Getter
@Setter
public class ConditionParamDto {
    Long year;
    boolean ascending;
    Long rankNum;
}
