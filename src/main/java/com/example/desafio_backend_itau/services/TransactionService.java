package com.example.desafio_backend_itau.services;

import com.example.desafio_backend_itau.exceptions.ValidationException;
import com.example.desafio_backend_itau.helpers.TransactionValidator;
import com.example.desafio_backend_itau.models.TransactionModel;
import com.example.desafio_backend_itau.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransactionService implements TransactionValidator {
    @Autowired
    private TransactionRepository repository;

    public void createTransaction(TransactionModel transactionModel) {
        List<String> errors = validate(transactionModel);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        repository.addTransaction(transactionModel);
    }

    public List<TransactionModel> getTransactions() {
        return repository.getTransactions();
    }

    public void deleteTransactions() {
        repository.deleteTransactions();
    }

    public DoubleSummaryStatistics calculateStatistics(int minutes) {
        return repository.getTransactions().stream()
                .filter(t -> Duration.between(t.getTime().plusHours(3), OffsetDateTime.now()).toMinutes() < minutes)
                .mapToDouble(TransactionModel::getValue)
                .summaryStatistics();
    }
}