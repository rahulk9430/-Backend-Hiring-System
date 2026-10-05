package com.example.recruitment.service.impl;

import com.example.recruitment.models.Candidate;
import com.example.recruitment.repository.CandidateRepository;
import com.example.recruitment.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

  private final CandidateRepository candidateRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Candidate registerCandidate(Candidate candidate) {

    // Check username
    if (candidateRepository
        .findByUsername(candidate.getUsername())
        .isPresent()) {

      throw new RuntimeException("Username already exists");
    }

    // Encode password
    candidate.setPassword(
        passwordEncoder.encode(candidate.getPassword()));

    // Automatically assign role
    candidate.setRole("CANDIDATE");

    // Automatically assign initial status
    candidate.setStatus("APPLIED");

    return candidateRepository.save(candidate);
  }

  @Override
  public List<Candidate> getAllCandidates() {
    return candidateRepository.findAll();
  }

  @Override
  public Candidate getCandidateById(Long id) {

    return candidateRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(
            "Candidate not found with id: " + id));
  }

  @Override
  public Candidate updateCandidate(
      Long id,
      Candidate candidate) {

    Candidate existingCandidate = getCandidateById(id);

    existingCandidate.setFirstName(candidate.getFirstName());
    existingCandidate.setLastName(candidate.getLastName());
    existingCandidate.setEmail(candidate.getEmail());
    existingCandidate.setPhone(candidate.getPhone());
    existingCandidate.setExperience(candidate.getExperience());
    existingCandidate.setSkills(candidate.getSkills());
    existingCandidate.setEducation(candidate.getEducation());

    return candidateRepository.save(existingCandidate);
  }

  @Override
  public void deleteCandidate(Long id) {

    Candidate candidate = getCandidateById(id);

    candidateRepository.delete(candidate);
  }

  @Override
  public void shortlistCandidate(Long id) {

    Candidate candidate = getCandidateById(id);

    candidate.setStatus("SHORTLISTED");

    candidateRepository.save(candidate);
  }

  @Override
  public void rejectCandidate(Long id) {

    Candidate candidate = getCandidateById(id);

    candidate.setStatus("REJECTED");

    candidateRepository.save(candidate);
  }

  @Override
  public void selectCandidate(Long id) {

    Candidate candidate = getCandidateById(id);

    candidate.setStatus("SELECTED");

    candidateRepository.save(candidate);
  }

  @Override
  public List<Candidate> getCandidatesByStatus(String status) {

    return candidateRepository.findByStatus(status);
  }

  @Override
  public Candidate getMyProfile(String username) {

    return candidateRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException(
            "Candidate profile not found"));
  }

  @Override
  public Candidate updateMyProfile(
      String username,
      Candidate candidate) {

    Candidate existingCandidate = candidateRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException(
            "Candidate profile not found"));

    existingCandidate.setFirstName(
        candidate.getFirstName());

    existingCandidate.setLastName(
        candidate.getLastName());

    existingCandidate.setEmail(
        candidate.getEmail());

    existingCandidate.setPhone(
        candidate.getPhone());

    existingCandidate.setExperience(
        candidate.getExperience());

    existingCandidate.setSkills(
        candidate.getSkills());

    existingCandidate.setEducation(
        candidate.getEducation());

    return candidateRepository.save(existingCandidate);
  }
}