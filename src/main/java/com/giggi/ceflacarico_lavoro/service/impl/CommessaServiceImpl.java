package com.giggi.ceflacarico_lavoro.service.impl;

import com.giggi.ceflacarico_lavoro.dto.request.commessa.CommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.mapper.CommessaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Commessa;
import com.giggi.ceflacarico_lavoro.repository.CommessaRepository;
import com.giggi.ceflacarico_lavoro.service.CommessaService;

@Service
@Transactional
@RequiredArgsConstructor
public class CommessaServiceImpl implements CommessaService {

    private final CommessaRepository commessaRepository;
    private final CommessaMapper commessaMapper;

    @Override
    public Commessa save(Commessa commessa) {
        if (commessaRepository.existsByCodice(commessa.getCodice())) {
            throw new RuntimeException("Commessa con codice '" + commessa.getCodice() + "' già esistente");
        }
        return commessaRepository.save(commessa);
    }

    @Override
    public Commessa update(String codice, CommessaUpdateRequestDTO commessaUpdateRequestDTO) {
        Commessa commessa = commessaRepository.findByCodice(codice).orElseThrow(
                () -> new RuntimeException("Commessa non trovata con codice: " + codice)
        );

        commessaMapper.updateCommessaFromDTO(commessaUpdateRequestDTO, commessa);

        return commessaRepository.save(commessa);
    }

    @Override
    public Commessa softDeleteByCodice(String codice) {
        Commessa commessa = commessaRepository.findByCodice(codice).orElseThrow(
                () -> new RuntimeException("Commessa non trovata con codice: " + codice)
        );

        commessa.setAttivo(false);
        return commessaRepository.save(commessa);
    }

    @Override
    public List<Commessa> findAll() {
        return commessaRepository.findAllByAttivoTrueOrderByCodiceAsc();
    }

    @Override
    public Commessa findById(Long id) {
        return commessaRepository.findById(id).orElse(null);
    }

    @Override
    public Commessa findByCodice(String codice) {
        return commessaRepository.findByCodice(codice).orElseThrow(
                () -> new RuntimeException("Commessa non trovata con codice: " + codice)
        );
    }
}
