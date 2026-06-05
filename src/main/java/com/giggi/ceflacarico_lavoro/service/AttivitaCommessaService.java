package com.giggi.ceflacarico_lavoro.service;

import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindAllDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindDTO;

public interface AttivitaCommessaService {
    AttivitaCommessaFindDTO create(String codiceCommessa, AttivitaCommessaCreateRequestDTO dto);
    AttivitaCommessaFindDTO update(Long id, AttivitaCommessaUpdateRequestDTO dto);
    void softDelete(Long id);
    AttivitaCommessaFindAllDTO findAllByCommessa(String codiceCommessa);
}
