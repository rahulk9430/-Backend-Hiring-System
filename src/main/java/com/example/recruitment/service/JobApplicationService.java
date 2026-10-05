package com.example.recruitment.service;

import com.example.recruitment.models.JobApplication;

import java.util.List;

public interface JobApplicationService {

    JobApplication applyForJob(
            Long jobId,
            String username,
            byte[] resumeData,
            String resumeFileName,
            String resumeContentType,
            String coverLetter
    );

    List<JobApplication> getMyApplications(
            String username
    );

    List<JobApplication> getApplicationsByJob(
            Long jobId
    );
}