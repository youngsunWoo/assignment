package com.nice.assignment.auth.exception;

import com.nice.assignment.auth.responce.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(UserRuntimeException.class)
    protected ResponseEntity<ApiResponse<Object>> handleProductExceptionHandler(UserRuntimeException e) {
        logger.error(e.getMessage(), e);

        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(e.getResponseCode().getCode());
        response.setMessage(e.getMessage());
        response.setData(e.getData());
        return new ResponseEntity<>(response, e.getResponseCode().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        logger.error(e.getMessage(), e);

        ApiResponse<String> response = ApiResponse.of("ERROR");
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException e) {

        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ApiResponse<String> response = ApiResponse.of("ERROR");
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
