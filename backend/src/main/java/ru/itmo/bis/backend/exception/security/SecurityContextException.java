package ru.itmo.bis.backend.exception.security;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

public class SecurityContextException extends BisRuntimeException {

    public SecurityContextException() {
        super(
                "Could not set user authentication in security context",
                HttpStatus.UNAUTHORIZED
        );
    }
}