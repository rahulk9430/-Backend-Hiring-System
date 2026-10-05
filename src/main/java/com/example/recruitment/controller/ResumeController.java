package com.example.recruitment.controller;

import com.example.recruitment.models.Resume;
import com.example.recruitment.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ResumeController {

        private final ResumeService resumeService;

        @PostMapping("/upload/{candidateId}")
        public ResponseEntity<Resume> uploadResume(
                        @PathVariable Long candidateId,
                        @RequestParam("file") MultipartFile file) {

                return ResponseEntity.ok(
                                resumeService.uploadResume(candidateId, file));
        }

        @GetMapping("/candidate/{candidateId}")
        public ResponseEntity<Resume> getResume(
                        @PathVariable Long candidateId) {

                return ResponseEntity.ok(
                                resumeService.getResumeByCandidateId(candidateId));
        }

        @GetMapping("/download/{candidateId}")
        public ResponseEntity<byte[]> downloadResume(
                        @PathVariable Long candidateId) {

                Resume resume = resumeService.getResumeByCandidateId(candidateId);

                byte[] file = resumeService.downloadResume(candidateId);

                return ResponseEntity.ok()
                                .contentType(MediaType.parseMediaType(resume.getFileType()))
                                .header(
                                                HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"" + resume.getFileName() + "\"")
                                .body(file);
        }

        @DeleteMapping("/candidate/{candidateId}")
        public ResponseEntity<String> deleteResume(
                        @PathVariable Long candidateId) {

                resumeService.deleteResume(candidateId);

                return ResponseEntity.ok("Resume deleted successfully");
        }
}