package com.giggi.ceflacarico_lavoro.service;

import java.util.List;

import com.giggi.ceflacarico_lavoro.dto.request.commessa.CommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Commessa;

public interface CommessaService {
    Commessa save(Commessa commessa);

    Commessa update(String codice, CommessaUpdateRequestDTO commessaUpdateRequestDTO);

    Commessa softDeleteByCodice(String codice);

    List<Commessa> findAll();

    Commessa findById(Long id);

    Commessa findByCodice(String codice);
}
