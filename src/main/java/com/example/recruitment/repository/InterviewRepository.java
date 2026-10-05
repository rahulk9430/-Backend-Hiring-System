package com.example.recruitment.repository;

import com.example.recruitment.models.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByCandidateId(Long candidateId);

    List<Interview> findByStatus(String status);

    List<Interview> findByCandidateUsername(String username);

    long countByStatus(String status);
}