package ru.itmo.bis.backend.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itmo.bis.backend.exception.BisRuntimeException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final String ERROR_NAME = "error";


  @ExceptionHandler(BisRuntimeException.class)
  public ResponseEntity<Map<String, String>> handleTaskNotFoundException(BisRuntimeException ex) {
    return ResponseEntity
        .status(ex.getStatusCode())
        .body(createErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(errors);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(createErrorResponse("Access is denied"));
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Map<String, String>> handleBadCredentialsException(AuthenticationException ex) {
    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(createErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleNotReadableException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(createErrorResponse("Cannot deserialize value"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(createErrorResponse("An unexpected error occurred"));
  }

  private static Map<String, String> createErrorResponse(String message) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put(ERROR_NAME, message);
    return errorResponse;
  }
}
