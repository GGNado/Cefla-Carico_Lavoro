package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindAllDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindDTO;
import com.giggi.ceflacarico_lavoro.service.AttivitaCommessaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttivitaCommessaController {

    private final AttivitaCommessaService attivitaCommessaService;

    @GetMapping("/commesse/{codiceCommessa}/attivita")
    public ResponseEntity<AttivitaCommessaFindAllDTO> findAllByCommessa(@PathVariable String codiceCommessa) {
        return ResponseEntity.ok(attivitaCommessaService.findAllByCommessa(codiceCommessa));
    }

    @PostMapping("/commesse/{codiceCommessa}/attivita")
    public ResponseEntity<AttivitaCommessaFindDTO> create(
            @PathVariable String codiceCommessa,
            @RequestBody AttivitaCommessaCreateRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attivitaCommessaService.create(codiceCommessa, dto));
    }

    @PatchMapping("/attivita-commesse/{id}")
    public ResponseEntity<AttivitaCommessaFindDTO> update(
            @PathVariable Long id,
            @RequestBody AttivitaCommessaUpdateRequestDTO dto) {
        return ResponseEntity.ok(attivitaCommessaService.update(id, dto));
    }

    @DeleteMapping("/attivita-commesse/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        attivitaCommessaService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
