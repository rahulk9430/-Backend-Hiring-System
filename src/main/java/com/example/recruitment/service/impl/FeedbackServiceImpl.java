package com.example.recruitment.service.impl;

import com.example.recruitment.models.Feedback;
import com.example.recruitment.repository.FeedbackRepository;
import com.example.recruitment.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback submitFeedback(Feedback feedback) {

        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackByInterviewId(Long interviewId) {

        return feedbackRepository.findByInterviewId(interviewId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Feedback not found for interview: " + interviewId));
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedback) {

        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Feedback not found with id: " + id));

        existingFeedback.setTechnicalRating(feedback.getTechnicalRating());
        existingFeedback.setCommunicationRating(feedback.getCommunicationRating());
        existingFeedback.setProblemSolvingRating(
                feedback.getProblemSolvingRating()
        );
        existingFeedback.setOverallRating(feedback.getOverallRating());
        existingFeedback.setRecommendation(feedback.getRecommendation());
        existingFeedback.setComments(feedback.getComments());

        return feedbackRepository.save(existingFeedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}