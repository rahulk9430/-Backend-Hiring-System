package com.example.recruitment.controller;

import com.example.recruitment.models.Interview;
import com.example.recruitment.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
@CrossOrigin("*")

public class InterviewController {

  private final InterviewService interviewService;

  @PostMapping
  public ResponseEntity<Interview> scheduleInterview(
      @RequestBody Interview interview) {

    return ResponseEntity.ok(
        interviewService.scheduleInterview(interview));
  }

  @GetMapping
  public ResponseEntity<List<Interview>> getAllInterviews() {

    return ResponseEntity.ok(
        interviewService.getAllInterviews());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Interview> getInterviewById(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        interviewService.getInterviewById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Interview> updateInterview(
      @PathVariable Long id,
      @RequestBody Interview interview) {

    return ResponseEntity.ok(
        interviewService.updateInterview(id, interview));
  }

  @PutMapping("/{id}/reschedule")
  public ResponseEntity<String> rescheduleInterview(
      @PathVariable Long id,
      @RequestBody Interview interview) {

    interviewService.rescheduleInterview(id, interview);

    return ResponseEntity.ok(
        "Interview rescheduled successfully");
  }

  @PutMapping("/{id}/cancel")
  public ResponseEntity<String> cancelInterview(
      @PathVariable Long id) {

    interviewService.cancelInterview(id);

    return ResponseEntity.ok(
        "Interview cancelled successfully");
  }

  @PutMapping("/{id}/complete")
  public ResponseEntity<String> completeInterview(
      @PathVariable Long id) {

    interviewService.completeInterview(id);

    return ResponseEntity.ok(
        "Interview completed successfully");
  }

  @GetMapping("/candidate/{candidateId}")
  public ResponseEntity<List<Interview>> getInterviewsByCandidate(
      @PathVariable Long candidateId) {

    return ResponseEntity.ok(
        interviewService.getInterviewsByCandidate(candidateId));
  }

  @GetMapping("/my")
  public ResponseEntity<List<Interview>> getMyInterviews(
      @AuthenticationPrincipal UserDetails userDetails) {

    return ResponseEntity.ok(
        interviewService.getMyInterviews(
            userDetails.getUsername()));
  }
}