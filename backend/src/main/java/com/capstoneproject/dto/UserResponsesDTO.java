package com.capstoneproject.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.capstoneproject.response.ValidationMessages;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the DTO for the User Responses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponsesDTO {

    /**
     * This is the Response Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseId;

    /**
     * This is the User Id.
     */
    @NotNull(message = ValidationMessages.USERID_NOTNULL)
    private Long userId;

    /**
     * This is the Quiz Id.
     */
    @NotNull(message = ValidationMessages.QUIZ_ID_NOTNULL)
    private Long quizId;

    /**
     * This is the Category Id.
     */
    @NotNull(message = ValidationMessages.CATEGORY_ID_NOTNULL)
    private Long categoryId;

    /**
     * This is the Number Of Questions.
     */
    @NotNull(message = ValidationMessages.NUM_OF_QUE_NOTNULL)
    private int numOfQuestions;

    /**
     * This is the number of Questions answered.
     */
    @NotNull(message = ValidationMessages.NUM_OF_QUE_ANSWERED_NOTNULL)
    private int numOfQuestionsAnswered;

    /**
     * This is the Total Marks.
     */
    @NotNull(message = ValidationMessages.TOTAL_MARKS_NOTNULL)
    private int totalMarks;

    /**
     * This is the marks scored field.
     */
    @NotNull(message = ValidationMessages.MARKS_SCORED_NOTNULL)
    private int marksScored;

    /**
     * This is the Time Stamp field.
     */
    private String timeStamp;

    /**
     * set time stamp.
     * @return the current time stamp.
     */
    public final String setTimeStamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }

}
