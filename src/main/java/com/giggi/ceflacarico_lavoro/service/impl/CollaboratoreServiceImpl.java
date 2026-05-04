package com.giggi.ceflacarico_lavoro.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.repository.CollaboratoreRepository;
import com.giggi.ceflacarico_lavoro.service.CollaboratoreService;

@Service
@Transactional
@RequiredArgsConstructor
public class CollaboratoreServiceImpl implements CollaboratoreService {

    private final CollaboratoreRepository collaboratoreRepository;

    @Override
    public Collaboratore save(Collaboratore collaboratore) {
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