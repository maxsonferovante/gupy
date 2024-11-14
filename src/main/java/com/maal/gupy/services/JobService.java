package com.maal.gupy.services;


import com.maal.gupy.domain.job.Job;
import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestJob;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }

    public UUID createJob(RequestJob requestJob) throws Exception {
        List<Job> jobsExist = jobRepository.findById(requestJob.id());
        if (jobsExist.isEmpty()) {
            Job job = new Job(requestJob);
            var result = jobRepository.save(job);
            return result.getIdentifier();
        }
        throw new EntityExistsException("Job already exists with id " + jobsExist.getFirst().getIdentifier());


    };

    public List<Job> findJobsisRemoteWork() {
        return jobRepository.findAllByisRemoteWork(true);
    }


    public List<Job> findJobsByDescription(String description) {
        return jobRepository.findAllByDescription(description);
    }

    public List<Job> findJobById(long id) {
        return jobRepository.findById(id);
    }

    public  List<Job> findJobByIdentifier(String identifier) {
        return jobRepository.findByIdentifier(UUID.fromString(identifier));
    }



}
