package com.example.recruitment.service;

import com.example.recruitment.models.JobOpening;

import java.util.List;

public interface JobOpeningService {

    JobOpening createJob(JobOpening job);

    List<JobOpening> getAllJobs();

    JobOpening getJobById(Long id);

    JobOpening updateJob(Long id, JobOpening job);

    void deleteJob(Long id);

    void closeJob(Long id);

    void putJobOnHold(Long id);

    List<JobOpening> getOpenJobs();
}