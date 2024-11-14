package com.maal.gupy.domain.job;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findAllByisRemoteWork(boolean isRemoteWork);
    List<Job> findAllByDescription(String description);
    List<Job> findById(Long id);
    List<Job> findByIdentifier(UUID identifier);
}
