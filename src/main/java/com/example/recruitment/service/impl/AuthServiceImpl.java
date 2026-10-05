package com.example.recruitment.service.impl;

import com.example.recruitment.models.LoginRequest;
import com.example.recruitment.models.LoginResponse;
import com.example.recruitment.security.JwtService;
import com.example.recruitment.security.CustomUserDetailsService;
import com.example.recruitment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        request.getUsername()
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                userDetails.getPassword())) {

            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        String token =
                jwtService.generateToken(userDetails);

        String role =
                userDetails.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority()
                        .replace("ROLE_", "");

        return new LoginResponse(
                "Login successful",
                userDetails.getUsername(),
                role,
                token
        );
    }
}