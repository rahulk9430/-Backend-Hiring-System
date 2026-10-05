package com.example.recruitment.repository;

import com.example.recruitment.models.HR;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HRRepository extends JpaRepository<HR, Long> {

      Optional<HR> findByUsername(String username);

}