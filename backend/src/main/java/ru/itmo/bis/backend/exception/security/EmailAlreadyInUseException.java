package ru.itmo.bis.backend.exception.security;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

public class EmailAlreadyInUseException extends BisRuntimeException {

    public EmailAlreadyInUseException() {
        super(
                "Email already in use",
                HttpStatus.BAD_REQUEST
        );
    }

}