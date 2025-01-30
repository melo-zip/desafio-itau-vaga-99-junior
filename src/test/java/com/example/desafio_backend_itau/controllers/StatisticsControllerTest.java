package com.example.desafio_backend_itau.controllers;

import com.example.desafio_backend_itau.services.StatisticsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.DoubleSummaryStatistics;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {
    @InjectMocks
    StatisticsController controller;

    @Mock
    StatisticsService service;

    @Test
    public void shouldReturnDoubleSummaryStatisticsResponseSuccessfully(){
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics(1, 100.0, 200.0, 300.0);

        when(service.getStatistics(1)).thenReturn(stats);

        ResponseEntity<DoubleSummaryStatistics> response = controller.listStatistics(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(stats, response.getBody());

        verify(service, times(1)).getStatistics(anyInt());
    }
}
