package com.example.desafio_backend_itau.helpers;

import com.example.desafio_backend_itau.models.TransactionModel;

import java.time.OffsetDateTime;

public interface TransactionValidator {
    default boolean validate(TransactionModel transactionModel){
        return transactionModel.getValue() >= 0  && transactionModel.getTime().isBefore(OffsetDateTime.now());
    }
}
