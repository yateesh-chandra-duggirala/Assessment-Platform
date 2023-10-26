package com.capstoneproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.QuizDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.QuizService;

import jakarta.validation.Valid;

/**
 * This class contains the Controller part of Quiz.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "quiz")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class QuizController {
    /**
     * quizService variable is used to perform the operations on Quiz Service.
     */
    @Autowired
    private QuizService quizService;

    /**
     * Create an instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(QuizController.class);

    /**
     * get all the Quiz.
     * @return the List of the quiz.
     */
    @GetMapping
    public final Response getAllQuiz() {
        List<QuizDTO> quiz = quizService.getQuizzes();
        logger.info(SuccessMessages.QUIZ_FETCH);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUIZ_FETCH, quiz);
        return response;
    }

    /**
     * Get the quiz based on ID.
     * @param quizId of Long type as path variable.
     * @return the status whether the quiz exist or not.
     */
    @GetMapping("/{quizId}")
    public final Response getQuizById(
            @PathVariable final Long quizId) {
        QuizDTO newQuiz = quizService.getQuizById(quizId);
        logger.info(SuccessMessages.QUIZ_FETCH_BY_ID);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUIZ_FETCH_BY_ID, newQuiz);
        return response;
    }

    /**
     * add quiz to the Repository.
     * @param quizDto of Quiz type is requested.
     * @return newQuiz.
     */
    @PostMapping
    public final Response addQuiz(
            @RequestBody @Valid final QuizDTO quizDto) {
        quizService.addQuiz(quizDto);
        logger.info(SuccessMessages.QUIZ_ADD_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUIZ_ADD_SUCCESS);
        return response;
    }

    /**
     * Deletes the quiz based on the id entered.
     * @param quizId of Long type is entered.
     * @return null if the quiz does not exist.
     */
    @DeleteMapping("/{quizId}")
    public final Response deleteQuiz(
            @PathVariable final Long quizId) {
        quizService.deleteQuiz(quizId);
        logger.info(SuccessMessages.QUIZ_DELETE_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUIZ_DELETE_SUCCESS);
        return response;
    }

    /**
     * Updates the quiz based on the id entered.
     * @param quizId of Long is considered to update that Quiz.
     * @param updatedQuiz to be updated.
     * @return the updated quiz.
     */
    @PutMapping("/{quizId}")
    public final Response updateQuiz(
            @PathVariable final Long quizId,
            @RequestBody @Valid final QuizDTO updatedQuiz) {
        quizService.updateQuiz(quizId, updatedQuiz);
        logger.info(SuccessMessages.QUIZ_UPDATED_SUCCESS);
       Response response = new Response(HttpStatus.OK.value(),
               SuccessMessages.QUIZ_UPDATED_SUCCESS);
        return response;
    }

    /**
     * Gets quiz based on the entered categoryId.
     * @param categoryId is to be entered to get the quiz associated.
     * @return the quiz of that Category Id if found.
     */
    @GetMapping("/byCategory/{categoryId}")
    public final Response getQuizByCategoryId(
            @PathVariable final Long categoryId) {
        List<QuizDTO> quizDto = quizService.getQuizByCategoryId(categoryId);
        logger.info(SuccessMessages.QUIZ_FETCH_BY_CATEGORY_ID);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUIZ_FETCH_BY_CATEGORY_ID, quizDto);
        return response;
    }
}
