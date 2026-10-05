package com.example.recruitment.controller;

import com.example.recruitment.models.Feedback;
import com.example.recruitment.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@CrossOrigin("*")

public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(
            @RequestBody Feedback feedback) {

        return ResponseEntity.ok(
                feedbackService.submitFeedback(feedback)
        );
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {

        return ResponseEntity.ok(
                feedbackService.getAllFeedback()
        );
    }

    @GetMapping("/interview/{interviewId}")
    public ResponseEntity<Feedback> getFeedbackByInterview(
            @PathVariable Long interviewId) {

        return ResponseEntity.ok(
                feedbackService.getFeedbackByInterviewId(interviewId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(
            @PathVariable Long id,
            @RequestBody Feedback feedback) {

        return ResponseEntity.ok(
                feedbackService.updateFeedback(id, feedback)
        );
    }
}