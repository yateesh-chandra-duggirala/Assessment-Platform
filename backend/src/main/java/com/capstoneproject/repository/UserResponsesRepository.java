package com.capstoneproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.UserResponses;

/**
 * This is The Repository for User Responses.
 */
@EnableJpaRepositories
public interface UserResponsesRepository
        extends JpaRepository<UserResponses, Long> {
}
