package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Attivita;

public interface AttivitaService {
    Attivita save(Attivita attivita);

    Attivita update(Attivita attivita);

    void deleteById(Long id);

    List<Attivita> findAll();

    Attivita findById(Long id);
}