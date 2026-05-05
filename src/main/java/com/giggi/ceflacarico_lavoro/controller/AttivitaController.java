package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.mapper.AttivitaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Attivita;
import com.giggi.ceflacarico_lavoro.service.AttivitaService;

@RestController
@RequestMapping("/api/attivita")
@RequiredArgsConstructor
public class AttivitaController {
    private final AttivitaService attivitaService;
    private final AttivitaMapper attivitaMapper;

    @GetMapping
    public List<Attivita> getAllAttivitas() {
        return attivitaService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createAttivita(@RequestBody AttivitaCreateRequestDTO attivitaCreateRequestDTO) {
        return ResponseEntity.ok(
                attivitaMapper.convert(
                        attivitaService.save(
                                attivitaMapper.convert(attivitaCreateRequestDTO)
                        )
                )
        );
    }
}