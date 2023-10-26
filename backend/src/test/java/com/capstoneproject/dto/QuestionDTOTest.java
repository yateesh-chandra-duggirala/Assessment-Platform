package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionDTOTest {

    private QuestionDTO questionDto;
    
    @BeforeEach
    public void setUp() {
        questionDto = new QuestionDTO(2L, "Test Question", "A", "B", "C", "D", "OptionB", 8L);
    }
    
    @Test
    public void testConstructor() {
        QuestionDTO newQuestionDto = new QuestionDTO();
        assertNull(newQuestionDto.getQuestionId());
        assertNull(newQuestionDto.getQuestionTitle());
        assertNull(newQuestionDto.getOption1());
        assertNull(newQuestionDto.getOption2());
        assertNull(newQuestionDto.getOption3());
        assertNull(newQuestionDto.getOption4());
        assertNull(newQuestionDto.getCorrectOption());
    }
    
    @Test
    public void testAllArgsConstructor() {
        assertEquals(2L, questionDto.getQuestionId());
        assertEquals("Test Question", questionDto.getQuestionTitle());
        assertEquals("A", questionDto.getOption1());
        assertEquals("B", questionDto.getOption2());
        assertEquals("C", questionDto.getOption3());
        assertEquals("D", questionDto.getOption4());
        assertEquals("OptionB", questionDto.getCorrectOption());
        assertEquals(8L, questionDto.getQuizId());
    }

    @Test
    public void testGettersAndSetters() {
        Long questionId = 2L;
        String questionTitle = "Test Question";
        String option1 = "A1";
        String option2 = "B2";
        String option3 = "C3";
        String option4 = "D4";
        String correctOption = "OptionC";
        Long quizId = 9L;

        questionDto.setQuizId(questionId);
        questionDto.setQuestionTitle(questionTitle);
        questionDto.setOption1(option1);
        questionDto.setOption2(option2);
        questionDto.setOption3(option3);
        questionDto.setOption4(option4);
        questionDto.setCorrectOption(correctOption);
        questionDto.setQuizId(quizId);

        assertEquals(questionId, questionDto.getQuestionId());
        assertEquals(questionTitle, questionDto.getQuestionTitle());
        assertEquals(option1, questionDto.getOption1());
        assertEquals(option2, questionDto.getOption2());
        assertEquals(option3, questionDto.getOption3());
        assertEquals(option4, questionDto.getOption4());
        assertEquals(correctOption, questionDto.getCorrectOption());
        assertEquals(quizId, questionDto.getQuizId());
    }

}
