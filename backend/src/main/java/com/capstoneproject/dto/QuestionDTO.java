package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the DTOs for Quiz.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDTO {

    /**
     * This is question Id field that is auto generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    /**
     * This is Question Title Field.
     */
    @NotBlank(message = ValidationMessages.QUESTION_TITLE_NOTBLANK)
    private String questionTitle;

    /**
     * This is Option 1 field.
     */
    @NotBlank(message = ValidationMessages.OPTION1_NOTBLANK)
    private String option1;

    /**
     * This is Option 2 field.
     */
    @NotBlank(message = ValidationMessages.OPTION2_NOTBLANK)
    private String option2;

    /**
     * This is Option 3 field.
     */
    @NotBlank(message = ValidationMessages.OPTION3_NOTBLANK)
    private String option3;

    /**
     * This is Option 4 field.
     */
    @NotBlank(message = ValidationMessages.OPTION4_NOTBLANK)
    private String option4;

    /**
     * This is Correct Option field.
     */
    @NotBlank(message = ValidationMessages.CORRECT_OPTION_NOTBLANK)
    private String correctOption;

    /**
     * This is Quiz Id from the Quiz.
     */
    @NotNull(message = ValidationMessages.QUIZ_ID_NOTNULL)
    private Long quizId;

}
