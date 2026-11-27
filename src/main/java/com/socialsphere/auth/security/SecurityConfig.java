package com.socialsphere.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt cost factor: 12 (higher cost = slower hashing).
        // Higher value means BCrypt takes more time to hash the password.
        return new BCryptPasswordEncoder(12);
    }
}
