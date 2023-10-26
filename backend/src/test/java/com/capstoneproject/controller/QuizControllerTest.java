package com.capstoneproject.controller;
import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class QuizControllerTest {
    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuiz() {
        List<QuizDTO> quizzes = new ArrayList<>();
        quizzes.add(new QuizDTO(1L, "Quiz1", "Description1", 5, 2, 1L));
        quizzes.add(new QuizDTO(2L, "Quiz2", "Description2", 10, 4, 2L));
        when(quizService.getQuizzes()).thenReturn(quizzes);

        Response response = quizController.getAllQuiz();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<QuizDTO> responseQuizzes = (List<QuizDTO>) response.getBody();
        assertNotNull(responseQuizzes);
        assertEquals(responseQuizzes, response.getBody());
        assertEquals(2, responseQuizzes.size());
        assertEquals("Quiz1", responseQuizzes.get(0).getQuizName());
    }

    @Test
    public void testGetQuizById() {
        Long quizId = 1L;
        QuizDTO quizDTO = new QuizDTO(quizId, "Quiz1", "Description1", 5, 2, 1L);
        when(quizService.getQuizById(quizId)).thenReturn(quizDTO);

        Response response = quizController.getQuizById(quizId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        QuizDTO responseQuiz = (QuizDTO) response.getBody();
        assertNotNull(responseQuiz);
        assertEquals(responseQuiz, response.getBody());
        assertEquals(quizId, responseQuiz.getQuizId());
        assertEquals("Quiz1", responseQuiz.getQuizName());
    }


    @Test
    public void testAddQuiz() {
        QuizDTO quizDTO = new QuizDTO(1L, "New Quiz", "New Description", 5, 2, 1L);
        when(quizService.addQuiz(quizDTO)).thenReturn(quizDTO);

        Response response = quizController.addQuiz(quizDTO);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(quizDTO);
        assertEquals(SuccessMessages.QUIZ_ADD_SUCCESS, response.getMessage());

    }

    @Test
    public void testDeleteQuiz() {
        Long quizId = 1L;
        Response response = quizController.deleteQuiz(quizId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUIZ_DELETE_SUCCESS, response.getMessage());

    }

    @Test
    public void testUpdateQuiz() {
        Long quizId = 1L;
        QuizDTO updatedQuizDTO = new QuizDTO(quizId, "Updated Quiz", "Updated Description", 10, 2, 1L);
        when(quizService.updateQuiz(quizId, updatedQuizDTO)).thenReturn(updatedQuizDTO);

        Response response = quizController.updateQuiz(quizId, updatedQuizDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.QUIZ_UPDATED_SUCCESS, response.getMessage());
    }


    @Test
    public void testGetQuizByCategoryId() throws Exception{
        Long categoryId = 1L;
        List<QuizDTO> quizList = new ArrayList<>();
        quizList.add(new QuizDTO(1L, "Quiz 1", "Quiz 1 Description", 7, 2, 9L));
        quizList.add(new QuizDTO(2L, "Quiz 2", "Quiz 2 Description", 8, 3, 10L));
        when(quizService.getQuizByCategoryId(categoryId)).thenReturn(quizList);

        Response response = quizController.getQuizByCategoryId(categoryId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<QuizDTO> responseQuizList = (List<QuizDTO>) response.getBody();
        assertNotNull(responseQuizList);
        assertEquals(2, responseQuizList.size());
        assertEquals(responseQuizList, response.getBody());
        assertEquals(SuccessMessages.QUIZ_FETCH_BY_CATEGORY_ID, response.getMessage());
    }
}
