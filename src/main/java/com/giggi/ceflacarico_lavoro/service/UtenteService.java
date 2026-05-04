package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Utente;

public interface UtenteService {
    Utente save(Utente utente);

    Utente update(Utente utente);

    void deleteById(Long id);

    List<Utente> findAll();

    Utente findById(Long id);
}