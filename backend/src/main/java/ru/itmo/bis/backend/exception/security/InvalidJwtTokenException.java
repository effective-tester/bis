package ru.itmo.bis.backend.exception.security;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

public class InvalidJwtTokenException extends BisRuntimeException {

    public InvalidJwtTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}