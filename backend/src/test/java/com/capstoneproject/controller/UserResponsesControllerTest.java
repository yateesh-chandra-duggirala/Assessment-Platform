package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.UserResponsesService;

class UserResponsesControllerTest {

    @InjectMocks
    private UserResponsesController userResponsesController;

    @Mock
    private UserResponsesService userResponsesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testAddUserResponses() {

        UserResponsesDTO userResponsesDTO = new UserResponsesDTO();
        when(userResponsesService.addUserResponses(userResponsesDTO)).thenReturn(null);

        Response response = userResponsesController.addUserResponses(userResponsesDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.RESPONSE_ADDED, response.getMessage());
    }

}
