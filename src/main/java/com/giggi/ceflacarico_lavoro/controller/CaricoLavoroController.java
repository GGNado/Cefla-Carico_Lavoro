package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.mapper.CaricoLavoroMapper;
import com.giggi.ceflacarico_lavoro.service.CaricoLavoroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caricoLavoro")
@RequiredArgsConstructor
public class CaricoLavoroController {
    private final CaricoLavoroService caricoLavoroService;
    private final CaricoLavoroMapper caricoLavoroMapper;

    @GetMapping
    public ResponseEntity<?> getAllCaricoLavoro() {

        return ResponseEntity.ok(
                caricoLavoroMapper.convert(caricoLavoroService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<?> createCaricoLavoro(@RequestBody CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO) {
        return ResponseEntity.ok(
                caricoLavoroMapper.convert(caricoLavoroService.save(caricoLavoroCreateRequestDTO))
        );
    }
}