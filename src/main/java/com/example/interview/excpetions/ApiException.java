package com.example.interview.excpetions;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {
    private String code;
    private String message;
    private String timestamp;

    public ApiException(String code, String message, Throwable cause, String timestamp) {
        super(cause);
        this.code = code;
        this.message = message;
        this.timestamp=timestamp;
    }
}
