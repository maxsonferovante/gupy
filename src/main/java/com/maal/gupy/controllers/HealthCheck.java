package com.maal.gupy.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/health")
public class HealthCheck {

    @GetMapping()
    public ResponseEntity<String> healthCheckResponseEntity() {
        return ResponseEntity.ok("Health check OK! " + new Date());
    }
}
