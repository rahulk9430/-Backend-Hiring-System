package com.example.recruitment.service.impl;

import com.example.recruitment.models.Candidate;
import com.example.recruitment.models.Resume;
import com.example.recruitment.repository.CandidateRepository;
import com.example.recruitment.repository.ResumeRepository;
import com.example.recruitment.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

  private final ResumeRepository resumeRepository;
  private final CandidateRepository candidateRepository;

  private final String uploadDirectory = "uploads/resumes/";

  @Override
  public Resume getMyResume(String username) {

    return resumeRepository
        .findByCandidateUsername(username)
        .orElseThrow(() -> new RuntimeException(
            "Resume not found"));
  }

  @Override
  public Resume uploadResume(Long candidateId, MultipartFile file) {

    Candidate candidate = candidateRepository.findById(candidateId)
        .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));

    try {

      Path directory = Paths.get(uploadDirectory);

      if (!Files.exists(directory)) {
        Files.createDirectories(directory);
      }

      String fileName = System.currentTimeMillis()
          + "_" + file.getOriginalFilename();

      Path filePath = directory.resolve(fileName);

      Files.copy(file.getInputStream(), filePath);

      Resume resume = resumeRepository
          .findByCandidateId(candidateId)
          .orElse(new Resume());

      resume.setFileName(file.getOriginalFilename());
      resume.setFilePath(filePath.toString());
      resume.setFileType(file.getContentType());
      resume.setCandidate(candidate);

      return resumeRepository.save(resume);

    } catch (IOException e) {
      throw new RuntimeException("Failed to upload resume", e);
    }
  }

  @Override
  public Resume getResumeByCandidateId(Long candidateId) {

    return resumeRepository.findByCandidateId(candidateId)
        .orElseThrow(() -> new RuntimeException("Resume not found for candidate: " + candidateId));
  }

  @Override
  public byte[] downloadResume(Long candidateId) {

    Resume resume = getResumeByCandidateId(candidateId);

    try {
      Path path = Paths.get(resume.getFilePath());

      return Files.readAllBytes(path);

    } catch (IOException e) {
      throw new RuntimeException("Failed to download resume", e);
    }
  }

  @Override
  public void deleteResume(Long candidateId) {

    Resume resume = getResumeByCandidateId(candidateId);

    try {

      Files.deleteIfExists(Paths.get(resume.getFilePath()));

      resumeRepository.delete(resume);

    } catch (IOException e) {
      throw new RuntimeException("Failed to delete resume", e);
    }
  }
}