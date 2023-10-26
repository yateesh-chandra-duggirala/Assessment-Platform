package com.capstoneproject.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.AssertionQuestionDTO;
import com.capstoneproject.dto.QuestionDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ConflictException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.models.Question;
import com.capstoneproject.models.Quiz;
import com.capstoneproject.repository.QuestionRepository;
import com.capstoneproject.repository.QuizRepository;

class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuizRepository quizRepository;
    
    @InjectMocks
    private QuestionService questionService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuestions() {
        
        Question q1= new Question("Test Question", "A", "B", "C", "D", "OptionB");
        Question q2= new Question("Test Question 2", "A1", "B2", "C3", "D4", "OptionC");
        Quiz quiz = new Quiz(2L, "Test QUiz", "Desc", 5, 2);
        q1.setQuiz(quiz);
        q2.setQuiz(quiz);

        List<Question> questionList = new ArrayList<>();
        questionList.add(q2);
        questionList.add(q1);
        when(questionRepository.findAll()).thenReturn(questionList);

        List<QuestionDTO> questionDtoList = questionService.getQuestions();
        assertEquals("Test Question", questionDtoList.get(1).getQuestionTitle());
        assertEquals("Test Question 2", questionDtoList.get(0).getQuestionTitle());
    }

    @Test
    public void testGetQuestionByQuizId() {
        Long quizId = 5L;

        Quiz quiz = new Quiz(quizId, "Quiz 1", "Description 1", 5, 2);
        Question q1= new Question("Test Question", "A", "B", "C", "D", "OptionB");
        Question q2= new Question("Test Question 2", "A1", "B2", "C3", "D4", "OptionC");
        q1.setQuiz(quiz);
        q2.setQuiz(quiz);

        List<Question> questionList = new ArrayList<>();
        questionList.add(q1);
        questionList.add(q2);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(questionRepository.getQuestionByQuizId(quizId)).thenReturn(questionList);

        List<QuestionDTO> questionDtoList = questionService.getQuestionByQuizId(quizId);
        assertNotNull(questionDtoList);
        assertEquals(2, questionDtoList.size());
        assertEquals("Test Question", questionDtoList.get(0).getQuestionTitle());
        assertEquals("Test Question 2", questionDtoList.get(1).getQuestionTitle());
    }

    @Test
    public void testGetQuestionByQuizIdElementNotExists() {
        Long quizId = 1L;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> questionService.getQuestionByQuizId(quizId));
    }

    @Test
    public void testGetQuestionById() {
        Long questionId = 10L;
        Quiz quiz = new Quiz(2L, "Test QUiz", "Desc", 5, 2);

        Question question = new Question("Test Question", "A", "B", "C", "D", "OptionB");
        question.setQuiz(quiz);
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        QuestionDTO questionDto = questionService.getQuestionById(questionId);
        assertEquals("Test Question", questionDto.getQuestionTitle());
        assertEquals("A", questionDto.getOption1());
        assertEquals("B", questionDto.getOption2());
        assertEquals("C", questionDto.getOption3());
        assertEquals("D", questionDto.getOption4());
        assertEquals("OptionB", questionDto.getCorrectOption());
    }

    @Test
    public void testQuestionByIdElementNotExists() {
        Long questionId = 1L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> questionService.getQuestionById(questionId));
    }
    
    @Test
    public void testAddQuestion() {
        Long quizId = 1L;
        QuestionDTO questionDto = new QuestionDTO(null, "Test Question", "A", "B", "C", "D", "B", quizId);

        Quiz quiz = new Quiz(quizId, "Quiz 1", "Quiz 1 Description", 7, 2);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(questionRepository.save(any(Question.class))).thenReturn(new Question("Test Question", "A", "B", "C", "D", "B"));

        QuestionDTO addedQuestion = questionService.addQuestion(questionDto);
        assertNull(addedQuestion.getQuestionId());
        assertEquals("Test Question", addedQuestion.getQuestionTitle());
        assertEquals("A", addedQuestion.getOption1());
        assertEquals("B", addedQuestion.getOption2());
        assertEquals("C", addedQuestion.getOption3());
        assertEquals("D", addedQuestion.getOption4());
        assertEquals(quizId, addedQuestion.getQuizId());
    }

    @Test
    public void testAddAssertQuestion() {
        Long quizId = 1L;
        AssertionQuestionDTO questionDto = new AssertionQuestionDTO(null, "Test Question", "A", "B", "B", quizId);

        Quiz quiz = new Quiz(quizId, "Quiz 1", "Quiz 1 Description", 7, 2);
        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        when(questionRepository.save(any(Question.class))).thenReturn(new Question("Test Question", "A", "B", "C", "D", "B"));

        AssertionQuestionDTO addedQuestion = questionService.addAssertionQuestion(questionDto);
        assertNull(addedQuestion.getQuestionId());
        assertEquals("Test Question", addedQuestion.getQuestionTitle());
        assertEquals("A", addedQuestion.getOption1());
        assertEquals("B", addedQuestion.getOption2());
        assertEquals(quizId, addedQuestion.getQuizId());
    }

    @Test
    public void testUpdateQuestionWithLessThanFourOptions() {
        Question existingQuestion = new Question();
        existingQuestion.setQuestionId(1L);
        existingQuestion.setQuestionTitle("Existing Question");
        existingQuestion.setOption1("Option 1");
        existingQuestion.setOption2("Option 2");
        existingQuestion.setOption3("Option 3");
        existingQuestion.setOption4("Option 4");
        existingQuestion.setCorrectOption("Option 1");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(existingQuestion));

        QuestionDTO updatedQuestionDTO = new QuestionDTO();
        updatedQuestionDTO.setQuestionTitle("Updated Question");
        updatedQuestionDTO.setOption1("Updated Option 1");
        updatedQuestionDTO.setOption2("Updated Option 2");

        assertThrows(AlreadyExistsException.class, () -> questionService.updateQuestion(1L, updatedQuestionDTO));
    }

    @Test
    public void testUpdateQuestionWithNoMatchingCorrectOption() {
        Question existingQuestion = new Question();
        existingQuestion.setQuestionId(1L);
        existingQuestion.setQuestionTitle("Existing Question");
        existingQuestion.setOption1("Option 1");
        existingQuestion.setOption2("Option 2");
        existingQuestion.setOption3("Option 3");
        existingQuestion.setOption4("Option 4");
        existingQuestion.setCorrectOption("Option 1");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(existingQuestion));

        QuestionDTO updatedQuestionDTO = new QuestionDTO();
        updatedQuestionDTO.setQuestionTitle("Updated Question");
        updatedQuestionDTO.setOption1("Updated Option 1");
        updatedQuestionDTO.setOption2("Updated Option 2");
        updatedQuestionDTO.setOption3("Updated Option 3");
        updatedQuestionDTO.setOption4("Updated Option 4");
        updatedQuestionDTO.setCorrectOption("Invalid Correct Option");

        assertThrows(ConflictException.class, () -> questionService.updateQuestion(1L, updatedQuestionDTO));
    }


    @Test
    public void testAddAssertQuestionWithUnmatchingCorrectOption_InvalidInput() {
        AssertionQuestionDTO questionDTO = new AssertionQuestionDTO();
        questionDTO.setQuizId(1L);
        questionDTO.setQuestionTitle("Sample Question");
        questionDTO.setOption1("Option 1");
        questionDTO.setOption2("Option 2");
        questionDTO.setCorrectOption("Invalid Correct Option");

        when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));

        assertThrows(ConflictException.class, () -> questionService.addAssertionQuestion(questionDTO));
    }

    @Test
    void testUPDATEAssertionQuestion_DuplicateOptions() {
        Long questionId = 1L;
        AssertionQuestionDTO questionDTO = new AssertionQuestionDTO(questionId, "Hello", "A", "A", "A", 1L);

        Question addedQuestion = new Question("Hello", "A", "B", "C", "D", "B");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(addedQuestion));
        assertThrows(AlreadyExistsException.class, () -> {
            questionService.updateAssertQuestion(questionId, questionDTO);
        });
    }

    @Test
    void testUPDATEAssertQuestion_CorrectOptionMismatch() {

        Long questionId = 1L;
        AssertionQuestionDTO questionDTO = new AssertionQuestionDTO(null, "Hello", "A", "B", "optB", 1L);
        Question addedQuestion = new Question("Hello", "A", "B", "C", "D", "optB");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(addedQuestion));

        assertThrows(ConflictException.class, () -> {
            questionService.updateAssertQuestion(questionId, questionDTO);
        });

        verify(questionRepository, never()).save(any());
    }


    @Test
    public void testAddAssertionQuestion_QuizNotFound() {
        AssertionQuestionDTO questionDTO = new AssertionQuestionDTO();
        questionDTO.setQuestionTitle("What is 2 + 2?");
        questionDTO.setOption1("3");
        questionDTO.setOption2("4");
        questionDTO.setCorrectOption("4");
        questionDTO.setQuizId(7L);

        when(quizRepository.findById(questionDTO.getQuizId())).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> questionService.addAssertionQuestion(questionDTO));
    }


    @Test
    public void testUpdateAssertQuestionWithNoMatchingCorrectOption() {
        Question existingQuestion = new Question();
        existingQuestion.setQuestionId(1L);
        existingQuestion.setQuestionTitle("Existing Question");
        existingQuestion.setOption1("Option 1");
        existingQuestion.setOption2("Option 2");
        existingQuestion.setOption3("Option 3");
        existingQuestion.setOption4("Option 4");
        existingQuestion.setCorrectOption("Option 1");

        when(questionRepository.findById(1L)).thenReturn(Optional.of(existingQuestion));

        AssertionQuestionDTO updatedQuestionDTO = new AssertionQuestionDTO();
        updatedQuestionDTO.setQuestionTitle("Updated Question");
        updatedQuestionDTO.setOption1("Updated Option 1");
        updatedQuestionDTO.setOption2("Updated Option 2");
        updatedQuestionDTO.setCorrectOption("Invalid Correct Option");

        assertThrows(ConflictException.class, () -> questionService.updateAssertQuestion(1L, updatedQuestionDTO));
    }

    @Test
    public void testAddQuestionWithLessThanFourOptions_InvalidInput() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuizId(1L);
        questionDTO.setQuestionTitle("Sample Question");
        questionDTO.setOption1("Option 1");
        questionDTO.setOption2("Option 2");

        when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));

        assertThrows(AlreadyExistsException.class, () -> questionService.addQuestion(questionDTO));
    }

    @Test
    public void testAddQuestionWithNoMatchingCorrectOption_InvalidInput() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuizId(1L);
        questionDTO.setQuestionTitle("Sample Question");
        questionDTO.setOption1("Option 1");
        questionDTO.setOption2("Option 2");
        questionDTO.setOption3("Option 3");
        questionDTO.setOption4("Option 4");
        questionDTO.setCorrectOption("Invalid Correct Option");

        when(quizRepository.findById(1L)).thenReturn(Optional.of(new Quiz()));

        assertThrows(ConflictException.class, () -> questionService.addQuestion(questionDTO));
    }

    @Test
    void testUPDATEQuestion_DuplicateOptions() {
        Long questionId = 1L;
        QuestionDTO questionDTO = new QuestionDTO(questionId, "Hello", "A", "B", "C", "A", "B", 1L);

        Question addedQuestion = new Question("Hello", "A", "B", "C", "D", "B");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(addedQuestion));
        assertThrows(AlreadyExistsException.class, () -> {
            questionService.updateQuestion(questionId, questionDTO);
        });
    }

    @Test
    void testUPDATEQuestion_CorrectOptionMismatch() {

        Long questionId = 1L;
        QuestionDTO questionDTO = new QuestionDTO(null, "Hello", "A", "B", "C", "D", "optB", 1L);
        Question addedQuestion = new Question("Hello", "A", "B", "C", "D", "optB");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(addedQuestion));

        assertThrows(ConflictException.class, () -> {
            questionService.updateQuestion(questionId, questionDTO);
        });

        verify(questionRepository, never()).save(any());
    }


    @Test
    public void testAddQuestion_QuizNotFound() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionTitle("What is 2 + 2?");
        questionDTO.setOption1("3");
        questionDTO.setOption2("4");
        questionDTO.setOption3("5");
        questionDTO.setOption4("6");
        questionDTO.setCorrectOption("4");
        questionDTO.setQuizId(7L);

        when(quizRepository.findById(questionDTO.getQuizId())).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> questionService.addQuestion(questionDTO));
    }

    @Test
    public void testDeleteQuestion() {
        Long questionId = 5L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(new Question()));

        questionService.deleteQuestion(questionId);
    }
    
    @Test
    public void testDeleteQuestionElementNotExists() {
        Long questionId = 5L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> questionService.deleteQuestion(questionId));
    }
    

    @Test
    void testUpdateQuestion_QuestionNotFound() {
        Long questionId = 1L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO(null, "Hello", "A", "B", "C", "D", "B", 1L);
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> {
            questionService.updateQuestion(questionId, updatedQuestionDTO);
        });
    }

    @Test
    void testUpdateQuestion_DuplicateOptions() {
        Long questionId = 1L;
        QuestionDTO updatedQuestionDTO = new QuestionDTO(null, "Hello", "A", "B", "C", "A", "B", 1L);
        Question existingQuestion = new Question("Hello", "A", "B", "C", "A", "B");
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));

        assertThrows(AlreadyExistsException.class, () -> {
            questionService.updateQuestion(questionId, updatedQuestionDTO);
        });
    }

    @Test
    public void testUpdateQuestionNotFound() {
        Long questionId = 7L;
        QuestionDTO questionDto = new QuestionDTO(null, "Hello", "A", "B", "C", "D", "OptionB", 1L);

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.updateQuestion(questionId, questionDto));
    }
    
    @Test
    public void testUpdateQuizWithElementNotFoundException() {
        Long questionId = 5L;
        QuestionDTO questionDto = new QuestionDTO();

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(ElementNotExistsException.class, () -> questionService.updateQuestion(questionId, questionDto));
    }

}
