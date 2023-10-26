package com.capstoneproject.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.exceptions.ConflictException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.models.Category;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.AllResultsRepository;
import com.capstoneproject.repository.CategoryRepository;
import com.capstoneproject.repository.QuizRepository;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.repository.UserResponsesRepository;

class UserResponsesServiceTest {

    @Mock
    private UserResponsesRepository userResponsesRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private QuizRepository quizRepo;

    @Mock
    private CategoryRepository categoryRepo;
    
    @Mock
    private AllResultsRepository allResultsRepo;

    @InjectMocks
    private UserResponsesService userResponsesService; 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUserResponses() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(5L);
        userResponsesDto.setQuizId(6L);
        userResponsesDto.setCategoryId(7L);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        userResponsesDto.setTotalMarks(20);
        userResponsesDto.setMarksScored(16);
        
        User user = new User(5L, "Yateesh");
        Category category = new Category(7L, "Category Test", "This is test Description");
        Quiz quiz = new Quiz(6L, "Quiz Test", "Test Quiz Description", 10, 5);
        when(userRepo.findById(5L)).thenReturn(Optional.of(user));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        UserResponsesDTO addedResponses = userResponsesService.addUserResponses(userResponsesDto);
        assertNotNull(addedResponses);
        assertEquals(10, addedResponses.getNumOfQuestions());
        assertEquals(8, addedResponses.getNumOfQuestionsAnswered());
    }

    @Test
    public void testAddUserResponsesWithExistingResponse() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();

        userResponsesDto.setUserId(1L);
        userResponsesDto.setCategoryId(3L);
        userResponsesDto.setQuizId(5L);
        userResponsesDto.setTotalMarks(10);
        userResponsesDto.setMarksScored(8);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);

        assertThrows(ElementNotExistsException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithNonExistingUser() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(1L);
        userResponsesDto.setCategoryId(3L);
        userResponsesDto.setQuizId(5L);
        userResponsesDto.setTotalMarks(10);
        userResponsesDto.setMarksScored(8);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);

        when(userResponsesRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithNonExistingQuiz() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();

        userResponsesDto.setUserId(1L);
        userResponsesDto.setCategoryId(3L);
        userResponsesDto.setQuizId(5L);
        userResponsesDto.setTotalMarks(10);
        userResponsesDto.setMarksScored(8);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);

        when(userRepo.findById(1L)).thenReturn(Optional.of(new User()));
        when(quizRepo.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithMarksScoredGreaterThanTotalMarks() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(5L);
        userResponsesDto.setQuizId(6L);
        userResponsesDto.setCategoryId(7L);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        userResponsesDto.setTotalMarks(20);
        userResponsesDto.setMarksScored(25);
        
        User user = new User(5L, "Yateesh");
        Category category = new Category(7L, "Category Test", "This is test Description");
        Quiz quiz = new Quiz(6L, "Quiz Test", "Test Quiz Description", 10, 5);
        when(userRepo.findById(5L)).thenReturn(Optional.of(user));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        assertThrows(ConflictException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithNumOfQuestionsAnsweredGreaterThanNumOfQuestions() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(5L);
        userResponsesDto.setQuizId(6L);
        userResponsesDto.setCategoryId(7L);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(12);
        userResponsesDto.setTotalMarks(20);
        userResponsesDto.setMarksScored(16);
        
        User user = new User(5L, "Yateesh");
        Category category = new Category(7L, "Category Test", "This is test Description");
        Quiz quiz = new Quiz(6L, "Quiz Test", "Test Quiz Description", 10, 5);
        when(userRepo.findById(5L)).thenReturn(Optional.of(user));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        assertThrows(ConflictException.class, () -> userResponsesService.addUserResponses(userResponsesDto));
    }

    @Test
    public void testAddUserResponsesWithNullTimeStamp() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(5L);
        userResponsesDto.setQuizId(6L);
        userResponsesDto.setCategoryId(7L);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        userResponsesDto.setTotalMarks(20);
        userResponsesDto.setMarksScored(16);
        userResponsesDto.setTimeStamp(null); 

        User user = new User(5L, "Yateesh");
        Category category = new Category(7L, "Category Test", "This is test Description");
        Quiz quiz = new Quiz(6L, "Quiz Test", "Test Quiz Description", 10, 5);
        when(userRepo.findById(5L)).thenReturn(Optional.of(user));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        UserResponsesDTO addedResponses = userResponsesService.addUserResponses(userResponsesDto);

        assertEquals(10, addedResponses.getNumOfQuestions());
        assertEquals(8, addedResponses.getNumOfQuestionsAnswered());
        assertNull(addedResponses.getTimeStamp());
    }

    @Test
    public void testAddUserResponsesWithNonNullTimeStamp() {
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        userResponsesDto.setUserId(5L);
        userResponsesDto.setQuizId(6L);
        userResponsesDto.setCategoryId(7L);
        userResponsesDto.setNumOfQuestions(10);
        userResponsesDto.setNumOfQuestionsAnswered(8);
        userResponsesDto.setTotalMarks(20);
        userResponsesDto.setMarksScored(16);
        userResponsesDto.setTimeStamp("2023-10-08T10:30:00");

        
        User user = new User(5L, "Yateesh");
        Category category = new Category(7L, "Category Test", "This is test Description");
        Quiz quiz = new Quiz(6L, "Quiz Test", "Test Quiz Description", 10, 5);
        when(userRepo.findById(5L)).thenReturn(Optional.of(user));
        when(quizRepo.findById(quiz.getQuizId())).thenReturn(Optional.of(quiz));
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));

        UserResponsesDTO addedResponses = userResponsesService.addUserResponses(userResponsesDto);
        
        assertNotNull(addedResponses);
        assertEquals(10, addedResponses.getNumOfQuestions());
        assertEquals(8, addedResponses.getNumOfQuestionsAnswered());
        assertNotNull(addedResponses.getTimeStamp()); 
    }

}
