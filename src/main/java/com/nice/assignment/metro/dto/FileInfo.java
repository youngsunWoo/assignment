package com.nice.assignment.metro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileInfo {
    @Schema(description = "저장할 대상 연도", required = true, example = "2019")
    Long year;
    @Schema(description = "저장할 파일 이름", required = true, example = "2019_data.csv")
    String filename;
}
