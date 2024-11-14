package com.maal.gupy.controllers;

import com.maal.gupy.domain.job.Job;
import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestFindDescriptionInJob;
import com.maal.gupy.domain.job.dto.RequestJob;
import com.maal.gupy.domain.job.dto.RequestListJobs;
import com.maal.gupy.services.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {


    @Autowired
    private JobService service;



    @GetMapping("/all")
    public ResponseEntity<Page<Job>> getJobs(Pageable pageable) {
        //    /jobs/all?page=1&size=5&sort=name,asc

        Page<Job> jobs = service.getAttlJobs(pageable);
        return ResponseEntity.ok(jobs);
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

    @PostMapping("/create/batch")
    public ResponseEntity<?> createBatchJobs(@RequestBody @Valid RequestListJobs listJobs) throws Exception {
        return  ResponseEntity.ok(service.createBatchJob(listJobs));
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
