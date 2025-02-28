package ru.itmo.bis.backend.exception.security;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

public class RoleNotFoundException extends BisRuntimeException {

    public RoleNotFoundException() {
        super(
                "Role not found",
                HttpStatus.NOT_FOUND
        );
    }
}