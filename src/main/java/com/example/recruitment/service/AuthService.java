package com.example.recruitment.service;

import com.example.recruitment.models.LoginRequest;
import com.example.recruitment.models.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}