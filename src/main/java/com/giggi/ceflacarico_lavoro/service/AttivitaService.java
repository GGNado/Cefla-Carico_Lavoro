package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Attivita;

public interface AttivitaService {
    Attivita save(Attivita attivita);

    Attivita update(AttivitaUpdateRequestDTO attivitaUpdateRequestDTO);

    Attivita softDeleteById(Long id);

    List<Attivita> findAll();

    Attivita findById(Long id);
}