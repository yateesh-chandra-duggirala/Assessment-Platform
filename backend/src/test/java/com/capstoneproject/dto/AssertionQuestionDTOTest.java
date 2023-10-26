package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssertionQuestionDTOTest {

    private AssertionQuestionDTO questionDto;
    
    @BeforeEach
    public void setUp() {
        questionDto = new AssertionQuestionDTO(2L, "Test Question", "A", "B", "B", 8L);
    }
    
    @Test
    public void testConstructor() {
        QuestionDTO newQuestionDto = new QuestionDTO();
        assertNull(newQuestionDto.getQuestionId());
        assertNull(newQuestionDto.getQuestionTitle());
        assertNull(newQuestionDto.getOption1());
        assertNull(newQuestionDto.getOption2());
        assertNull(newQuestionDto.getCorrectOption());
    }
    
    @Test
    public void testAllArgsConstructor() {
        assertEquals(2L, questionDto.getQuestionId());
        assertEquals("Test Question", questionDto.getQuestionTitle());
        assertEquals("A", questionDto.getOption1());
        assertEquals("B", questionDto.getOption2());
        assertEquals("B", questionDto.getCorrectOption());
        assertEquals(8L, questionDto.getQuizId());
    }

    @Test
    public void testGettersAndSetters() {
        Long questionId = 2L;
        String questionTitle = "Test Question";
        String option1 = "A1";
        String option2 = "B2";
        String correctOption = "B2";
        Long quizId = 9L;

        questionDto.setQuizId(questionId);
        questionDto.setQuestionTitle(questionTitle);
        questionDto.setOption1(option1);
        questionDto.setOption2(option2);
        questionDto.setCorrectOption(correctOption);
        questionDto.setQuizId(quizId);

        assertEquals(questionId, questionDto.getQuestionId());
        assertEquals(questionTitle, questionDto.getQuestionTitle());
        assertEquals(option1, questionDto.getOption1());
        assertEquals(option2, questionDto.getOption2());
        assertEquals(correctOption, questionDto.getCorrectOption());
        assertEquals(quizId, questionDto.getQuizId());
    }

}
