package com.giggi.ceflacarico_lavoro.resolver.caricolavoro;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.AttivitaCommessa;
import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.entity.Utente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

public record CaricoLavoroContext (
    Collaboratore collaboratore,
    AttivitaCommessa attivitaCommessa,
    Utente utente,
    CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO
){}
