package com.giggi.ceflacarico_lavoro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;
import com.giggi.ceflacarico_lavoro.service.CaricoLavoroService;

@RestController
@RequestMapping("/api/caricoLavoros")
@RequiredArgsConstructor
public class CaricoLavoroController {
    private final CaricoLavoroService caricoLavoroService;

    @GetMapping
    public List<CaricoLavoro> getAllCaricoLavoros() {
        return caricoLavoroService.findAll();
    }
    // CRUD endpoints qui
}