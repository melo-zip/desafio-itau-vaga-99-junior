package com.example.desafio_backend_itau.controllers;

import com.example.desafio_backend_itau.dtos.TransactionRecordDto;
import com.example.desafio_backend_itau.models.TransactionModel;
import com.example.desafio_backend_itau.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping("/transaction")
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody @Valid TransactionRecordDto transactionRecordDto){
        TransactionModel transactionModel = new TransactionModel();
        BeanUtils.copyProperties(transactionRecordDto, transactionModel);
        try {
            service.createTransaction(transactionModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionModel>> getAllTransactions(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransactions());
    }

    @DeleteMapping("/transaction")
    public ResponseEntity<TransactionModel> deleteTransactions(){
        service.deleteTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
