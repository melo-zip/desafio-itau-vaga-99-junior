package com.example.desafio_backend_itau.repositories;

import com.example.desafio_backend_itau.models.TransactionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

public class TransactionRepositoryTest {
    private TransactionRepository repository;
    private TransactionModel transactionModel;

    @BeforeEach
    public void setUp() {
        repository = new TransactionRepository();
        transactionModel = new TransactionModel(100.0, OffsetDateTime.parse("2025-01-01T01:00:00+00:00"));

    }

    @Test
    public void shouldAddTransactionSuccessfully() {
        repository.addTransaction(transactionModel);

        List<TransactionModel> transactions = repository.getTransactions();
        Assertions.assertFalse(transactions.isEmpty());
        Assertions.assertEquals(1, transactions.size());
        Assertions.assertEquals(transactionModel.getValue(), transactions.get(0).getValue());
        Assertions.assertEquals(transactionModel.getTime(), transactions.get(0).getTime());
    }

    @Test
    public void shouldDeleteAllTransactionsSuccessfully() {
        repository.addTransaction(transactionModel);
        repository.deleteTransactions();

        List<TransactionModel> transactions = repository.getTransactions();
        Assertions.assertTrue(transactions.isEmpty(), "A lista deveria estar vazia após a exclusão.");
    }
}
