package com.example.recruitment.service.impl;

import com.example.recruitment.models.Candidate;
import com.example.recruitment.models.JobApplication;
import com.example.recruitment.models.JobOpening;
import com.example.recruitment.repository.CandidateRepository;
import com.example.recruitment.repository.JobApplicationRepository;
import com.example.recruitment.repository.JobOpeningRepository;
import com.example.recruitment.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl
                implements JobApplicationService {

        private final JobApplicationRepository applicationRepository;

        private final CandidateRepository candidateRepository;

        private final JobOpeningRepository jobOpeningRepository;

        @Override
        public JobApplication applyForJob(
                        Long jobId,
                        String username,
                        byte[] resumeData,
                        String resumeFileName,
                        String resumeContentType,
                        String coverLetter) {

                Candidate candidate = candidateRepository
                                .findByUsername(username)
                                .orElseThrow(() -> new RuntimeException(
                                                "Candidate not found"));

                JobOpening job = jobOpeningRepository
                                .findById(jobId)
                                .orElseThrow(() -> new RuntimeException(
                                                "Job not found"));

                // Check job status

                if (!"OPEN".equalsIgnoreCase(job.getStatus())) {

                        throw new RuntimeException(
                                        "This job is not open for application");

                }

                // Check duplicate application

                if (applicationRepository
                                .existsByCandidateIdAndJobId(
                                                candidate.getId(),
                                                jobId)) {

                        throw new RuntimeException(
                                        "You have already applied for this job");

                }

                // Validate Resume

                if (resumeData == null ||
                                resumeData.length == 0) {

                        throw new RuntimeException(
                                        "Resume is required");

                }

                // Validate PDF

                if (resumeContentType == null ||
                                !"application/pdf"
                                                .equalsIgnoreCase(
                                                                resumeContentType)) {

                        throw new RuntimeException(
                                        "Only PDF resume is allowed");

                }

                JobApplication application = new JobApplication();

                application.setCandidate(candidate);

                application.setJob(job);

                application.setAppliedAt(
                                LocalDate.now());

                application.setStatus("APPLIED");

                // Resume

                application.setResumeData(
                                resumeData);

                application.setResumeFileName(
                                resumeFileName);

                application.setResumeContentType(
                                resumeContentType);

                // Cover Letter

                application.setCoverLetter(
                                coverLetter);

                return applicationRepository.save(
                                application);
        }

        @Override
        public List<JobApplication> getMyApplications(
                        String username) {

                return applicationRepository
                                .findByCandidateUsername(username);
        }

        @Override
        public List<JobApplication> getApplicationsByJob(
                        Long jobId) {

                return applicationRepository
                                .findByJobId(jobId);
        }
}