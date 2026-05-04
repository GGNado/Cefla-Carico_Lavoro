package com.giggi.ceflacarico_lavoro.service.impl;

import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Utente;
import com.giggi.ceflacarico_lavoro.mapper.CollaboratoreMapper;
import com.giggi.ceflacarico_lavoro.repository.UtenteRepository;
import com.giggi.ceflacarico_lavoro.validator.collaboratore.CollaboratoreValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.repository.CollaboratoreRepository;
import com.giggi.ceflacarico_lavoro.service.CollaboratoreService;
import org.springframework.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class CollaboratoreServiceImpl implements CollaboratoreService {

    private final CollaboratoreRepository collaboratoreRepository;
    private final UtenteRepository utenteRepository;
    private final CollaboratoreValidator collaboratoreValidator;
    private final CollaboratoreMapper collaboratoreMapper;

    @Override
    public Collaboratore save(CollaboratoreCreateRequestDTO collaboratoreCreateRequestDTO) {
        collaboratoreValidator.validate(collaboratoreCreateRequestDTO);

        Collaboratore collaboratore = collaboratoreMapper.convert(collaboratoreCreateRequestDTO);

        if (StringUtils.hasText(collaboratoreCreateRequestDTO.getEmail())) {
            Utente utente = utenteRepository.findByEmail(collaboratoreCreateRequestDTO.getEmail());
            collaboratore.setUserAccount(utente);
            utente.setCollaborator(collaboratore);
        }

        return collaboratoreRepository.save(collaboratore);

    }

    @Override
    public Collaboratore update(Collaboratore collaboratore) {
        return collaboratoreRepository.save(collaboratore);
    }

    @Override
    public void deleteById(Long id) {
        collaboratoreRepository.deleteById(id);
    }

    @Override
    public List<Collaboratore> findAll() {
        return collaboratoreRepository.findAll();
    }

    @Override
    public Collaboratore findById(Long id) {
        return collaboratoreRepository.findById(id).orElse(null);
    }
}