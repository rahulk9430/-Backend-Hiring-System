package com.example.recruitment.controller;

import com.example.recruitment.models.Candidate;
import com.example.recruitment.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CandidateController {

  private final CandidateService candidateService;

  @PostMapping("/register")
  public ResponseEntity<Candidate> registerCandidate(
      @RequestBody Candidate candidate) {

    return ResponseEntity.ok(
        candidateService.registerCandidate(candidate));
  }

  @GetMapping
  public ResponseEntity<List<Candidate>> getAllCandidates() {

    return ResponseEntity.ok(
        candidateService.getAllCandidates());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Candidate> getCandidateById(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        candidateService.getCandidateById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Candidate> updateCandidate(
      @PathVariable Long id,
      @RequestBody Candidate candidate) {

    return ResponseEntity.ok(
        candidateService.updateCandidate(id, candidate));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCandidate(
      @PathVariable Long id) {

    candidateService.deleteCandidate(id);

    return ResponseEntity.ok("Candidate deleted successfully");
  }

  @PutMapping("/{id}/shortlist")
  public ResponseEntity<String> shortlistCandidate(
      @PathVariable Long id) {

    candidateService.shortlistCandidate(id);

    return ResponseEntity.ok("Candidate shortlisted successfully");
  }

  @PutMapping("/{id}/reject")
  public ResponseEntity<String> rejectCandidate(
      @PathVariable Long id) {

    candidateService.rejectCandidate(id);

    return ResponseEntity.ok("Candidate rejected successfully");
  }

  @PutMapping("/{id}/select")
  public ResponseEntity<String> selectCandidate(
      @PathVariable Long id) {

    candidateService.selectCandidate(id);

    return ResponseEntity.ok("Candidate selected successfully");
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Candidate>> getCandidatesByStatus(
      @PathVariable String status) {

    return ResponseEntity.ok(
        candidateService.getCandidatesByStatus(status));
  }

  @GetMapping("/profile")
  public ResponseEntity<Candidate> getMyProfile(
      @AuthenticationPrincipal UserDetails userDetails) {

    Candidate candidate = candidateService.getMyProfile(
        userDetails.getUsername());

    return ResponseEntity.ok(candidate);
  }
  @PutMapping("/profile")
public ResponseEntity<Candidate> updateMyProfile(
    @AuthenticationPrincipal UserDetails userDetails,
    @RequestBody Candidate candidate) {

    Candidate updatedCandidate =
        candidateService.updateMyProfile(
            userDetails.getUsername(),
            candidate
        );

    return ResponseEntity.ok(updatedCandidate);
}
}