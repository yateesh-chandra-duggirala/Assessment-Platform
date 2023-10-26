package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AllResultsDTOTest {

    @Test
    void testGettersAndSetters() {
        Long resultId = 4L;
        Long userId = 5L;
        String email = "test@test.com";
        String userName = "test User";
        String categoryName = "Test Category";
        String quizName = "Test Quiz";
        int numOfQ = 4;
        int numOfQA = 2;
        int tot = 4;
        int mar = 2;
        String timeStamp = "2023-09-20";

        AllResultsDTO allResultsDto = new AllResultsDTO();
        allResultsDto.setResultId(resultId);
        allResultsDto.setUserId(userId);
        allResultsDto.setEmail(email);
        allResultsDto.setUserName(userName);
        allResultsDto.setCategoryName(categoryName);
        allResultsDto.setQuizName(quizName);
        allResultsDto.setNumOfQuestions(numOfQ);
        allResultsDto.setNumOfQuestionsAnswered(numOfQA);
        allResultsDto.setTotalMarks(tot);
        allResultsDto.setMarksScored(mar);
        allResultsDto.setTimeStamp(timeStamp);

        assertEquals(resultId, allResultsDto.getResultId());
        assertEquals(userId, allResultsDto.getUserId());
        assertEquals(email, allResultsDto.getEmail());
        assertEquals(userName, allResultsDto.getUserName());
        assertEquals(categoryName, allResultsDto.getCategoryName());
        assertEquals(quizName, allResultsDto.getQuizName());
        assertEquals(numOfQ, allResultsDto.getNumOfQuestions());
        assertEquals(numOfQA, allResultsDto.getNumOfQuestionsAnswered());
        assertEquals(tot, allResultsDto.getTotalMarks());
        assertEquals(mar, allResultsDto.getMarksScored());
        assertEquals(timeStamp, allResultsDto.getTimeStamp());
    }

    @Test
    void testAllArgsConstructor() {
        Long resultId = 4L;
        Long userId = 5L;
        String email = "test@test.com";
        String userName = "test User";
        String categoryName = "Test Category";
        String quizName = "Test Quiz";
        int numOfQ = 4;
        int numOfQA = 2;
        int tot = 4;
        int mar = 2;
        String timeStamp = "2023-09-20";

        AllResultsDTO allResultsDto = new AllResultsDTO(resultId, userId, email, userName, categoryName,
               quizName, numOfQ, numOfQA, tot, mar, timeStamp);

        assertEquals(resultId, allResultsDto.getResultId());
        assertEquals(userId, allResultsDto.getUserId());
        assertEquals(email, allResultsDto.getEmail());
        assertEquals(userName, allResultsDto.getUserName());
        assertEquals(categoryName, allResultsDto.getCategoryName());
        assertEquals(quizName, allResultsDto.getQuizName());
        assertEquals(numOfQ, allResultsDto.getNumOfQuestions());
        assertEquals(numOfQA, allResultsDto.getNumOfQuestionsAnswered());
        assertEquals(tot, allResultsDto.getTotalMarks());
        assertEquals(mar, allResultsDto.getMarksScored());
        assertEquals(timeStamp, allResultsDto.getTimeStamp());
    }

}
