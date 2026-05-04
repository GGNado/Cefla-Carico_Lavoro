package com.giggi.ceflacarico_lavoro.service.impl;

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

    @Override
    public CaricoLavoro save(CaricoLavoro caricoLavoro) {
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
        return caricoLavoroRepository.findAll();
    }

    @Override
    public CaricoLavoro findById(Long id) {
        return caricoLavoroRepository.findById(id).orElse(null);
    }
}