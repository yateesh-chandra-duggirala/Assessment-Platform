package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuizDTOTest {

    private QuizDTO quizDto;

    @BeforeEach
    public void setUp() {
        quizDto = new QuizDTO(1L, "Test Quiz", "This is Quiz Description",10, 4, 2L);
    }

    
    @Test
    public void testQuizDTOConstructor() {
        QuizDTO newQuizDto = new QuizDTO();
        assertNull(newQuizDto.getQuizId());
        assertNull(newQuizDto.getQuizName());
        assertNull(newQuizDto.getQuizDescription());
        assertEquals(0,newQuizDto.getNumOfQuestions());
        assertEquals(0, newQuizDto.getTimeInMin());
        assertNull(null);
    }

    @Test
    public void testParametrizedConstructor() {
        assertEquals(1L, quizDto.getQuizId());
        assertEquals("Test Quiz", quizDto.getQuizName());
        assertEquals("This is Quiz Description", quizDto.getQuizDescription());
        assertEquals(10, quizDto.getNumOfQuestions());
        assertEquals(2l, quizDto.getCategoryId());
    }
    
    @Test
    public void testGettersAndSetters() {
        quizDto.setQuizId(3L);
        quizDto.setQuizName("Updated Quiz");
        quizDto.setQuizDescription("Updated Description");
        quizDto.setNumOfQuestions(5);
        quizDto.setTimeInMin(8);
        quizDto.setCategoryId(4L);
        
        assertEquals(3L, quizDto.getQuizId());
        assertEquals("Updated Quiz", quizDto.getQuizName());
        assertEquals("Updated Description", quizDto.getQuizDescription());
        assertEquals(5, quizDto.getNumOfQuestions());
        assertEquals(4L, quizDto.getCategoryId());
        assertEquals(8, quizDto.getTimeInMin());
    }


}
