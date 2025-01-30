package com.example.desafio_backend_itau.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.DoubleSummaryStatistics;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {
    @InjectMocks
    StatisticsService service;

    @Mock
    TransactionService transactionService;

    @Test
    public void shouldReturnStatisticsSuccessfully(){
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics(1, 100.0, 200.0, 300.0);
        when(transactionService.calculateStatistics(1)).thenReturn(stats);

        DoubleSummaryStatistics statistics = service.getStatistics(1);
        Assertions.assertEquals(stats, statistics);

        verify(transactionService, times(1)).calculateStatistics(anyInt());
    }
}
