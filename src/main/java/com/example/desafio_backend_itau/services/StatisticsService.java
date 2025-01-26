package com.example.desafio_backend_itau.services;

import com.example.desafio_backend_itau.models.TransactionModel;
import com.example.desafio_backend_itau.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    TransactionRepository transactionRepository;

    public DoubleSummaryStatistics getStatistics(int minutes) {
        List<TransactionModel> transactions = transactionRepository.getTransactions();
        List<Double> values = new ArrayList<>();

        for (int i = 0; i < transactions.size(); i++) {
            OffsetDateTime zuluDateTime = transactions.get(i).getTime();

            Duration durationTime = Duration.between(zuluDateTime.plusHours(3), OffsetDateTime.now());
            long duration = durationTime.toMinutes();

            if (duration < minutes) {
                values.add(transactions.get(i).getValue());
            }
        }

        double[] arrayValues = values.stream().mapToDouble(Double::doubleValue).toArray();
        return Arrays.stream(arrayValues).summaryStatistics();
    }

}
