package com.giggi.ceflacarico_lavoro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.service.CollaboratoreService;

@RestController
@RequestMapping("/api/collaboratores")
@RequiredArgsConstructor
public class CollaboratoreController {
    private final CollaboratoreService collaboratoreService;

    @GetMapping
    public List<Collaboratore> getAllCollaboratores() {
        return collaboratoreService.findAll();
    }
    // CRUD endpoints qui
}