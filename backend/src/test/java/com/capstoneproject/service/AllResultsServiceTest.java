package com.capstoneproject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.repository.AllResultsRepository;

class AllResultsServiceTest {

    @InjectMocks
    private AllResultsService allResultsService;

    @Mock
    private AllResultsRepository allResultsRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllResults() {
        List<AllResults> allResults = new ArrayList<>();
        AllResults allResults1 = new AllResults();
        allResults1.setResultId(1L);
        AllResults allResults2 = new AllResults();
        allResults2.setResultId(2L);

        allResults.add(allResults1);
        allResults.add(allResults2);
        when(allResultsRepo.findAll()).thenReturn(allResults);

        List<AllResultsDTO> allResultsDto = allResultsService.getAllResults();
        assertEquals(2, allResultsDto.size());
    }

    @Test
    public void testGetResultsByUserId() {
        List<AllResults> allResults = new ArrayList<>();
        AllResults allResults1 = new AllResults();
        allResults1.setResultId(1L);
        AllResults allResults2 = new AllResults();
        allResults2.setResultId(2L);

        allResults.add(allResults1);
        allResults.add(allResults2);
        when(allResultsRepo.getResultsByUserId(5L)).thenReturn(allResults);

        List<AllResultsDTO> resultsDto = allResultsService.getResultsByUserId(5L);
        assertEquals(2, resultsDto.size());
    }
}
