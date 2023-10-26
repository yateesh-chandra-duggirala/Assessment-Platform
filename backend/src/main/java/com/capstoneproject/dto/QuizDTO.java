package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * This class contains the DTO for Quiz.
 */
@Getter
@Setter
public class QuizDTO {

    /**
     * This is quizId variable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    /**
     * This is quizName variable.
     */
    @NotBlank(message = ValidationMessages.QUIZ_NAME_NOTBLANK)
    private String quizName;

    /**
     * This is Quiz Description variable.
     */
    @NotBlank(message = ValidationMessages.QUIZ_DESC_NOTBLANK)
    private String quizDescription;

    /**
     * This field contains the number of Questions variable.
     */
    @NotNull(message = ValidationMessages.QUESTIONS_NOTNULL)
    private int numOfQuestions;

    /**
     * This field contains time in Minutes.
     */
    @NotNull(message = ValidationMessages.TIME_NOTNULL)
    private int timeInMin;

    /**
     * This is the categoryId variable.
     */
    @NotNull(message = ValidationMessages.CATEGORY_ID_NOTNULL)
    private Long categoryId;

    /**
     * This is argument.
     */
    public QuizDTO() {
    }

    /**
     * This is parameterized constructor.
     * @param quizid          of Long type.
     * @param quizname        of String type.
     * @param quizdescription of String type.
     * @param numOfquestions  of Integer type.
     * @param time of integer type.
     * @param categoryid      of Long type.
     */
    public QuizDTO(final Long quizid, final String quizname,
            final String quizdescription, final int numOfquestions,
            final int time, final Long categoryid) {
        this.quizId = quizid;
        this.quizName = quizname;
        this.quizDescription = quizdescription;
        this.numOfQuestions = numOfquestions;
        this.timeInMin = time;
        this.categoryId = categoryid;
    }
}
