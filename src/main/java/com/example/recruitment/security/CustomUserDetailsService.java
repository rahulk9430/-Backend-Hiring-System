package com.example.recruitment.security;

import com.example.recruitment.models.Candidate;
import com.example.recruitment.models.HR;
import com.example.recruitment.repository.CandidateRepository;
import com.example.recruitment.repository.HRRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final HRRepository hrRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Check HR
        HR hr = hrRepository
                .findByUsername(username)
                .orElse(null);

        if (hr != null) {

            return new User(
                    hr.getUsername(),
                    hr.getPassword(),
                    Collections.singleton(
                            new SimpleGrantedAuthority(
                                    "ROLE_" + hr.getRole()
                            )
                    )
            );
        }

        // Check Candidate
        Candidate candidate = candidateRepository
                .findByUsername(username)
                .orElse(null);

        if (candidate != null) {

            return new User(
                    candidate.getUsername(),
                    candidate.getPassword(),
                    Collections.singleton(
                            new SimpleGrantedAuthority(
                                    "ROLE_" + candidate.getRole()
                            )
                    )
            );
        }

        throw new UsernameNotFoundException(
                "User not found: " + username
        );
    }
}