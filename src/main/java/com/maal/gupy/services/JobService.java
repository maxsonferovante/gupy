package com.maal.gupy.services;


import com.maal.gupy.domain.job.Job;
import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestJob;
import com.maal.gupy.domain.job.dto.RequestListJobs;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public UUID createJob(RequestJob requestJob) throws Exception {
        List<Job> jobsExist = jobRepository.findById(requestJob.id());
        if (jobsExist.isEmpty()) {
            Job job = new Job(requestJob);
            var result = jobRepository.save(job);
            return result.getIdentifier();
        }
        throw new EntityExistsException();
    };

    public List<UUID> createBatchJob(RequestListJobs requestListJobs) throws Exception {
        if (requestListJobs.jobs().isEmpty()) {
            throw new Exception("Job list cannot be empty");
        }

        List<Job> jobs = requestListJobs.jobs().stream().map(Job::new).collect(Collectors.toList());

        List<Job> savedJobs = jobRepository.saveAll(jobs);

        return savedJobs.stream()
                .map(Job::getIdentifier)
                .collect(Collectors.toList());
    }

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

//    @Transactional
    public Page<Job> getAttlJobs(Pageable pageable) {
//        jobRepository.removeDuplicateJobs();
        return jobRepository.findAll(pageable);
    }


    public void deleteJobById(Long id) {
        List<Job> job = jobRepository.findById(id);
        if (job.isEmpty()) {
            throw new EntityNotFoundException();
        }
        jobRepository.deleteAll(job);
    }
}
