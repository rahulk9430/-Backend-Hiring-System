package com.example.recruitment.service;

import com.example.recruitment.models.HR;

import java.util.List;

public interface HRService {

    HR createHR(HR hr);

    List<HR> getAllHRs();

    HR getHRById(Long id);

    HR updateHR(Long id, HR hr);

    void deleteHR(Long id);

    HR getMyProfile(String username);
}