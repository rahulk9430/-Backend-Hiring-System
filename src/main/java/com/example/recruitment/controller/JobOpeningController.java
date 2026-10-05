package com.example.recruitment.controller;

import com.example.recruitment.models.JobApplication;
import com.example.recruitment.models.JobOpening;
import com.example.recruitment.service.JobApplicationService;
import com.example.recruitment.service.JobOpeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JobOpeningController {

  private final JobOpeningService jobOpeningService;

  private final JobApplicationService jobApplicationService;

  @PostMapping
  public ResponseEntity<JobOpening> createJob(
      @RequestBody JobOpening job) {

    return ResponseEntity.ok(
        jobOpeningService.createJob(job));
  }

  @GetMapping
  public ResponseEntity<List<JobOpening>> getAllJobs() {

    return ResponseEntity.ok(
        jobOpeningService.getAllJobs());
  }

  @PostMapping(value = "/job/{jobId}/apply", consumes = "multipart/form-data")
  public ResponseEntity<JobApplication> applyForJob(

      @PathVariable Long jobId,

      @AuthenticationPrincipal UserDetails userDetails,

      @RequestParam("resume") MultipartFile resume,

      @RequestParam(value = "coverLetter", required = false) String coverLetter

  ) throws IOException {

    JobApplication application = jobApplicationService.applyForJob(

        jobId,

        userDetails.getUsername(),

        resume.getBytes(),

        resume.getOriginalFilename(),

        resume.getContentType(),

        coverLetter);

    return ResponseEntity.ok(application);
  }

  @GetMapping("/{id}")
  public ResponseEntity<JobOpening> getJobById(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        jobOpeningService.getJobById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<JobOpening> updateJob(
      @PathVariable Long id,
      @RequestBody JobOpening job) {

    return ResponseEntity.ok(
        jobOpeningService.updateJob(id, job));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteJob(
      @PathVariable Long id) {

    jobOpeningService.deleteJob(id);

    return ResponseEntity.ok("Job deleted successfully");
  }

  @PutMapping("/{id}/close")
  public ResponseEntity<String> closeJob(
      @PathVariable Long id) {

    jobOpeningService.closeJob(id);

    return ResponseEntity.ok("Job closed successfully");
  }

  @PutMapping("/{id}/hold")
  public ResponseEntity<String> putJobOnHold(
      @PathVariable Long id) {

    jobOpeningService.putJobOnHold(id);

    return ResponseEntity.ok("Job put on hold successfully");
  }

  @GetMapping("/open")
  public ResponseEntity<List<JobOpening>> getOpenJobs() {

    return ResponseEntity.ok(
        jobOpeningService.getOpenJobs());
  }
}