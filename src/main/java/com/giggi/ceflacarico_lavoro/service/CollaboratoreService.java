package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Collaboratore;

public interface CollaboratoreService {
    Collaboratore save(CollaboratoreCreateRequestDTO collaboratoreCreateRequestDTO);

    Collaboratore update(CollaboratoreUpdateRequestDTO collaboratoreUpdateRequestDTO);

    void deleteById(Long id);

    List<Collaboratore> findAll();

    Collaboratore findById(Long id);
}