package com.giggi.ceflacarico_lavoro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Attivita;
import com.giggi.ceflacarico_lavoro.service.AttivitaService;

@RestController
@RequestMapping("/api/attivitas")
@RequiredArgsConstructor
public class AttivitaController {
    private final AttivitaService attivitaService;

    @GetMapping
    public List<Attivita> getAllAttivitas() {
        return attivitaService.findAll();
    }
    // CRUD endpoints qui
}