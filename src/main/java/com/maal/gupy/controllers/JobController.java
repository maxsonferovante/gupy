package com.maal.gupy.controllers;

import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestFindDescriptionInJob;
import com.maal.gupy.domain.job.dto.RequestJob;
import com.maal.gupy.services.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
    private JobService service;



    @GetMapping("/all")
    public ResponseEntity<?> getJobs() {
        return ResponseEntity.ok(service.getAllJobs());
    }

    @GetMapping("/remote")
    public ResponseEntity<?> getRemoteJobs() {
        return ResponseEntity.ok(service.findJobsisRemoteWork());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createJob(@RequestBody @Valid RequestJob requestJob) throws Exception {
        var result = service.createJob(requestJob);
        return ResponseEntity.ok("Job created with identifier: " + result);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<?> getJobsByDescription(@PathVariable String description) {
        return ResponseEntity.ok(service.findJobsByDescription(description));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobsById(@PathVariable long id) {
        return ResponseEntity.ok(
                service.findJobById(id)
        );
    }

    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<?> getJobsById(@PathVariable String identifier) {
        return ResponseEntity.ok(service.findJobByIdentifier(identifier));
    }
}
