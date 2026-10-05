package com.example.recruitment.service.impl;

import com.example.recruitment.models.DashboardResponse;
import com.example.recruitment.repository.CandidateRepository;
import com.example.recruitment.repository.InterviewRepository;
import com.example.recruitment.repository.JobApplicationRepository;
import com.example.recruitment.repository.JobOpeningRepository;
import com.example.recruitment.repository.OfferRepository;
import com.example.recruitment.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CandidateRepository candidateRepository;
    private final JobOpeningRepository jobOpeningRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final InterviewRepository interviewRepository;
    private final OfferRepository offerRepository;

    @Override
    public DashboardResponse getDashboardSummary() {

        return new DashboardResponse(

                // =========================
                // CANDIDATES
                // =========================

                candidateRepository.count(),

                candidateRepository.countByStatus("APPLIED"),

                candidateRepository.countByStatus("SHORTLISTED"),

                candidateRepository.countByStatus("SELECTED"),

                candidateRepository.countByStatus("REJECTED"),


                // =========================
                // JOBS
                // =========================

                jobOpeningRepository.count(),

                jobOpeningRepository.countByStatus("OPEN"),

                jobOpeningRepository.countByStatus("CLOSED"),

                jobOpeningRepository.countByStatus("HOLD"),


                // =========================
                // APPLICATIONS
                // =========================

                jobApplicationRepository.count(),


                // =========================
                // INTERVIEWS
                // =========================

                interviewRepository.count(),

                interviewRepository.countByStatus("SCHEDULED"),

                interviewRepository.countByStatus("COMPLETED"),

                interviewRepository.countByStatus("CANCELLED"),


                // =========================
                // OFFERS
                // =========================

                offerRepository.count(),

                offerRepository.countByStatus("SENT"),

                offerRepository.countByStatus("ACCEPTED"),

                offerRepository.countByStatus("REJECTED")
        );
    }
}