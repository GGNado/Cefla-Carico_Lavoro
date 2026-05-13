package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;

public interface CaricoLavoroService {
    CaricoLavoro save(CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO);

    CaricoLavoro update(CaricoLavoro caricoLavoro);

    void deleteById(Long id);

    List<CaricoLavoro> findAll();

    CaricoLavoro findById(Long id);
}