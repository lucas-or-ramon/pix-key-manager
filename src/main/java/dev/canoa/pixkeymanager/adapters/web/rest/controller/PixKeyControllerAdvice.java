package dev.canoa.pixkeymanager.adapters.web.rest.controller;

import dev.canoa.pixkeymanager.application.exception.NotFoundPixKeyException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PixKeyControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        if (fieldError != null) {
            return ResponseEntity.unprocessableEntity().body(fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.unprocessableEntity().body(exception.getMessage());
    }

    @ExceptionHandler(NotFoundPixKeyException.class)
    public ResponseEntity<String> handleNotFoundPixKeyException(NotFoundPixKeyException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ConstraintViolationException exception) {
        return ResponseEntity.unprocessableEntity().body(exception.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}
