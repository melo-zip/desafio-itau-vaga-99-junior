package com.example.desafio_backend_itau.dtos;

import java.time.OffsetDateTime;

public record TransactionRecordDto(Double value, OffsetDateTime time) {
}
