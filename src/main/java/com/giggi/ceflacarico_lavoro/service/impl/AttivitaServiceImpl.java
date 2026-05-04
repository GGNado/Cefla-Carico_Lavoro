package com.giggi.ceflacarico_lavoro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Attivita;
import com.giggi.ceflacarico_lavoro.repository.AttivitaRepository;
import com.giggi.ceflacarico_lavoro.service.AttivitaService;

@Service
@Transactional
@RequiredArgsConstructor
public class AttivitaServiceImpl implements AttivitaService {

    private final AttivitaRepository attivitaRepository;

    @Override
    public Attivita save(Attivita attivita) {
        return attivitaRepository.save(attivita);
    }

    @Override
    public Attivita update(Attivita attivita) {
        return attivitaRepository.save(attivita);
    }

    @Override
    public void deleteById(Long id) {
        attivitaRepository.deleteById(id);
    }

    @Override
    public List<Attivita> findAll() {
        return attivitaRepository.findAll();
    }

    @Override
    public Attivita findById(Long id) {
        return attivitaRepository.findById(id).orElse(null);
    }
}