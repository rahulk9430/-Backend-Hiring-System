package com.example.recruitment.service;

import com.example.recruitment.models.Interview;

import java.util.List;

public interface InterviewService {

    Interview scheduleInterview(Interview interview);

    List<Interview> getAllInterviews();

    Interview getInterviewById(Long id);

    Interview updateInterview(Long id, Interview interview);

    void rescheduleInterview(Long id, Interview interview);

    void cancelInterview(Long id);

    void completeInterview(Long id);

    List<Interview> getInterviewsByCandidate(Long candidateId);

    List<Interview> getMyInterviews(String username);
}