package com.capstoneproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.UserResponsesService;

import jakarta.validation.Valid;

/**
 * This is the controller for User Responses.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("response")
@SuppressWarnings("rawtypes")
public class UserResponsesController {

    /**
     * This autowires Response Service.
     */
    @Autowired
    private UserResponsesService responseService;

    /**
     * Creating instance for logger.
     */
    private Logger logger = LoggerFactory.getLogger(
            UserResponsesController.class);
    /**
     * This is the addUser Response Entity.
     * @param userResponse - UserResponse DTO.
     * @return Ok status.
     */
    @PostMapping("/add")
    public final Response addUserResponses(
            @RequestBody @Valid final UserResponsesDTO userResponse) {
        responseService.addUserResponses(userResponse);
        logger.info(SuccessMessages.RESPONSE_ADDED);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.RESPONSE_ADDED);
        return response;
    }

}
