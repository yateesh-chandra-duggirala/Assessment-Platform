package com.capstoneproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class contains the Bean to Password Encoder.
 */
@Configuration
public class SecurityConfig {

    /**
     * This method contains the Bean that stored BCryptPasswordEncoder.
     *
     * @return the encrypted password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
