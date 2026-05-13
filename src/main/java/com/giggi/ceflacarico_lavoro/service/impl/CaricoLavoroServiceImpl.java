package com.giggi.ceflacarico_lavoro.service.impl;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.mapper.CaricoLavoroMapper;
import com.giggi.ceflacarico_lavoro.resolver.caricolavoro.CaricoLavoroContext;
import com.giggi.ceflacarico_lavoro.resolver.caricolavoro.CaricoLavoroResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;
import com.giggi.ceflacarico_lavoro.repository.CaricoLavoroRepository;
import com.giggi.ceflacarico_lavoro.service.CaricoLavoroService;

@Service
@Transactional
@RequiredArgsConstructor
public class CaricoLavoroServiceImpl implements CaricoLavoroService {

    private final CaricoLavoroRepository caricoLavoroRepository;
    private final CaricoLavoroMapper caricoLavoroMapper;
    private final CaricoLavoroResolver caricoLavoroResolver;

    @Override
    public CaricoLavoro save(CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO) {
        CaricoLavoroContext caricoLavoroContext = caricoLavoroResolver.resolve(caricoLavoroCreateRequestDTO);
        CaricoLavoro caricoLavoro = caricoLavoroMapper.convert(caricoLavoroCreateRequestDTO);
        caricoLavoro.setCollaborator(caricoLavoroContext.collaboratore());
        caricoLavoro.setActivityType(caricoLavoroContext.attivita());
        caricoLavoro.setCreatedBy(caricoLavoroContext.utente());
        return caricoLavoroRepository.save(caricoLavoro);
    }

    @Override
    public CaricoLavoro update(CaricoLavoro caricoLavoro) {
        return caricoLavoroRepository.save(caricoLavoro);
    }

    @Override
    public void deleteById(Long id) {
        caricoLavoroRepository.deleteById(id);
    }

    @Override
    public List<CaricoLavoro> findAll() {
        return caricoLavoroRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public CaricoLavoro findById(Long id) {
        return caricoLavoroRepository.findById(id).orElse(null);
    }
}