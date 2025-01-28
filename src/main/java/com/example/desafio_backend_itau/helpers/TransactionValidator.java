package com.example.desafio_backend_itau.helpers;

import com.example.desafio_backend_itau.models.TransactionModel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public interface TransactionValidator {
    default List<String> validate(TransactionModel transactionModel){
        List<String> errors = new ArrayList<>();

        if (transactionModel.getValue() < 0) {
            errors.add("Transaction value cannot be negative.");
        }
        if (transactionModel.getTime().isAfter(OffsetDateTime.now())) {
            errors.add("Transaction time cannot be in the future.");
        }

        return errors;
    }
}
