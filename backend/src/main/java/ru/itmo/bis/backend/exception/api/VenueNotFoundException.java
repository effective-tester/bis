package ru.itmo.bis.backend.exception.api;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

import java.util.UUID;

public class VenueNotFoundException extends BisRuntimeException {

    public VenueNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public VenueNotFoundException() {
        super(
                "Venue not found",
                HttpStatus.NOT_FOUND
        );
    }

    public VenueNotFoundException(UUID id) {
        super(
                "Venue not found with id: " + id,
                HttpStatus.NOT_FOUND
        );
    }
}