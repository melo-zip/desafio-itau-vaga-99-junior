package com.example.desafio_backend_itau.models;

import java.time.OffsetDateTime;

public class TransactionModel {
    private Double value;
    private OffsetDateTime time;

    public TransactionModel() {
    }

    public TransactionModel(Double value, OffsetDateTime time) {
        this.value = value;
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }
}
