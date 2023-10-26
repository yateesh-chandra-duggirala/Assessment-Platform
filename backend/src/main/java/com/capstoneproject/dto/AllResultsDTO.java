package com.capstoneproject.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the DTO for All Results.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllResultsDTO {

    /**
     * This is the result Id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;

    /**
     * This is the User Id field.
     */
    private Long userId;

    /**
     * This is the email field.
     */
    private String email;

    /**
     * This is the UserName Field.
     */
    private String userName;

    /**
     * This is the category name field.
     */
    private String categoryName;

    /**
     * This is the Quiz Name field.
     */
    private String quizName;

    /**
     * This is the number of Questions field.
     */
    private int numOfQuestions;

    /**
     * This is the Number of Questions Answered field.
     */
    private int numOfQuestionsAnswered;

    /**
     * This is the Total Marks field.
     */
    private int totalMarks;

    /**
     * This is the marks scored field.
     */
    private int marksScored;

    /**
     * This is the Time Stamp field.
     */
    private String timeStamp;
}
