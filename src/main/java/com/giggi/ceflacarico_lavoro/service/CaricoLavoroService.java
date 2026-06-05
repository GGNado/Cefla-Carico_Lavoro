package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;

public interface CaricoLavoroService {
    CaricoLavoro save(CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO);

    CaricoLavoro update(Long id, CaricoLavoroUpdateRequestDTO caricoLavoroUpdateRequestDTO);

    void softDeleteById(Long id);

    List<CaricoLavoro> findAll();

    CaricoLavoro findById(Long id);
}