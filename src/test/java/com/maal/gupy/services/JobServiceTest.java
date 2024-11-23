package com.maal.gupy.services;

import com.maal.gupy.domain.job.Job;
import com.maal.gupy.domain.job.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobServiceTest {

    @Mock
    private JobRepository jobRepo;


    @InjectMocks
    private JobService jobService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createJob() {
    }

    @Test
    void createBatchJob() {
    }

    @Test
    void findJobsisRemoteWork() {
    }

    @Test
    void findJobsByDescription() {
    }

    @Test
    void findJobById() {
    }

    @Test
    void findJobByIdentifier() {
    }

    @Test
    @DisplayName("Should Return Empty Page When No Jobs Are Found")
    void getAttlJobs() {

        Pageable mockPageable = PageRequest.of(0, 10);

        Page<Job> emptyPage = new PageImpl<>(Collections.emptyList(), mockPageable, 0);

        when(this.jobRepo.findAll(mockPageable)).thenReturn(emptyPage);

        Page<Job> result = this.jobService.getAttlJobs(mockPageable);

        assertNotNull(result);
        assertEquals(0  , result.getTotalElements());
        verify(this.jobRepo).findAll(mockPageable);
    }

    @Test
    @DisplayName("Should Delete Job When Job Exists")
    void deleteJobById() {
        Long id = 1L;

        Job job = new Job();

        when(this.jobRepo.findById(id)).thenReturn(Collections.singletonList(job));

        this.jobService.deleteJobById(id);

        verify(this.jobRepo).findById(id);
        verify(this.jobRepo).deleteAll(Collections.singletonList(job));
    }

    @Test
    @DisplayName("Should Thorow Exception When Job Does Not Exit")
    void deleteJobByIdThrowsException (){
        Long id = 1L;
        when(this.jobRepo.findById(id)).thenReturn(Collections.emptyList());

        // assertThrows: Verifica que o método lança uma exceção esperada quando o cenário exige isso.
        assertThrows(EntityNotFoundException.class, () -> this.jobService.deleteJobById(id));

        verify(this.jobRepo).findById(id);


        // never(): Garante que o método deleteAll não é chamado quando o Job não é encontrado.
        verify(this.jobRepo, never()).deleteAll(anyList());
    }
}