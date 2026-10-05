package com.example.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_openings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;

    private String department;

    private String location;

    private String experienceRequired;

    private String skillsRequired;

    private String salaryRange;

    @Column(length = 2000)
    private String jobDescription;

    private Integer numberOfPositions;

    private String status;

    @ManyToOne
    @JoinColumn(name = "hr_id")
    private HR hr;
}