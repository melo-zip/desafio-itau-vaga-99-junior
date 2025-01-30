package com.example.desafio_backend_itau.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.DoubleSummaryStatistics;

@Service
public class StatisticsService {
    @Autowired
    TransactionService transactionService;

    public DoubleSummaryStatistics getStatistics(int minutes) {
      return transactionService.calculateStatistics(minutes);
    }
}
