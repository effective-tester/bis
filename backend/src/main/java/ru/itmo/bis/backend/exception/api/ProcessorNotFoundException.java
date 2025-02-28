package ru.itmo.bis.backend.exception.api;

import org.springframework.http.HttpStatus;
import ru.itmo.bis.backend.exception.BisRuntimeException;

public class ProcessorNotFoundException extends BisRuntimeException {

    public ProcessorNotFoundException() {
        super(
                "Processor not found",
                HttpStatus.NOT_FOUND
        );
    }
}