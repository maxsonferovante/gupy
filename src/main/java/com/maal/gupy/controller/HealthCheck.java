package com.maal.gupy.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HealthCheck {

    @GetMapping()
    public String healthCheck() {
        return new Date().toString();
    }
}
