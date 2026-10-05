package com.example.recruitment.controller;

import com.example.recruitment.models.HR;
import com.example.recruitment.service.HRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hrs")
@RequiredArgsConstructor
@CrossOrigin("*")

public class HRController {

  private final HRService hrService;

  @PostMapping
  public ResponseEntity<HR> createHR(@RequestBody HR hr) {
    return ResponseEntity.ok(hrService.createHR(hr));
  }

  @GetMapping
  public ResponseEntity<List<HR>> getAllHRs() {
    return ResponseEntity.ok(hrService.getAllHRs());
  }

  @GetMapping("/{id}")
  public ResponseEntity<HR> getHRById(@PathVariable Long id) {
    return ResponseEntity.ok(hrService.getHRById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<HR> updateHR(
      @PathVariable Long id,
      @RequestBody HR hr) {

    return ResponseEntity.ok(hrService.updateHR(id, hr));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteHR(@PathVariable Long id) {

    hrService.deleteHR(id);

    return ResponseEntity.ok("HR deleted successfully");
  }

  @GetMapping("/profile")
  public ResponseEntity<HR> getMyProfile(
      @AuthenticationPrincipal UserDetails userDetails) {

    HR hr = hrService.getMyProfile(
        userDetails.getUsername());

    return ResponseEntity.ok(hr);
  }


  
}