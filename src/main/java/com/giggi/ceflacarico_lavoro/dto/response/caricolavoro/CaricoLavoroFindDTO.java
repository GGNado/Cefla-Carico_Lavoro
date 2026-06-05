package com.giggi.ceflacarico_lavoro.dto.response.caricolavoro;

import lombok.Getter;
import lombok.Setter;

import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindDTO;

@Setter
@Getter
public class CaricoLavoroFindDTO {
    private Long id;
    private AttivitaCommessaFindDTO attivitaCommessa;
    private String nomeCollaboratore;
    private String nomeUtente;
    private String inputDate;
    private Integer quantity;
    private String estimatedTime;
    private String notes;
}