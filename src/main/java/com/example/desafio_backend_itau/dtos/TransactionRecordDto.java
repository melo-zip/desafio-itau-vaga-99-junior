package com.example.desafio_backend_itau.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record TransactionRecordDto(@NotNull(message = "value field cannot be null") Double value,@NotNull(message = "time field cannot be null") OffsetDateTime time) {
}
