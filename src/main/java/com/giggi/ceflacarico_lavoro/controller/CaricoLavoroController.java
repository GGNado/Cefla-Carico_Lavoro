package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.MessageResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCaricoLavoroById(@PathVariable Long id) {
        return ResponseEntity.ok(
                caricoLavoroMapper.convert(caricoLavoroService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<?> createCaricoLavoro(@RequestBody CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO) {
        return ResponseEntity.ok(
                caricoLavoroMapper.convert(caricoLavoroService.save(caricoLavoroCreateRequestDTO))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCaricoLavoro(@PathVariable Long id, @RequestBody CaricoLavoroUpdateRequestDTO dto) {
        return ResponseEntity.ok(
                caricoLavoroMapper.convert(caricoLavoroService.update(id, dto))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaricoLavoro(@PathVariable Long id) {
        caricoLavoroService.softDeleteById(id);
        return ResponseEntity.ok(MessageResponse.success("CaricoLavoro eliminato con successo"));
    }
}