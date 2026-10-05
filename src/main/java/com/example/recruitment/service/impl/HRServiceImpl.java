package com.example.recruitment.service.impl;

import com.example.recruitment.models.HR;
import com.example.recruitment.repository.HRRepository;
import com.example.recruitment.service.HRService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HRServiceImpl implements HRService {

  private final HRRepository hrRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public HR createHR(HR hr) {

    // Check username
    if (hrRepository.findByUsername(hr.getUsername()).isPresent()) {
      throw new RuntimeException("Username already exists");
    }

    // Encode password
    hr.setPassword(
        passwordEncoder.encode(hr.getPassword()));

    // Automatically assign HR role
    hr.setRole("HR");

    return hrRepository.save(hr);
  }

  @Override
  public List<HR> getAllHRs() {
    return hrRepository.findAll();
  }

  @Override
  public HR getHRById(Long id) {

    return hrRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(
            "HR not found with id: " + id));
  }

  @Override
  public HR updateHR(Long id, HR hr) {

    HR existingHR = getHRById(id);

    existingHR.setName(hr.getName());
    existingHR.setEmail(hr.getEmail());
    existingHR.setPhone(hr.getPhone());
    existingHR.setDepartment(hr.getDepartment());

    return hrRepository.save(existingHR);
  }

  @Override
  public void deleteHR(Long id) {

    HR existingHR = getHRById(id);

    hrRepository.delete(existingHR);
  }

  @Override
  public HR getMyProfile(String username) {

    return hrRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException(
            "HR profile not found"));
  }
}