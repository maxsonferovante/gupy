package com.maal.gupy.services;

import com.maal.gupy.domain.job.Job;
import com.maal.gupy.domain.job.JobRepository;
import com.maal.gupy.domain.job.dto.RequestJob;
import com.maal.gupy.domain.job.dto.RequestListJobs;
import jakarta.persistence.EntityExistsException;
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
import java.util.UUID;
import java.util.stream.Collectors;

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
    @DisplayName("Should Create Job When It Does Not Exist")
    void createJobSuccess() throws Exception {

        RequestJob fakeRequestJob = new RequestJob(
                1L, // id
                101L, // companyId
                "Software Engineer", // name
                "Develop and maintain software solutions.", // description
                201L, // careerPageId
                "Tech Careers", // careerPageName
                "https://example.com/logo.png", // careerPageLogo
                "Full-time", // type
                "2023-11-01", // publishedDate
                "2023-12-01", // applicationDeadline
                true, // isRemoteWork
                "San Francisco", // city
                "CA", // state
                "USA", // country
                "https://example.com/job/1", // jobUrl
                false, // disabilities
                "Hybrid", // workplaceType
                "https://example.com/careers" // careerPageUrl
        );
        Job mockJob = new Job(fakeRequestJob);
        mockJob.setIdentifier(UUID.randomUUID());

        when(this.jobRepo.findById(fakeRequestJob.id())).thenReturn(Collections.emptyList());

        when(this.jobRepo.save(any(Job.class))).thenReturn(mockJob);

        UUID requestId = this.jobService.createJob(fakeRequestJob);

        assertNotNull(requestId);
        assertEquals(mockJob.getIdentifier(), requestId);
        verify(this.jobRepo).findById(fakeRequestJob.id());
        verify(this.jobRepo).save(any(Job.class));
    }

    @Test
    @DisplayName("Should Throw Exception When Job Already Exists")
    void createJobThrowsException() {
        RequestJob fakeRequestJob = new RequestJob(
                1L, // id
                101L, // companyId
                "Software Engineer", // name
                "Develop and maintain software solutions.", // description
                201L, // careerPageId
                "Tech Careers", // careerPageName
                "https://example.com/logo.png", // careerPageLogo
                "Full-time", // type
                "2023-11-01", // publishedDate
                "2023-12-01", // applicationDeadline
                true, // isRemoteWork
                "San Francisco", // city
                "CA", // state
                "USA", // country
                "https://example.com/job/1", // jobUrl
                false, // disabilities
                "Hybrid", // workplaceType
                "https://example.com/careers" // careerPageUrl
        );
        Job existionJob = new Job(fakeRequestJob);
        existionJob.setIdentifier(UUID.randomUUID());

        when(this.jobRepo.findById(fakeRequestJob.id())).thenReturn(Collections.singletonList(existionJob));

        assertThrows(EntityExistsException.class, () -> this.jobService.createJob(fakeRequestJob));

        verify(jobRepo).findById(fakeRequestJob.id());
        verify(this.jobRepo, never()).save(any(Job.class));
    }


    @Test
    @DisplayName("Should Create Multiple Jobs Successfully")
    void createBatchJobSuccess() throws Exception {
        // Arrange
        RequestListJobs requestListJobs = new RequestListJobs(List.of(
                new RequestJob(
                        1L, // id
                        101L, // companyId
                        "Software Engineer", // name
                        "Develop and maintain software solutions.", // description
                        201L, // careerPageId
                        "Tech Careers", // careerPageName
                        "https://example.com/logo.png", // careerPageLogo
                        "Full-time", // type
                        "2023-11-01", // publishedDate
                        "2023-12-01", // applicationDeadline
                        true, // isRemoteWork
                        "San Francisco", // city
                        "CA", // state
                        "USA", // country
                        "https://example.com/job/1", // jobUrl
                        false, // disabilities
                        "Hybrid", // workplaceType
                        "https://example.com/careers" // careerPageUrl
                ),
                new RequestJob(
                        1L, // id
                        101L, // companyId
                        "Software Engineer", // name
                        "Develop and maintain software solutions.", // description
                        201L, // careerPageId
                        "Tech Careers", // careerPageName
                        "https://example.com/logo.png", // careerPageLogo
                        "Full-time", // type
                        "2023-11-01", // publishedDate
                        "2023-12-01", // applicationDeadline
                        true, // isRemoteWork
                        "San Francisco", // city
                        "CA", // state
                        "USA", // country
                        "https://example.com/job/1", // jobUrl
                        false, // disabilities
                        "Hybrid", // workplaceType
                        "https://example.com/careers" // careerPageUrl
                )
        ));

        List<Job> mockJobs = requestListJobs.jobs().stream().map(Job::new).collect(Collectors.toList());
        mockJobs.forEach(job -> job.setIdentifier(UUID.randomUUID()));

        when(jobRepo.saveAll(anyList())).thenReturn(mockJobs);

        // Act
        List<UUID> result = jobService.createBatchJob(requestListJobs);

        // Assert
        assertNotNull(result);
        assertEquals(mockJobs.size(), result.size()); // Verifica que todos os IDs foram retornados
        verify(jobRepo).saveAll(anyList()); // Verifica que saveAll foi chamado
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