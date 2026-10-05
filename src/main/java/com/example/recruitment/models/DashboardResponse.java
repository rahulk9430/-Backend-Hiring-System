package com.example.recruitment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    // Candidates
    private long totalCandidates;
    private long appliedCandidates;
    private long shortlistedCandidates;
    private long selectedCandidates;
    private long rejectedCandidates;

    // Jobs
    private long totalJobs;
    private long openJobs;
    private long closedJobs;
    private long onHoldJobs;

    // Applications
    private long totalApplications;

    // Interviews
    private long totalInterviews;
    private long scheduledInterviews;
    private long completedInterviews;
    private long cancelledInterviews;

    // Offers
    private long totalOffers;
    private long pendingOffers;
    private long acceptedOffers;
    private long rejectedOffers;

}