package com.capstoneproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.User;

/**
 * This interface is extending JPA Repository.
 */
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find User Name by Email and Password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return An Optional User containing the user if found.
     */
    Optional<User> findOneByEmailAndPassword(String email, String password);

    /**
     * Find User Name by Email.
     *
     * @param email The email of the user.
     * @return An User containing the user with email if found.
     */
    @Query("select s from User s where s.email = :email")
    Optional<User> findByEmail(String email);
}
