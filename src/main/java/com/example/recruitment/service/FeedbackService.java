package com.example.recruitment.service;

import com.example.recruitment.models.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback submitFeedback(Feedback feedback);

    Feedback getFeedbackByInterviewId(Long interviewId);

    Feedback updateFeedback(Long id, Feedback feedback);

    List<Feedback> getAllFeedback();
}