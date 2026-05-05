package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.response.utente.UtenteFindAllDTO;
import com.giggi.ceflacarico_lavoro.mapper.UtenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Utente;
import com.giggi.ceflacarico_lavoro.service.UtenteService;

@RestController
@RequestMapping("/api/utenti")
@RequiredArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;
    private final UtenteMapper utenteMapper;

    @GetMapping
    public ResponseEntity<UtenteFindAllDTO> getAllUtenti() {
        return ResponseEntity.ok(
                new UtenteFindAllDTO(
                        utenteService
                                .findAll().stream()
                                .map(utenteMapper::convert)
                                .toList())
        );
    }
}