package com.example.recruitment.service.impl;

import com.example.recruitment.models.JobOpening;
import com.example.recruitment.repository.JobOpeningRepository;
import com.example.recruitment.service.JobOpeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOpeningServiceImpl implements JobOpeningService {

    private final JobOpeningRepository jobOpeningRepository;

    @Override
    public JobOpening createJob(JobOpening job) {

        if (job.getStatus() == null) {
            job.setStatus("OPEN");
        }

        return jobOpeningRepository.save(job);
    }

    @Override
    public List<JobOpening> getAllJobs() {
        return jobOpeningRepository.findAll();
    }

    @Override
    public JobOpening getJobById(Long id) {
        return jobOpeningRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Job not found with id: " + id));
    }

    @Override
    public JobOpening updateJob(Long id, JobOpening job) {

        JobOpening existingJob = getJobById(id);

        existingJob.setJobTitle(job.getJobTitle());
        existingJob.setDepartment(job.getDepartment());
        existingJob.setLocation(job.getLocation());
        existingJob.setExperienceRequired(job.getExperienceRequired());
        existingJob.setSkillsRequired(job.getSkillsRequired());
        existingJob.setSalaryRange(job.getSalaryRange());
        existingJob.setJobDescription(job.getJobDescription());
        existingJob.setNumberOfPositions(job.getNumberOfPositions());

        return jobOpeningRepository.save(existingJob);
    }

    @Override
    public void deleteJob(Long id) {

        JobOpening job = getJobById(id);

        jobOpeningRepository.delete(job);
    }

    @Override
    public void closeJob(Long id) {

        JobOpening job = getJobById(id);

        job.setStatus("CLOSED");

        jobOpeningRepository.save(job);
    }

    @Override
    public void putJobOnHold(Long id) {

        JobOpening job = getJobById(id);

        job.setStatus("ON_HOLD");

        jobOpeningRepository.save(job);
    }

    @Override
    public List<JobOpening> getOpenJobs() {
        return jobOpeningRepository.findByStatus("OPEN");
    }
}