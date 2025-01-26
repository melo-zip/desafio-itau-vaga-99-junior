package com.example.desafio_backend_itau.services;

import com.example.desafio_backend_itau.helpers.TransactionValidator;
import com.example.desafio_backend_itau.models.TransactionModel;
import com.example.desafio_backend_itau.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionValidator {
    @Autowired
    private TransactionRepository repository;

    public TransactionModel createTransaction(TransactionModel transactionModel) {
            if(!validate(transactionModel)){
                    throw new IllegalArgumentException();
            }
        return repository.addTransaction(transactionModel);
    }

    public List<TransactionModel> getTransactions(){
        return repository.getTransactions();
    }

    public void deleteTransactions(){
        repository.deleteTransactions();
    }
}
