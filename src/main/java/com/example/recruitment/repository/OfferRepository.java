package com.example.recruitment.repository;

import com.example.recruitment.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findByCandidateId(Long candidateId);

    Optional<Offer> findByCandidateUsername(
            String username);

    long countByStatus(String status);
}