package com.capstoneproject.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.AssertionQuestionDTO;
import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ConflictException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.models.Question;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuestionRepository;
import com.capstoneproject.repository.QuizRepository;
import com.capstoneproject.response.ExceptionMessages;
import com.capstoneproject.response.SuccessMessages;

/**
 * This class contains the Service for Question.
 */
@Service
public class QuestionService {
    /**
     * questionRepository variable is created to perform Operations.
     */
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * quizRepository is injected.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * Creating the Logger Instance.
     */
    private Logger logger = LoggerFactory.getLogger(QuestionService.class);

    /**
     * Gets the List of All questions.
     *
     * @return List of Questions.
     */
    public final List<QuestionDTO> getQuestions() {
        List<Question> question = questionRepository.findAll();
        logger.info(SuccessMessages.QUESTION_FETCH);
        return question.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    /**
     * This method is written to convert the model into DTO.
     * @param question of Question is passed.
     * @return the Question DTO.
     */
    public final QuestionDTO convertModelToDTO(final Question question) {
        logger.info(SuccessMessages.MODEL_TO_DTO);
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setQuestionTitle(question.getQuestionTitle());
        questionDTO.setOption1(question.getOption1());
        questionDTO.setOption2(question.getOption2());
        questionDTO.setOption3(question.getOption3());
        questionDTO.setOption4(question.getOption4());
        questionDTO.setCorrectOption(question.getCorrectOption());
        questionDTO.setQuizId(question.getQuiz().getQuizId());
        return questionDTO;
    }

    /**
     * Gets the Question by Specific User ID.
     *
     * @param questionId - of Long Type.
     * @return the Question of UserId.
     */
    public final QuestionDTO getQuestionById(final Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new ElementNotExistsException(
                        ExceptionMessages.QUESTION_NOT_EXIST));
        logger.info(SuccessMessages.QUESTION_FETCH_BY_ID);
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(existingQuestion.getQuestionId());
        questionDTO.setQuestionTitle(existingQuestion.getQuestionTitle());
        questionDTO.setOption1(existingQuestion.getOption1());
        questionDTO.setOption2(existingQuestion.getOption2());
        questionDTO.setOption3(existingQuestion.getOption3());
        questionDTO.setOption4(existingQuestion.getOption4());
        questionDTO.setCorrectOption(existingQuestion.getCorrectOption());
        questionDTO.setQuizId(existingQuestion.getQuiz().getQuizId());
        return questionDTO;
    }

    /**
     * Add the questions.
     *
     * @param questionDTO of Question Type.
     * @return the status of the question being added.
     */
    public final QuestionDTO addQuestion(final QuestionDTO questionDTO) {

        Quiz quiz = quizRepository.findById(
                questionDTO.getQuizId()).orElseThrow(
                        () -> new ElementNotExistsException(
                                ExceptionMessages.QUIZ_NOT_EXIST));

        Set<String> optionList = new HashSet<>();
        optionList.add(questionDTO.getOption1());
        optionList.add(questionDTO.getOption2());
        optionList.add(questionDTO.getOption3());
        optionList.add(questionDTO.getOption4());
        final int optionNumber = 4;
        if (optionList.size() < optionNumber) {
            logger.error(ExceptionMessages.OPTIONS_NOT_REPEATED);
            throw new AlreadyExistsException(
                    ExceptionMessages.OPTIONS_NOT_REPEATED);
        }
        Question newQuestion = new Question();
        newQuestion.setQuestionId(questionDTO.getQuestionId());
        newQuestion.setQuestionTitle(questionDTO.getQuestionTitle());
        newQuestion.setOption1(questionDTO.getOption1());
        newQuestion.setOption2(questionDTO.getOption2());
        newQuestion.setOption3(questionDTO.getOption3());
        newQuestion.setOption4(questionDTO.getOption4());
        boolean matchFound = false;
        for (String option : optionList) {
            if (questionDTO.getCorrectOption().equals(option)) {
                newQuestion.setCorrectOption(questionDTO.getCorrectOption());
                matchFound = true;
            }
        }
        if (!matchFound) {
            logger.error(ExceptionMessages.OPTIONS_NOT_MATCHED);
            throw new ConflictException(ExceptionMessages.OPTIONS_NOT_MATCHED);
        }
        newQuestion.setQuiz(quiz);
        logger.info(SuccessMessages.QUESTION_ADD_SUCCESS);
        questionRepository.save(newQuestion);
        return questionDTO;
    }

    /**
     * This is used for adding Assertion type Questions.
     * @param assertQuestionDTO of Assertion Question DTO.
     * @return assertion Question DTO.
     */
    public final AssertionQuestionDTO addAssertionQuestion(final
            AssertionQuestionDTO assertQuestionDTO) {

        Quiz quiz = quizRepository.findById(
                assertQuestionDTO.getQuizId()).orElseThrow(
                        () -> new ElementNotExistsException(
                                ExceptionMessages.QUIZ_NOT_EXIST));

        Set<String> optionList = new HashSet<>();
        optionList.add(assertQuestionDTO.getOption1());
        optionList.add(assertQuestionDTO.getOption2());
        final int optionNumber = 2;
        if (optionList.size() < optionNumber) {
            logger.error(ExceptionMessages.OPTIONS_NOT_REPEATED);
            throw new AlreadyExistsException(
                    ExceptionMessages.OPTIONS_NOT_REPEATED);
        }
        Question newQuestion = new Question();
        newQuestion.setQuestionId(assertQuestionDTO.getQuestionId());
        newQuestion.setQuestionTitle(assertQuestionDTO.getQuestionTitle());
        newQuestion.setOption1(assertQuestionDTO.getOption1());
        newQuestion.setOption2(assertQuestionDTO.getOption2());
        boolean matchFound = false;
        for (String option : optionList) {
            if (assertQuestionDTO.getCorrectOption().equals(option)) {
                newQuestion.setCorrectOption(
                        assertQuestionDTO.getCorrectOption());
                matchFound = true;
            }
        }
        if (!matchFound) {
            logger.error(ExceptionMessages.OPTIONS_NOT_MATCHED);
            throw new ConflictException(ExceptionMessages.OPTIONS_NOT_MATCHED);
        }
        newQuestion.setQuiz(quiz);
        logger.info(SuccessMessages.QUESTION_ADD_SUCCESS);
        questionRepository.save(newQuestion);
        return assertQuestionDTO;
    }

