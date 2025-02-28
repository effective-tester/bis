package ru.itmo.bis.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BisRuntimeException extends RuntimeException {

    private final HttpStatus statusCode;

    protected BisRuntimeException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    protected BisRuntimeException(HttpStatus statusCode) {
        super();
        this.statusCode = statusCode;
    }
}
