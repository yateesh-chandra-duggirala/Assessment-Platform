package com.capstoneproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.capstoneproject.models.AllResults;

/**
 * This is the Repository for All Results.
 */
@EnableJpaRepositories
public interface AllResultsRepository extends JpaRepository<AllResults, Long> {

    /**
     * This Method gets results By User and Quiz.
     * @param userId - Long type
     * @param quizName - String type.
     * @return results with the userId and quizName.
     */
    @Query("select result from AllResults as result where"
            + " result.userId = :userId and result.quizName = :quizName")
    Optional<AllResults> getResultsByUserIdAndQuizName(Long userId,
            String quizName);

    /**
     * This method gets Results By Email.
     * @param userId - Long type.
     * @return the List of All Results.
     */
    @Query("select result from AllResults as result where"
            + " result.userId = :userId")
    List<AllResults> getResultsByUserId(Long userId);
}
