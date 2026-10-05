package com.example.recruitment.controller;

import com.example.recruitment.models.JobApplication;
import com.example.recruitment.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin("*")

public class JobApplicationController {

        private final JobApplicationService jobApplicationService;

        // Candidate
        @GetMapping("/my")
        public ResponseEntity<List<JobApplication>> getMyApplications(
                        @AuthenticationPrincipal UserDetails userDetails) {

                return ResponseEntity.ok(
                                jobApplicationService.getMyApplications(
                                                userDetails.getUsername()));
        }

        // HR
        @GetMapping("/job/{jobId}")
        public ResponseEntity<List<JobApplication>> getApplicationsByJob(
                        @PathVariable Long jobId) {

                return ResponseEntity.ok(
                                jobApplicationService.getApplicationsByJob(jobId));
        }

        @PostMapping(value = "/job/{jobId}/apply", consumes = "multipart/form-data")
        public ResponseEntity<JobApplication> applyForJob(

                        @PathVariable Long jobId,

                        @AuthenticationPrincipal UserDetails userDetails,

                        @RequestParam("resume") MultipartFile resume,

                        @RequestParam(value = "coverLetter", required = false) String coverLetter) throws IOException {

                JobApplication application = jobApplicationService.applyForJob(

                                jobId,

                                userDetails.getUsername(),

                                resume.getBytes(),

                                resume.getOriginalFilename(),

                                resume.getContentType(),

                                coverLetter);

                return ResponseEntity.ok(application);
        }
}