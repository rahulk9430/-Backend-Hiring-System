package com.example.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;

    private Integer technicalRating;

    private Integer communicationRating;

    private Integer problemSolvingRating;

    private Integer overallRating;

    private String recommendation;

    @Column(length = 2000)
    private String comments;
}