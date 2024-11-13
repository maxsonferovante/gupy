package com.maal.gupy.controllers;

import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestFindDescriptionInJob;
import com.maal.gupy.domain.job.dto.RequestJob;
import com.maal.gupy.services.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
    private JobService service;

    @GetMapping("/all")
    public ResponseEntity<?> getJobs() {
        var jobs = service.getAllJobs();
        if (jobs.isEmpty()) {
            return ResponseEntity.ok("No jobs found");
        }
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/remote")
    public ResponseEntity<?> getRemoteJobs() {
        var jobs = service.findJobsisRemoteWork();
        if (jobs.isEmpty()) {
            return ResponseEntity.ok("No remote jobs found");
        }
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createJob(@RequestBody @Valid RequestJob requestJob) {
        var result = service.createJob(requestJob);
        return ResponseEntity.ok("Job created with identifier: " + result);
    }

    @PostMapping("/description")
    public ResponseEntity<?> getJobsByDescription(@RequestParam @Valid RequestFindDescriptionInJob request) {
        var jobs = service.findJobsByDescription(request);
        if (jobs.isEmpty()) {
            return ResponseEntity.ok("No jobs found with description: " + request.description());
        }
        return ResponseEntity.ok(jobs);
    }
}
