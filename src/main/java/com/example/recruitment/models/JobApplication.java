package com.example.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
    name = "job_applications",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"candidate_id", "job_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(
        name = "candidate_id",
        nullable = false
    )
    private Candidate candidate;


    @ManyToOne
    @JoinColumn(
        name = "job_id",
        nullable = false
    )
    private JobOpening job;


    private LocalDate appliedAt;


    private String status;


    // =========================
    // Resume
    // =========================

    @Lob
    @Column(
        name = "resume_data",
        columnDefinition = "LONGBLOB"
    )
    private byte[] resumeData;


    private String resumeFileName;


    private String resumeContentType;


    // =========================
    // Application Details
    // =========================

    @Column(
        name = "cover_letter",
        columnDefinition = "TEXT"
    )
    private String coverLetter;
}