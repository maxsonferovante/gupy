package com.maal.gupy.controllers;

import com.maal.gupy.domain.job.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
    private JobRepository repository;

    @GetMapping
    public ResponseEntity<?> getJobs() {
        var jobs = repository.findAll();
        if (jobs.isEmpty()) {
            return ResponseEntity.ok("No jobs found");
        }
        return ResponseEntity.ok(jobs);
    }
}
