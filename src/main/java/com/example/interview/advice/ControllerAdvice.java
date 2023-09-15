package com.example.interview.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    /*@ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ErrorDomain> apiExceptionHandler(ApiException apiException) {
        String timestamp = Utils.getTimestampStringInUTCFormat();
        ErrorDomain errorDomain = ErrorDomain.builder()
                .code(apiException.getCode())
                .message(apiException.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorDomain, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDomain> runtimeExceptionHandler(RuntimeException runtimeException) {
        String timestamp = Utils.getTimestampStringInUTCFormat();
        ErrorDomain errorDomain = ErrorDomain.builder()
                .code(Constants.UNEXPECTED_ERROR)
                .message(runtimeException.getMessage())
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(errorDomain, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
