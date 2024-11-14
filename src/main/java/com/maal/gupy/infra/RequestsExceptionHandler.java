package com.maal.gupy.infra;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threat404() {
        return ResponseEntity.badRequest().body(
                new ExceptionDTO("Entity not foud", 400)
        );
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionDTO> threat401() {
        return  ResponseEntity.badRequest().body(
                new ExceptionDTO("Entity already exists", 401)
        );
    }
}


