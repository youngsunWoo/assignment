package com.nice.assignment.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode implements ResponseCodeBase {
    SUCCESS(HttpStatus.OK, 200, "OK"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -500, "Server Error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, -400, "Incorrect Request"),

    ;
    private final HttpStatus status;
    private final int code;
    private final String message;
}
