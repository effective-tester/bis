package ru.itmo.bis.backend.exception.api;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

import java.util.UUID;

public class UserNotFoundException extends BisRuntimeException {

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException() {
        super(
                "User not found",
                HttpStatus.NOT_FOUND
        );
    }

    public UserNotFoundException(UUID id) {
        super(
                "User not found with id: " + id,
                HttpStatus.NOT_FOUND
        );
    }
}