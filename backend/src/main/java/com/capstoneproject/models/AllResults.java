package com.capstoneproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is All Results Entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllResults {

    /**
     * This is the Result Id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;

    /**
     * This is the user Id field.
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * This is the User Email field.
     */
    @Column(nullable = false)
    private String email;

    /**
     * This is the User Name field.
     */
    @Column(nullable = false)
    private String userName;

    /**
     * This is the Category Name.
     */
    @Column(nullable = false)
    private String categoryName;

    /**
     * This is the Quiz name.
     */
    @Column(nullable = false)
    private String quizName;

    /**
     * This is the Number of Questions field.
     */
    @Column(nullable = false)
    private int numOfQuestions;

    /**
     * This is the number of Questions Answered Field.
     */
    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    /**
     * This is the Total Marks field.
     */
    @Column(nullable = false)
    private int totalMarks;

    /**
     * This is the Marks Scored field.
     */
    @Column(nullable = false)
    private int marksScored;

    /**
     * This is the Time stamp field.
     */
    @Column(nullable = false)
    private String timeStamp;

}
