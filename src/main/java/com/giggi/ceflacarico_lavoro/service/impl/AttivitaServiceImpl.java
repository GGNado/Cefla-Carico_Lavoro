package com.giggi.ceflacarico_lavoro.service.impl;

import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.mapper.AttivitaMapper;
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
    private final AttivitaMapper attivitaMapper;

    @Override
    public Attivita save(Attivita attivita) {
        return attivitaRepository.save(attivita);
    }

    @Override
    public Attivita update(AttivitaUpdateRequestDTO attivitaUpdateRequestDTO) {
        Attivita attivita = attivitaRepository.findById(attivitaUpdateRequestDTO.getId()).orElseThrow(
                () -> new RuntimeException("Attivita non trovata")
        );

        attivitaMapper.updateAttivitaFromDTO(attivitaUpdateRequestDTO, attivita);

        return attivitaRepository.save(attivita);
    }

    @Override
    public Attivita softDeleteById(Long id) {
        Attivita attivita = attivitaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Attivita non trovata")
        );

        attivita.setActive(false);
        return attivitaRepository.save(attivita);
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