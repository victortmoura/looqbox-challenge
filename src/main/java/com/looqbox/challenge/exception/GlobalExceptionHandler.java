package com.looqbox.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleInvalidEnumValue(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid sort type. Available options: ALPHABETICAL, LENGTH");
    }
}
