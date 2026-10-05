package com.example.recruitment.repository;

import com.example.recruitment.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByCandidateId(Long candidateId);

    Optional<Resume> findByCandidateUsername(
        String username
);
}