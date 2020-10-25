package com.nice.assignment.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    public static final ApiResponse<?> SUCCESS = of(ApiResponseCode.SUCCESS);

    @JsonProperty("code")
    private int code;
    @JsonProperty("msg")
    private String message;
    @JsonProperty("data")
    private T data;

    public static <S> ApiResponse<S> of(S data) {
        return of(ApiResponseCode.SUCCESS, data);
    }

    public static <S> ApiResponse<S> of(ResponseCodeBase code) {
        return of(code.getCode(), code.getMessage());
    }

    public static <S> ApiResponse<S> of(ResponseCodeBase code, S data) {
        ApiResponse<S> response = of(code);
        response.setData(data);
        return response;
    }

    public static <S> ApiResponse<S> of(int code, String message) {
        ApiResponse<S> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
