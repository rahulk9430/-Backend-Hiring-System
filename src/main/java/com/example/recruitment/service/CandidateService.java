package com.example.recruitment.service;

import com.example.recruitment.models.Candidate;

import java.util.List;

public interface CandidateService {

  // Candidate createCandidate(Candidate candidate);

  Candidate registerCandidate(Candidate candidate);

  List<Candidate> getAllCandidates();

  Candidate getCandidateById(Long id);

  Candidate updateCandidate(Long id, Candidate candidate);

  void deleteCandidate(Long id);

  void shortlistCandidate(Long id);

  void rejectCandidate(Long id);

  void selectCandidate(Long id);

  List<Candidate> getCandidatesByStatus(String status);

  Candidate getMyProfile(String username);

  Candidate updateMyProfile(
      String username,
      Candidate candidate);
}