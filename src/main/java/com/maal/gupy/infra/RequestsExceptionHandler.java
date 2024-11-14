package com.maal.gupy.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threat404() {
        ExceptionDTO response = new ExceptionDTO("Data not found provided ID", 404);
        return  ResponseEntity.status(404).body(response);
    }
}
