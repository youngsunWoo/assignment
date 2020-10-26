package com.nice.assignment.metro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ConditionParamDto {
    @Schema(description = "검색 대상 연도", required = true, example = "2019")
    Long year;
    @Schema(description = "정렬 기준(true : 오름차순, false : 내림차순)", required = true)
    boolean ascending;
    @Schema(description = "검색 갯수", required = true, example = "10")
    Long rankNum;
}
