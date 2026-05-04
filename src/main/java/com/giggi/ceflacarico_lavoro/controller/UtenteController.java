package com.giggi.ceflacarico_lavoro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Utente;
import com.giggi.ceflacarico_lavoro.service.UtenteService;

@RestController
@RequestMapping("/api/utentes")
@RequiredArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;

    @GetMapping
    public List<Utente> getAllUtentes() {
        return utenteService.findAll();
    }
    // CRUD endpoints qui
}