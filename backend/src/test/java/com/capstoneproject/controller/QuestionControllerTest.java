package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.capstoneproject.dto.AssertionQuestionDTO;
import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.QuestionService;

@SuppressWarnings({ "rawtypes" })
class QuestionControllerTest {

    @Mock
    private QuestionService questionService;
    
    @InjectMocks
    private QuestionController questionController;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuestion() {
        List<QuestionDTO> questions = new ArrayList<>();
        questions.add(new QuestionDTO(1L, "Test Question", "A", "B", "C", "D", "OptionB", 4L));
        questions.add(new QuestionDTO(2L, "Test Question 2", "A1", "B2", "C3", "D4", "OptionB", 5L));
        when(questionService.getQuestions()).thenReturn(questions);

        Response response = questionController.getQuestions();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUESTION_FETCH, response.getMessage());

    }

    @Test
    public void testGetQuestionById() {
        Long questionId = 9L;
        QuestionDTO questionDTO = new QuestionDTO(questionId, "Question 1", "A", "B", "C", "D", "OptionB", 4L );
        when(questionService.getQuestionById(questionId)).thenReturn(questionDTO);

        Response response = questionController.getQuestionById(questionId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUESTION_FETCH_BY_ID, response.getMessage());
        assertEquals(questionDTO, response.getBody());

    }

    @Test
    public void testAddQuestion() {
        QuestionDTO questionDto = new QuestionDTO(1L, "Question 1", "A", "B", "C", "D", "OptionB", 4L);
        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);

        Response response = questionController.addQuestion(questionDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(questionDto);
        assertEquals(SuccessMessages.QUESTION_ADD_SUCCESS, response.getMessage());

    }

    @Test
    public void testAddAssertQuestion() {
        AssertionQuestionDTO questionDto = new AssertionQuestionDTO(1L, "Question 1", "A", "B", "B", 4L);
        when(questionService.addAssertionQuestion(questionDto)).thenReturn(questionDto);

        Response response = questionController.addAssertQuestion(questionDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(questionDto);
        assertEquals(SuccessMessages.QUESTION_ADD_SUCCESS, response.getMessage());

    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 3L;
        Response response = questionController.deleteQuestion(questionId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUESTION_DELETE_SUCCESS, response.getMessage());

    }

    @Test
    public void testUpdateQuestion() {
        Long questionId = 8L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO(questionId, "Updated Question", "A", "B", "C", "D", "OptionB", 4L);
        when(questionService.updateQuestion(questionId, updatedQuestionDTO)).thenReturn(updatedQuestionDTO);

        Response response = questionController.updateQuestion(questionId, updatedQuestionDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUESTION_UPDATED_SUCCESS, response.getMessage());

    }

    @Test
    public void testUpdateAssertionQuestion() {
        Long questionId = 8L;
        AssertionQuestionDTO updatedQuestionDTO = new AssertionQuestionDTO(questionId, "Updated Question", "A", "B", "B", 4L);
        when(questionService.updateAssertQuestion(questionId, updatedQuestionDTO)).thenReturn(updatedQuestionDTO);

        Response response = questionController.updateAssertQuestion(questionId, updatedQuestionDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUESTION_UPDATED_SUCCESS, response.getMessage());

    }

    @Test
    public void testQuestionByQuizId() {
        Long questionId = 1L;
        List<QuestionDTO> questions = new ArrayList<>();
        questions.add(new QuestionDTO(1L, "Question 1", "A", "B", "C", "D", "OptionB", 4L));
        questions.add(new QuestionDTO(2L, "Question 2", "A", "B", "C", "D", "OptionB", 5L));
        when(questionService.getQuestionByQuizId(questionId)).thenReturn(questions);

        Response response = questionController.getQuestionByQuizId(questionId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUESTION_FETCH_BY_QUIZ_ID, response.getMessage());

    }
}
