package com.aarambh.helpdesk.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiRuntimeException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ApiRuntimeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
