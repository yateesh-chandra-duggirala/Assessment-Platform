package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizTest {

    @Test
    void testGettersAndSetters() {
        Long quizId = 3L;
        String quizName = "Test Quiz";
        String quizDescription = "Test Quiz Description";
        int numOfQues = 10; 
        
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        quiz.setQuizName(quizName);
        quiz.setQuizDescription(quizDescription);
        quiz.setNumOfQuestions(numOfQues);
        
        assertEquals(quizId, quiz.getQuizId());
        assertEquals(quizName, quiz.getQuizName());
        assertEquals(quizDescription, quiz.getQuizDescription());
        assertEquals(numOfQues, quiz.getNumOfQuestions());
        
    }
    
    @Test
    void testGetQuestionsWhenQuestionsSet() {
        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Test Question", "A", "B", "C", "D", "OptionB"));
        quiz.setQuestions(questions);
        assertEquals(questions, quiz.getQuestions());
    }

    @Test
    void testGetAndSetUserResponses() {
        Quiz quiz = new Quiz();

        List<UserResponses> userResponses = new ArrayList<>();
        userResponses.add(new UserResponses(4L,5, 4, 20, 16, "2023-09-20"));
        userResponses.add(new UserResponses(5L,5, 3, 20, 12, "2023-09-19"));
        quiz.setUserResponses(userResponses);

        List<UserResponses> list = quiz.getUserResponses();
        assertNotSame(userResponses, list);
        assertEquals(userResponses, list);
    }
}
