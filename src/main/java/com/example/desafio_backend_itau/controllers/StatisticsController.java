package com.example.desafio_backend_itau.controllers;

import com.example.desafio_backend_itau.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    @GetMapping("/statistic")
    public ResponseEntity<DoubleSummaryStatistics> listStatistics(@RequestParam(value = "minutes", defaultValue = "1") int minutes) {
        DoubleSummaryStatistics stats = service.getStatistics(minutes);
        return ResponseEntity.status(HttpStatus.OK).body(stats);
    }
}
