package com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class AttivitaCommessaCreateRequestDTO {
    private String nome;
    private String descrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String assegnatario;
}
