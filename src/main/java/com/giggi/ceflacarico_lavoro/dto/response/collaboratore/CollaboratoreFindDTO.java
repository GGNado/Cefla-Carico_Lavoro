package com.giggi.ceflacarico_lavoro.dto.response.collaboratore;

import com.giggi.ceflacarico_lavoro.dto.response.utente.UtenteFindDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CollaboratoreFindDTO {
    private Long id;
    private String fullName;
    private UtenteFindDTO utente;
}