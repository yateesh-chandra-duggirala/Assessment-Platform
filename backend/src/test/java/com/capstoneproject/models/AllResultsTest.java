package com.capstoneproject.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class AllResultsTest {

    @Test
    void testGetterSetter() {
        AllResults allResults = new AllResults();
        allResults.setResultId(1L);
        allResults.setUserId(2L);
        allResults.setEmail("test@example.com");
        allResults.setUserName("TestUser");
        allResults.setCategoryName("TestCategory");
        allResults.setQuizName("TestQuiz");
        allResults.setNumOfQuestions(10);
        allResults.setNumOfQuestionsAnswered(8);
        allResults.setTotalMarks(20);
        allResults.setMarksScored(15);
        allResults.setTimeStamp("2023-09-28 15:30:00");

        assertEquals(1L, allResults.getResultId());
        assertEquals(2L, allResults.getUserId());
        assertEquals("test@example.com", allResults.getEmail());
        assertEquals("TestUser", allResults.getUserName());
        assertEquals("TestCategory", allResults.getCategoryName());
        assertEquals("TestQuiz", allResults.getQuizName());
        assertEquals(10, allResults.getNumOfQuestions());
        assertEquals(8, allResults.getNumOfQuestionsAnswered());
        assertEquals(20, allResults.getTotalMarks());
        assertEquals(15, allResults.getMarksScored());
        assertEquals("2023-09-28 15:30:00", allResults.getTimeStamp());
    }

    @Test
    void testNoArgsConstructor() {
        AllResults allResults = new AllResults();

        assertNotNull(allResults);
        assertEquals(null, allResults.getResultId());
        assertEquals(null, allResults.getUserId());
        assertEquals(null, allResults.getEmail());
        assertEquals(null, allResults.getUserName());
        assertEquals(null, allResults.getCategoryName());
        assertEquals(null, allResults.getQuizName());
        assertEquals(0, allResults.getNumOfQuestions());
        assertEquals(0, allResults.getNumOfQuestionsAnswered());
        assertEquals(0, allResults.getTotalMarks());
        assertEquals(0, allResults.getMarksScored());
        assertEquals(null, allResults.getTimeStamp());
    }

    @Test
    void testAllArgsConstructor() {
        AllResults allResults = new AllResults(1L, 2L, "test@example.com", "TestUser",
                "TestCategory", "TestQuiz", 10, 8, 20, 15, "2023-09-28 15:30:00");

        assertNotNull(allResults);
        assertEquals(1L, allResults.getResultId());
        assertEquals(2L, allResults.getUserId());
        assertEquals("test@example.com", allResults.getEmail());
        assertEquals("TestUser", allResults.getUserName());
        assertEquals("TestCategory", allResults.getCategoryName());
        assertEquals("TestQuiz", allResults.getQuizName());
        assertEquals(10, allResults.getNumOfQuestions());
        assertEquals(8, allResults.getNumOfQuestionsAnswered());
        assertEquals(20, allResults.getTotalMarks());
        assertEquals(15, allResults.getMarksScored());
        assertEquals("2023-09-28 15:30:00", allResults.getTimeStamp());
    }
}