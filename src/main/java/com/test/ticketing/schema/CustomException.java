package com.test.ticketing.schema;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{
    private final HttpStatus status;
    private final String message;

    public CustomException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
