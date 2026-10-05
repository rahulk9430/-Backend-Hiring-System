package com.example.recruitment.service;

import com.example.recruitment.models.Resume;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

  Resume uploadResume(Long candidateId, MultipartFile file);

  Resume getResumeByCandidateId(Long candidateId);

  byte[] downloadResume(Long candidateId);

  void deleteResume(Long candidateId);

  Resume getMyResume(String username);

}