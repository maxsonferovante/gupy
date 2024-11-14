package com.maal.gupy.domain.job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findAllByisRemoteWork(boolean isRemoteWork);
    List<Job> findAllByDescription(String description);
    List<Job> findById(Long id);
    List<Job> findByIdentifier(UUID identifier);
    Page<Job> findAll(Pageable pageable);

    @Modifying
    @Query(value = """
        WITH duplicates AS (
                SELECT id, identifier,
                       ROW_NUMBER() OVER (PARTITION BY id ORDER BY identifier) AS row_num
                FROM job
            )
            DELETE FROM job
            WHERE identifier IN (
                SELECT identifier
                FROM duplicates
                WHERE row_num > 1
            )
    """, nativeQuery = true)
    void removeDuplicateJobs();
}
