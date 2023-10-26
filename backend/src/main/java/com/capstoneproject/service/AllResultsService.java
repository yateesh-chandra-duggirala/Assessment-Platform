package com.capstoneproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.repository.AllResultsRepository;
import com.capstoneproject.response.SuccessMessages;

/**
 * This is the Service class for the All Results.
 */
@Service
public class AllResultsService {

    /**
     * This autowires the AllResults Repository.
     */
    @Autowired
    private AllResultsRepository allResultsRepository;

    /**
     * Creating the instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AllResultsService.class);

    /**
     * This is the getter method for All Results.
     * @return the list of All Results.
     */
    public final List<AllResultsDTO> getAllResults() {
        List<AllResults> allResults = allResultsRepository.findAll();
        logger.info(SuccessMessages.RESULTS_FETCH);
        return allResults.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }

    /**
     * This method converts the Entity to DTO.
     * @param allResults of AllResults.
     * @return the converted DTO.
     */
    private AllResultsDTO convertModelToDTO(final AllResults allResults) {
        logger.info(SuccessMessages.MODEL_TO_DTO);
        AllResultsDTO allResultsDto = new AllResultsDTO();
        allResultsDto.setResultId(allResults.getResultId());
        allResultsDto.setUserId(allResults.getUserId());
        allResultsDto.setEmail(allResults.getEmail());
        allResultsDto.setUserName(allResults.getUserName());
        allResultsDto.setCategoryName(allResults.getCategoryName());
        allResultsDto.setQuizName(allResults.getQuizName());
        allResultsDto.setNumOfQuestions(allResults.getNumOfQuestions());
        allResultsDto.setNumOfQuestionsAnswered(
                allResults.getNumOfQuestionsAnswered());
        allResultsDto.setTotalMarks(allResults.getTotalMarks());
        allResultsDto.setMarksScored(allResults.getMarksScored());
        allResultsDto.setTimeStamp(allResults.getTimeStamp());
        return allResultsDto;
    }

    /**
     * This method gets the Result by Email.
     * @param userId is passed.
     * @return the List of Results for that email.
     */
    public final List<AllResultsDTO> getResultsByUserId(final Long userId) {
        List<AllResults> allResults = allResultsRepository
                .getResultsByUserId(userId);
        logger.info(SuccessMessages.RESULTS_FETCH_BY_ID);
        return allResults.stream().map(this::convertModelToDTO)
                .collect(Collectors.toList());
    }
}
