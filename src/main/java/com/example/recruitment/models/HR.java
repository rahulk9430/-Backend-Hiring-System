package com.example.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "hrs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Login details
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;

    // HR details
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String department;

    @JsonIgnore
    @OneToMany(mappedBy = "hr")
    private List<JobOpening> jobOpenings;
}