package com.capstoneproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.capstoneproject.models.Question;

/**
 * This interface contains the JPA Repository.
 */
@EnableJpaRepositories
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /**
     * This is used to get the Question provided by Id.
     * @param quizId from Quiz to query.
     * @return the questions of that Quiz Id.
     */
    @Query("select question from Question as question where"
            + " question.quiz.quizId = :quizId")
    List<Question> getQuestionByQuizId(@Param("quizId") Long quizId);
}