    /**
     * updates the question.
     *
     * @param questionId      of Long type.
     * @param updatedQuestionDTO of Question type.
     * @return the updated question.
     */
    public final QuestionDTO updateQuestion(final Long questionId,
            final QuestionDTO updatedQuestionDTO) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new ElementNotExistsException(
                        ExceptionMessages.QUESTION_NOT_EXIST));
        logger.info(SuccessMessages.QUESTION_FOUND);
        existingQuestion
                    .setQuestionTitle(updatedQuestionDTO.getQuestionTitle());
        Set<String> optionList = new HashSet<>();
        optionList.add(updatedQuestionDTO.getOption1());
        optionList.add(updatedQuestionDTO.getOption2());
        optionList.add(updatedQuestionDTO.getOption3());
        optionList.add(updatedQuestionDTO.getOption4());
        final int optionNumber = 4;
        if (optionList.size() < optionNumber) {
            logger.error(ExceptionMessages.OPTIONS_NOT_REPEATED);
            throw new AlreadyExistsException(
                    ExceptionMessages.OPTIONS_NOT_REPEATED);
        }
        existingQuestion.setOption1(updatedQuestionDTO.getOption1());
        existingQuestion.setOption2(updatedQuestionDTO.getOption2());
        existingQuestion.setOption3(updatedQuestionDTO.getOption3());
        existingQuestion.setOption4(updatedQuestionDTO.getOption4());
        boolean matchFound = false;
        for (String option : optionList) {
            if (updatedQuestionDTO.getCorrectOption().equals(
                    option)) {
                existingQuestion.setCorrectOption(
                        updatedQuestionDTO.getCorrectOption());
                matchFound = true;
            }
        }
        if (!matchFound) {
            logger.error(ExceptionMessages.OPTIONS_NOT_MATCHED);
            throw new ConflictException(ExceptionMessages.OPTIONS_NOT_MATCHED);
        }
        logger.info(SuccessMessages.QUESTION_UPDATED_SUCCESS);
        questionRepository.save(existingQuestion);
        return updatedQuestionDTO;
    }

    /**
     * This method updates the Assertion Question.
     * @param questionId of Long type.
     * @param updatedAssertQuestionDTO AssertionQuestionDTO type.
     * @return Assertion Question DTO.
     */
    public final AssertionQuestionDTO updateAssertQuestion(
            final Long questionId,
            final AssertionQuestionDTO updatedAssertQuestionDTO) {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new ElementNotExistsException(
                        ExceptionMessages.QUESTION_NOT_EXIST));
        logger.info(SuccessMessages.QUESTION_FOUND);
        existingQuestion
                    .setQuestionTitle(
                            updatedAssertQuestionDTO.getQuestionTitle());
        Set<String> optionList = new HashSet<>();
        optionList.add(updatedAssertQuestionDTO.getOption1());
        optionList.add(updatedAssertQuestionDTO.getOption2());
        final int optionNumber = 2;
        if (optionList.size() < optionNumber) {
            logger.error(ExceptionMessages.OPTIONS_NOT_REPEATED);
            throw new AlreadyExistsException(
                    ExceptionMessages.OPTIONS_NOT_REPEATED);
        }
        existingQuestion.setOption1(updatedAssertQuestionDTO.getOption1());
        existingQuestion.setOption2(updatedAssertQuestionDTO.getOption2());

        boolean matchFound = false;
        for (String option : optionList) {
            if (updatedAssertQuestionDTO.getCorrectOption().equals(
                    option)) {
                existingQuestion.setCorrectOption(
                        updatedAssertQuestionDTO.getCorrectOption());
                matchFound = true;
            }
        }
        if (!matchFound) {
            logger.error(ExceptionMessages.OPTIONS_NOT_MATCHED);
            throw new ConflictException(ExceptionMessages.OPTIONS_NOT_MATCHED);
        }
        logger.info(SuccessMessages.QUESTION_UPDATED_SUCCESS);
        questionRepository.save(existingQuestion);
        return updatedAssertQuestionDTO;
    }

    /**
     * deletes the question by Id.
     *
     * @param questionId of Long type.
     */
    public final void deleteQuestion(final Long questionId) {
       questionRepository.findById(questionId)
                .orElseThrow(() -> new ElementNotExistsException(
                        ExceptionMessages.QUESTION_NOT_EXIST));
       logger.info(SuccessMessages.QUESTION_DELETE_SUCCESS);
       questionRepository.deleteById(questionId);
    }

    /**
     * This method returns the Questions when Quiz ID is given.
     * @param quizId of Quiz.
     * @return the List Of questions.
     */
    public final List<QuestionDTO> getQuestionByQuizId(final Long quizId) {
        quizRepository.findById(quizId).orElseThrow(
                () -> new ElementNotExistsException(
                        ExceptionMessages.QUIZ_NOT_EXIST));
        logger.info(SuccessMessages.QUIZ_FOUND);
        List<Question> questions = questionRepository
                    .getQuestionByQuizId(quizId);
        logger.info(SuccessMessages.QUESTION_FETCH_BY_QUIZ_ID);
        return questions.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }
}
