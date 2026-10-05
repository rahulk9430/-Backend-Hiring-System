package com.example.recruitment.service.impl;

import com.example.recruitment.models.Interview;
import com.example.recruitment.repository.InterviewRepository;
import com.example.recruitment.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

  private final InterviewRepository interviewRepository;

  @Override
  public Interview scheduleInterview(Interview interview) {

    interview.setStatus("SCHEDULED");

    return interviewRepository.save(interview);
  }

  @Override
  public List<Interview> getAllInterviews() {
    return interviewRepository.findAll();
  }

  @Override
  public Interview getInterviewById(Long id) {

    return interviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Interview not found with id: " + id));
  }

  @Override
  public Interview updateInterview(Long id, Interview interview) {

    Interview existingInterview = getInterviewById(id);

    existingInterview.setInterviewerName(interview.getInterviewerName());
    existingInterview.setInterviewRound(interview.getInterviewRound());
    existingInterview.setInterviewDate(interview.getInterviewDate());
    existingInterview.setInterviewTime(interview.getInterviewTime());
    existingInterview.setInterviewMode(interview.getInterviewMode());
    existingInterview.setMeetingLink(interview.getMeetingLink());

    return interviewRepository.save(existingInterview);
  }

  @Override
  public void rescheduleInterview(Long id, Interview interview) {

    Interview existingInterview = getInterviewById(id);

    existingInterview.setInterviewDate(interview.getInterviewDate());
    existingInterview.setInterviewTime(interview.getInterviewTime());
    existingInterview.setStatus("RESCHEDULED");

    interviewRepository.save(existingInterview);
  }

  @Override
  public void cancelInterview(Long id) {

    Interview interview = getInterviewById(id);

    interview.setStatus("CANCELLED");

    interviewRepository.save(interview);
  }

  @Override
  public void completeInterview(Long id) {

    Interview interview = getInterviewById(id);

    interview.setStatus("COMPLETED");

    interviewRepository.save(interview);
  }

  @Override
  public List<Interview> getInterviewsByCandidate(Long candidateId) {

    return interviewRepository.findByCandidateId(candidateId);

  }

  @Override
  public List<Interview> getMyInterviews(
      String username) {

    return interviewRepository
        .findByCandidateUsername(username);
  }

}