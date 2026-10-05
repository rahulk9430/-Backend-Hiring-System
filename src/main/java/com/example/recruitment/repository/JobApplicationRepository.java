package com.example.recruitment.repository;

import com.example.recruitment.models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    boolean existsByCandidateIdAndJobId(
            Long candidateId,
            Long jobId
    );

    List<JobApplication> findByCandidateUsername(
            String username
    );

    List<JobApplication> findByJobId(
            Long jobId
    );

    long countByStatus(String status);
}