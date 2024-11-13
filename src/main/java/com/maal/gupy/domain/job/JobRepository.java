package com.maal.gupy.domain.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findAllByisRemoteWork(boolean isRemoteWork);
    List<Job> findAllByDescription(String description);
}
