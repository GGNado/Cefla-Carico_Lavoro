package com.giggi.ceflacarico_lavoro.controller;

import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.collaboratore.CollaboratoreFindAllDTO;
import com.giggi.ceflacarico_lavoro.dto.response.collaboratore.CollaboratoreFindDTO;
import com.giggi.ceflacarico_lavoro.mapper.CollaboratoreMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.service.CollaboratoreService;

@RestController
@RequestMapping("/api/collaboratori")
@RequiredArgsConstructor
public class CollaboratoreController {
    private final CollaboratoreService collaboratoreService;
    private final CollaboratoreMapper collaboratoreMapper;

    @GetMapping
    public ResponseEntity<?> getAllCollaboratore() {
        return ResponseEntity.ok().body(
                new CollaboratoreFindAllDTO(
                        collaboratoreService.findAll()
                                .stream()
                                .map(collaboratoreMapper::convert)
                                .toList()
                )
        );
    }

    @PostMapping
    public ResponseEntity<?> createCollaboratore(
            @RequestBody CollaboratoreCreateRequestDTO collaboratoreCreateRequestDTO) {
        return ResponseEntity.ok(
                collaboratoreMapper.convert(
                        collaboratoreService.save(collaboratoreCreateRequestDTO)
                )
        );
    }

    @Operation(
            summary = "Get all collaboratori",
            description = "Roles: ADMIN, MANAGER"
    )
    @PatchMapping
    public ResponseEntity<?> updateCollaboratore(@RequestBody CollaboratoreUpdateRequestDTO collaboratoreUpdateRequestDTO) {
        return ResponseEntity.ok(
                collaboratoreMapper.convert(
                        collaboratoreService.update(collaboratoreUpdateRequestDTO)
                )
        );
    }

}