package com.example.desafio_backend_itau.controllers;

import com.example.desafio_backend_itau.dtos.TransactionRecordDto;
import com.example.desafio_backend_itau.exceptions.ValidationException;
import com.example.desafio_backend_itau.models.TransactionModel;
import com.example.desafio_backend_itau.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
    @InjectMocks
    TransactionController controller;

    @Mock
    TransactionService service;

    TransactionModel transactionModel;

    TransactionRecordDto recordDto;

    @BeforeEach
    public void setUp() {
        recordDto = new TransactionRecordDto(100.0, OffsetDateTime.parse("2025-01-01T01:00:00+00:00"));
        transactionModel = new TransactionModel(100.0, OffsetDateTime.parse("2025-01-01T01:00:00+00:00"));
    }

    @Test
    public void shouldCreateTransactionSuccessfully() {
        ResponseEntity<Object> response = controller.createTransaction(recordDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).createTransaction(any(TransactionModel.class));
    }

    @Test
    public void shouldReturnValidationErrorWhenTransactionIsInvalid(){
        TransactionRecordDto invalidDto = new TransactionRecordDto(-100.0, OffsetDateTime.now());

        doThrow(new ValidationException(Collections.singletonList("valid")))
                .when(service)
                .createTransaction(any(TransactionModel.class));

        ResponseEntity<Object> response = controller.createTransaction(invalidDto);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    public void shouldReturnAllTransactionsSuccessfully(){
        when(service.getTransactions()).thenReturn(Collections.singletonList(transactionModel));
        ResponseEntity<List<TransactionModel>> response = controller.getAllTransactions();
        TransactionModel transaction = response.getBody().get(0);
        Assertions.assertEquals(transaction, transactionModel);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());

        verify(service, times(1)).getTransactions();
    }

    @Test
    public void shouldClearAllTransactionsSuccessfully(){
        ResponseEntity<TransactionModel> response = controller.deleteTransactions();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).deleteTransactions();
    }
}
