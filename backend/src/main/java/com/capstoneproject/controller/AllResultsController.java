package com.capstoneproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.AllResultsService;

/**
 * This is the controller class for the All Results.
 */
@RestController
@RequestMapping("results")
@CrossOrigin
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AllResultsController {

    /**
     * This autowires All Results Service.
     */
    @Autowired
    private AllResultsService allResultsService;

    /**
     * Creating instance for the Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AllResultsController.class);

    /**
     * This is the Response Entity of All Results.
     * @return OK status.
     */
    @GetMapping
    public final Response getAllResults() {
        List<AllResultsDTO> allResultsDTO = allResultsService.getAllResults();
        logger.info(SuccessMessages.RESULTS_FETCH);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.RESULTS_FETCH, allResultsDTO);
        return response;
    }

    /**
     * This is the Response Entity.
     * @param userId - String.
     * @return the Ok status.
     */
    @GetMapping("/{userId}")
    public final Response getResultsByUserId(
            @PathVariable final Long userId) {
        List<AllResultsDTO> allResultsDto = allResultsService
                .getResultsByUserId(userId);
        logger.info(SuccessMessages.RESULTS_FETCH_BY_ID + userId);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.RESULTS_FETCH_BY_ID, allResultsDto);
        return response;
    }
}
