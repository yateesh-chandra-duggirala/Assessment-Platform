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

import com.capstoneproject.dto.AssertionQuestionDTO;
import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.QuestionService;

import jakarta.validation.Valid;

/**
 * This class contains the Controller part for the Question.
 */
@CrossOrigin
@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping(path = "question")
public class QuestionController {

    /**
     * questionService variable is used to operate on the Question Service.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Creating instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(QuestionController.class);

    /**
     * Gets all the questions.
     * @return the List of the questions.
     */
    @GetMapping
    public final Response getQuestions() {
        List<QuestionDTO> questions = questionService.getQuestions();
        logger.info(SuccessMessages.QUESTION_FETCH);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_FETCH, questions);
        return response;
    }

    /**
     * Gets the question for the Specific Id.
     * @param questionId of Long type for input.
     * @return the Question associated with a specific ID.
     */
    @GetMapping("/{questionId}")
    public final Response getQuestionById(
            @PathVariable final Long questionId) {
        QuestionDTO newQuestion = questionService.getQuestionById(questionId);
        logger.info(SuccessMessages.QUESTION_FETCH_BY_ID);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_FETCH_BY_ID, newQuestion);
        return response;
    }

    /**
     * adds the questions.
     * @param questionDto of Question type to be requested.
     * @return the success status when added.
     */
    @PostMapping("/mcq")
    public final Response addQuestion(
            @RequestBody @Valid final QuestionDTO questionDto) {
        questionService.addQuestion(questionDto);
        logger.info(SuccessMessages.QUESTION_ADD_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_ADD_SUCCESS);
        return response;
    }

    /**
     * adds the Assert Question.
     * @param questionDto of Question Type.
     * @return the Response.
     */
    @PostMapping("/assert")
    public final Response addAssertQuestion(
            @RequestBody @Valid final AssertionQuestionDTO questionDto) {
        questionService.addAssertionQuestion(questionDto);
        logger.info(SuccessMessages.QUESTION_ADD_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_ADD_SUCCESS);
        return response;
    }

    /**
     * Deletes the question.
     * @param questionId - It is needed to delete the Question of that id.
     * @return the deleted successfully status.
     */
    @DeleteMapping("/{questionId}")
    public final Response deleteQuestion(
            @PathVariable final Long questionId) {
        questionService.deleteQuestion(questionId);
        logger.info(SuccessMessages.QUESTION_DELETE_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_DELETE_SUCCESS);
        return response;
    }

    /**
     * updates the question.
     * @param questionId is required to update its content.
     * @param updatedQuestionDto replace the content.
     * @return the success status after updating.
     */
    @PutMapping("/assert/{questionId}")
    public final Response updateAssertQuestion(
            @PathVariable final Long questionId,
            @RequestBody @Valid final AssertionQuestionDTO updatedQuestionDto) {
        questionService.updateAssertQuestion(questionId,
                updatedQuestionDto);
        logger.info(SuccessMessages.QUESTION_UPDATED_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_UPDATED_SUCCESS);
        return response;
    }

    /**
     * updating the assertion type question.
     * @param questionId of Long type.
     * @param updatedQuestionDto QuestionDTO type.
     * @return Response.
     */
    @PutMapping("/mcq/{questionId}")
    public final Response updateQuestion(
            @PathVariable final Long questionId,
            @RequestBody @Valid final QuestionDTO updatedQuestionDto) {
        questionService.updateQuestion(questionId,
                updatedQuestionDto);
        logger.info(SuccessMessages.QUESTION_UPDATED_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_UPDATED_SUCCESS);
        return response;
    }

    /**
     * This is used to get Question by Quiz Id.
     * @param quizId of Quiz.
     * @return the Response OK.
     */
    @GetMapping("/byQuiz/{quizId}")
    public final Response getQuestionByQuizId(
            @PathVariable final Long quizId) {
        List<QuestionDTO> questionDto = questionService
                .getQuestionByQuizId(quizId);
        logger.info(SuccessMessages.QUESTION_FETCH_BY_QUIZ_ID);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.QUESTION_FETCH_BY_QUIZ_ID, questionDto);
        return response;
    }

}
