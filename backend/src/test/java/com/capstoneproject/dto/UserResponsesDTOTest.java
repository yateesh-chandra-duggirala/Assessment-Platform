package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserResponsesDTOTest {

    @Test
    void testUserResponseDTOGettersAndSetters() {
        Long responseId = 4L;
        Long userId = 6L;
        Long quizId = 5L; 
        Long categoryId = 7L;
        int num = 7;
        int ans = 6;
        int totalMarks = 7;
        int marksScored = 6;
        String timeStamp = "2023-09-2023";

        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setResponseId(responseId);
        userResponsesDto.setUserId(userId);
        userResponsesDto.setQuizId(quizId);
        userResponsesDto.setCategoryId(categoryId);
        userResponsesDto.setNumOfQuestions(num);
        userResponsesDto.setNumOfQuestionsAnswered(ans);
        userResponsesDto.setTotalMarks(totalMarks);
        userResponsesDto.setMarksScored(marksScored);
        userResponsesDto.setTimeStamp(timeStamp);

        assertEquals(responseId, userResponsesDto.getResponseId());
        assertEquals(userId, userResponsesDto.getUserId());
        assertEquals(quizId, userResponsesDto.getQuizId());
        assertEquals(categoryId, userResponsesDto.getCategoryId());
        assertEquals(num, userResponsesDto.getNumOfQuestions());
        assertEquals(ans, userResponsesDto.getNumOfQuestionsAnswered());
        assertEquals(totalMarks, userResponsesDto.getTotalMarks());
        assertEquals(marksScored, userResponsesDto.getMarksScored());
        assertEquals(timeStamp, userResponsesDto.getTimeStamp());
    }

    @Test
    void testConstructor() {
        Long responseId = 4L;
        Long userId = 7L;
        Long quizId = 8L;
        Long categoryId = 9L;
        int num = 7;
        int ans = 6;
        int totalMarks = 7;
        int marksScored = 6;
        String timeStamp = "2023-09-20";

        UserResponsesDTO userResponsesDto = new UserResponsesDTO(4L, 7L, 8L, 9L, 7, 6, 7, 6, "2023-09-20");
        assertEquals(responseId, userResponsesDto.getResponseId());
        assertEquals(userId, userResponsesDto.getUserId());
        assertEquals(quizId,userResponsesDto.getQuizId());
        assertEquals(categoryId, userResponsesDto.getCategoryId());
        assertEquals(num, userResponsesDto.getNumOfQuestions());
        assertEquals(ans, userResponsesDto.getNumOfQuestionsAnswered());
        assertEquals(totalMarks, userResponsesDto.getTotalMarks());
        assertEquals(marksScored, userResponsesDto.getMarksScored());
        assertEquals(timeStamp, userResponsesDto.getTimeStamp());
    }
}
