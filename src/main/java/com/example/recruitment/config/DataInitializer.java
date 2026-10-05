package com.example.recruitment.config;

import com.example.recruitment.models.HR;
import com.example.recruitment.repository.HRRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final HRRepository hrRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createDefaultHR() {

        return args -> {

            if (hrRepository.count() == 0) {

                HR hr = new HR();

                hr.setUsername("admin");
                hr.setPassword(
                        passwordEncoder.encode("admin123")
                );
                hr.setRole("HR");

                hr.setName("System Admin");
                hr.setEmail("admin@recruitment.com");
                hr.setPhone("9999999999");
                hr.setDepartment("Human Resources");

                hrRepository.save(hr);

                System.out.println(
                        "Default HR created successfully!"
                );
            }
        };
    }
}