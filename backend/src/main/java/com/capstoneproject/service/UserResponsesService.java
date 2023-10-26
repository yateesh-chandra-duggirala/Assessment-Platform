package com.capstoneproject.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.exceptions.ConflictException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserResponses;
import com.capstoneproject.repository.AllResultsRepository;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.repository.QuizRepository;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.repository.UserResponsesRepository;
import com.capstoneproject.response.ExceptionMessages;
import com.capstoneproject.response.SuccessMessages;

/**
 * This is Service class for the UserResponses.
 */
@Service
public class UserResponsesService {

    /**
     * This autowires the UserResponse Repository.
     */
    @Autowired
    private UserResponsesRepository responsesRepository;

    /**
     * This autowires AllResults Repository.
     */
    @Autowired
    private AllResultsRepository allResultsRepository;

    /**
     * This autowires the User Repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This autowires the Category Repository.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This autowires the Quiz Repository.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * Creating Instance for logger.
     */
    private Logger logger = LoggerFactory.getLogger(
            UserResponsesService.class);

    /**
     * This method is used to add the User Responses.
     * @param responses of UserResponsesDTO is passed.
     * @return the added User Response.
     */
    public final UserResponsesDTO addUserResponses(
            final UserResponsesDTO responses) {
        User user = userRepository.findById(responses.getUserId())
                    .orElseThrow(() -> new ElementNotExistsException(
                            ExceptionMessages.USER_NOT_EXIST
                            + responses.getUserId()));
        Quiz quiz = quizRepository.findById(responses.getQuizId())
                    .orElseThrow(() -> new ElementNotExistsException(
                            ExceptionMessages.QUIZ_NOT_EXIST
                            + responses.getQuizId()));
        logger.info(SuccessMessages.RESPONSE_ADDED);
        AllResults results = new AllResults();
        results.setUserId(responses.getUserId());
        Optional<User> users = userRepository
                .findById(responses.getUserId());
        results.setEmail(users.get().getEmail());
        results.setUserName(users.get().getName());
        Optional<Category> category = categoryRepository
                .findById(responses.getCategoryId());
        results.setCategoryName(category.get().getCategoryName());
        Optional<Quiz> quizz = quizRepository
                .findById(responses.getQuizId());
        results.setQuizName(quizz.get().getQuizName());
        if (responses.getNumOfQuestionsAnswered()
                <= responses.getNumOfQuestions()) {
            results.setNumOfQuestions(responses.getNumOfQuestions());
            results.setNumOfQuestionsAnswered(
                    responses.getNumOfQuestionsAnswered());
        } else {
            throw new ConflictException(ExceptionMessages.QUESTION_CONFLICT);
        }
        if (responses.getMarksScored() <= responses.getTotalMarks()) {
            results.setTotalMarks(responses.getTotalMarks());
            results.setMarksScored(responses.getMarksScored());
        } else {
            throw new ConflictException(ExceptionMessages.MARKS_CONFLICT);
        }
        if (responses.getTimeStamp() != null) {
            results.setTimeStamp(responses.getTimeStamp());
        } else {
            results.setTimeStamp(responses.setTimeStamp());
        }
        allResultsRepository.save(results);

        UserResponses userResponses = new UserResponses();
        userResponses.setUsers(user);
        userResponses.setQuiz(quiz);
        if (responses.getNumOfQuestionsAnswered()
                <= responses.getNumOfQuestions()) {
            userResponses
            .setNumOfQuestions(responses.getNumOfQuestions());
            userResponses.setNumOfQuestionsAnswered(
                    responses.getNumOfQuestionsAnswered());
        } else {
            throw new ConflictException(ExceptionMessages.QUESTION_CONFLICT);
        }
        if (responses.getMarksScored() <= responses.getTotalMarks()) {
            userResponses.setTotalMarks(responses.getTotalMarks());
            userResponses.setMarksScored(responses.getMarksScored());
        } else {
            throw new ConflictException(ExceptionMessages.MARKS_CONFLICT);
        }
        if (responses.getTimeStamp() != null) {
            userResponses.setTimeStamp(responses.getTimeStamp());
        } else {
            userResponses.setTimeStamp(responses.setTimeStamp());
        }
        responsesRepository.save(userResponses);
        return responses;
    }
}
