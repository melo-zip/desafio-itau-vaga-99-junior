package com.example.desafio_backend_itau.repositories;

import com.example.desafio_backend_itau.models.TransactionModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    List<TransactionModel> transactions = new ArrayList<>();

    public List<TransactionModel> getTransactions() {
        return transactions;
    }

    public void addTransaction(TransactionModel transactionModel) {
        transactions.add(transactionModel);
    }

    public void deleteTransactions(){
        transactions.clear();
    }
}
