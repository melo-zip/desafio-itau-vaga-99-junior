package com.example.desafio_backend_itau.services;

import com.example.desafio_backend_itau.exceptions.ValidationException;
import com.example.desafio_backend_itau.models.TransactionModel;
import com.example.desafio_backend_itau.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @InjectMocks
    TransactionService service;

    @Mock
    TransactionRepository repository;

    TransactionModel transactionModel;

    @Test
    public void shouldCreateTransactionSuccessfully() {
        transactionModel = new TransactionModel(100.0, OffsetDateTime.now().minusHours(1));

        service.createTransaction(transactionModel);

        verify(repository, times(1)).addTransaction(any(TransactionModel.class));
    }

    @Test
    public void shouldThrowValidationExceptionWhenTransactionHasNullFields() {
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            service.createTransaction(new TransactionModel());
        });

        assertTrue(thrown.getErrors().contains("Fields cannot be null"));
    }

    @Test
    public void shouldThrowValidationExceptionWhenTransactionValueIsNegative() {
        transactionModel = new TransactionModel(-100.0, OffsetDateTime.now().minusHours(1));
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            service.createTransaction(transactionModel);
        });

        assertTrue(thrown.getErrors().contains("Transaction value cannot be negative."));
    }

    @Test
    public void shouldThrowValidationExceptionWhenTransactionTimeIsInFuture() {
        transactionModel = new TransactionModel(100.0, OffsetDateTime.now().plusHours(1));
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            service.createTransaction(transactionModel);
        });

        assertTrue(thrown.getErrors().contains("Transaction time cannot be in the future."));
    }

    @Test
    public void shouldReturnTransactionListSuccessfully() {
        transactionModel = new TransactionModel(100.0, OffsetDateTime.parse("2025-01-24T05:26:45+00:00"));
        when(service.getTransactions()).thenReturn(Collections.singletonList(transactionModel));

        List<TransactionModel> transactions = service.getTransactions();
        TransactionModel transaction = transactions.get(0);

        assertEquals(transaction, transactionModel);
        verify(repository, times(1)).getTransactions();
    }

    @Test
    public void shouldDeleteAllTransactionsSuccessfully(){
        service.deleteTransactions();

        verify(repository, times(1)).deleteTransactions();
    }

    @Test
    public void shouldReturnTransactionStatisticsSuccessfully(){
        TransactionModel transaction = new TransactionModel(100.0, OffsetDateTime.now());
        TransactionModel transaction1 = new TransactionModel(30.0, OffsetDateTime.now());
        when(repository.getTransactions()).thenReturn(List.of(transaction, transaction1));

        DoubleSummaryStatistics doubleSummaryStatistics = service.calculateStatistics(1);
        Assertions.assertEquals( 2, doubleSummaryStatistics.getCount());
        Assertions.assertEquals(100.0, doubleSummaryStatistics.getMax());
        Assertions.assertEquals(30.0, doubleSummaryStatistics.getMin());
        Assertions.assertEquals(130, doubleSummaryStatistics.getSum());

        verify(repository, times(1)).getTransactions();
    }
}
