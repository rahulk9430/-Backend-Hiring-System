package com.example.recruitment.repository;

import com.example.recruitment.models.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {

    List<JobOpening> findByStatus(String status);

    long countByStatus(String status);
}