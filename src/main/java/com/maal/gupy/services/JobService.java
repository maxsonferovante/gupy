package com.maal.gupy.services;


import com.maal.gupy.domain.job.Job;
import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestFindDescriptionInJob;
import com.maal.gupy.domain.job.dto.RequestJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        var jobs = jobRepository.findAll();
        return jobs;
    }

    public String createJob(RequestJob requestJob) {
        Job job = new Job(requestJob);
        var result = jobRepository.save(job);
        return result.getIdentifier();
    };

    public List<Job> findJobsisRemoteWork() {
        var jobs = jobRepository.findAllByisRemoteWork(true);
        return jobs;
    }


    public List<Job> findJobsByDescription(RequestFindDescriptionInJob requestFindDescriptionInJob) {
        var jobs = jobRepository.findAllByDescription(requestFindDescriptionInJob.description());
        return jobs;
    }
}
