package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita.AttivitaFindAllDTO;
import com.giggi.ceflacarico_lavoro.mapper.AttivitaMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<?> getAllAttivita() {
        return ResponseEntity.ok().body(
                new AttivitaFindAllDTO(
                        attivitaService.findAll()
                                .stream()
                                .map(attivitaMapper::convert)
                                .toList()
                )
        );
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

    @PatchMapping
    public ResponseEntity<?> updateAttivita(@RequestBody AttivitaUpdateRequestDTO attivitaUpdateRequestDTO) {
        return ResponseEntity.ok(
                attivitaMapper.convert(
                        attivitaService.update(attivitaUpdateRequestDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {

        return ResponseEntity.ok(
                attivitaMapper.convert(
                        attivitaService.softDeleteById(id)
                )
        );
    }
}