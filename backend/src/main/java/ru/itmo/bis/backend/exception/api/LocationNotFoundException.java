package ru.itmo.bis.backend.exception.api;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

import java.util.UUID;

public class LocationNotFoundException extends BisRuntimeException {

    public LocationNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public LocationNotFoundException() {
        super(
                "Location not found",
                HttpStatus.NOT_FOUND
        );
    }

    public LocationNotFoundException(UUID id) {
        super(
                "Location not found with id: " + id,
                HttpStatus.NOT_FOUND
        );
    }
}