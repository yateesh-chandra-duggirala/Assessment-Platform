package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.response.Response;
import com.capstoneproject.service.AllResultsService;

@SuppressWarnings("rawtypes")
class AllResultsControllerTest {

    @InjectMocks
    private AllResultsController allResultsController;

    @Mock
    private AllResultsService allResultsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllResults() {
        List<AllResultsDTO> allResultsDto = new ArrayList<>();
        AllResultsDTO result1 = new AllResultsDTO();
        AllResultsDTO result2 = new AllResultsDTO();
        allResultsDto.add(result1);
        allResultsDto.add(result2);
        when(allResultsService.getAllResults()).thenReturn(allResultsDto);

        Response response = allResultsController.getAllResults();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(allResultsDto, response.getBody());
    }

    @Test
    public void testGetResultsByUserId() {
        Long userId = 1L;
        List<AllResultsDTO> resultsList = new ArrayList<>();
        resultsList.add(new AllResultsDTO());
        when(allResultsService.getResultsByUserId(userId)).thenReturn(resultsList);

        Response response = allResultsController.getResultsByUserId(userId);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(resultsList, response.getBody());
    }
}