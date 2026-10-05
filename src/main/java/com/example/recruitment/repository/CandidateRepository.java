package com.example.recruitment.repository;

import com.example.recruitment.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

  Optional<Candidate> findByUsername(String username);

  List<Candidate> findByStatus(String status);

  long countByStatus(String status);
}