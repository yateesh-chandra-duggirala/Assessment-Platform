package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserResponsesTest {

    @Test
    public void testGettersAndSetters() {
        Long responseId = 4L;
        User user = new User(1L, "Yateesh");
        Quiz quiz = new Quiz(2L, "Quiz", "Quiz Description", 5, 5);
        int num = 7;
        int ans = 6;
        int totalMarks = 7;
        int marksScored = 6;
        String timeStamp = "2023-09-2023";

        UserResponses userResponses = new UserResponses();
        userResponses.setResponseId(responseId);
        userResponses.setUsers(user);
        userResponses.setQuiz(quiz);
        userResponses.setNumOfQuestions(num);
        userResponses.setNumOfQuestionsAnswered(ans);
        userResponses.setTotalMarks(totalMarks);
        userResponses.setMarksScored(marksScored);
        userResponses.setTimeStamp(timeStamp);

        assertEquals(responseId, userResponses.getResponseId());
        assertEquals(user.getUserId(), userResponses.getUsers().getUserId());
        assertEquals(quiz.getQuizId(), userResponses.getQuiz().getQuizId());
        assertEquals(num, userResponses.getNumOfQuestions());
        assertEquals(ans, userResponses.getNumOfQuestionsAnswered());
        assertEquals(totalMarks, userResponses.getTotalMarks());
        assertEquals(marksScored, userResponses.getMarksScored());
        assertEquals(timeStamp, userResponses.getTimeStamp());
    }

    @Test
    void testConstructor() {
        Long responseId = 4L;
        int num = 7;
        int ans = 6;
        int totalMarks = 7;
        int marksScored = 6;
        String timeStamp = "2023-09-20";

        UserResponses userResponses = new UserResponses(4L, 7, 6, 7, 6, "2023-09-20");
        assertEquals(responseId, userResponses.getResponseId());
        assertEquals(num, userResponses.getNumOfQuestions());
        assertEquals(ans, userResponses.getNumOfQuestionsAnswered());
        assertEquals(totalMarks, userResponses.getTotalMarks());
        assertEquals(marksScored, userResponses.getMarksScored());
        assertEquals(timeStamp, userResponses.getTimeStamp());
    }

    @Test
    void testUserConstructor() {
        User user = new User(4L, "Yateesh");

        UserResponses userResponses = new UserResponses();
        userResponses.setUsers(user);
        assertEquals(user.getUserId(), userResponses.getUsers().getUserId());
        assertEquals(user.getName(), userResponses.getUsers().getName());
    }

    @Test
    void testQuizConstructor() {
        Quiz quiz = new Quiz(4L, "Quiz test", "Quiz Description", 10, 5);

        UserResponses userResponses = new UserResponses();
        userResponses.setQuiz(quiz);
        assertEquals(quiz.getQuizId(), userResponses.getQuiz().getQuizId());
    }
}
