package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.commessa.CommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.commessa.CommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.commessa.CommessaFindAllDTO;
import com.giggi.ceflacarico_lavoro.mapper.CommessaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.giggi.ceflacarico_lavoro.service.CommessaService;

@RestController
@RequestMapping("/api/commesse")
@RequiredArgsConstructor
public class CommessaController {
    private final CommessaService commessaService;
    private final CommessaMapper commessaMapper;

    @GetMapping
    public ResponseEntity<?> getAllCommesse() {
        return ResponseEntity.ok().body(
                new CommessaFindAllDTO(
                        commessaService.findAll()
                                .stream()
                                .map(commessaMapper::convert)
                                .toList()
                )
        );
    }

    @GetMapping("/{codice}")
    public ResponseEntity<?> getCommessaByCodice(@PathVariable String codice) {
        return ResponseEntity.ok(
                commessaMapper.convert(
                        commessaService.findByCodice(codice)
                )
        );
    }

    @PostMapping
    public ResponseEntity<?> createCommessa(@RequestBody CommessaCreateRequestDTO commessaCreateRequestDTO) {
        return ResponseEntity.ok(
                commessaMapper.convert(
                        commessaService.save(
                                commessaMapper.convert(commessaCreateRequestDTO)
                        )
                )
        );
    }

    @PatchMapping("/{codice}")
    public ResponseEntity<?> updateCommessa(@PathVariable String codice,
                                            @RequestBody CommessaUpdateRequestDTO commessaUpdateRequestDTO) {
        return ResponseEntity.ok(
                commessaMapper.convert(
                        commessaService.update(codice, commessaUpdateRequestDTO)
                )
        );
    }

    @DeleteMapping("/{codice}")
    public ResponseEntity<?> softDelete(@PathVariable String codice) {
        return ResponseEntity.ok(
                commessaMapper.convert(
                        commessaService.softDeleteByCodice(codice)
                )
        );
    }
}
