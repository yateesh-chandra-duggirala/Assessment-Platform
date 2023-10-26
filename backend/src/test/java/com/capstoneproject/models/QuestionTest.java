package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionTest {

    @Test
    void testGettersAndSetters() {
        Long questionId = 4L;
        String questionTitle = "Test Question";
        String option1 = "a";
        String option2 = "b";
        String option3 = "c";
        String option4 = "d";
        String correctOption = "option1";
        
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionTitle(questionTitle);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectOption(correctOption);
        
        assertEquals(questionId, question.getQuestionId());
        assertEquals(questionTitle, question.getQuestionTitle());
        assertEquals(option1, question.getOption1());
        assertEquals(option2, question.getOption2());
        assertEquals(option3, question.getOption3());
        assertEquals(option4, question.getOption4());
        assertEquals(correctOption, question.getCorrectOption());
    }

}

