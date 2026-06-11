package com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class AttivitaCommessaFindDTO {
    private Long id;
    private String commessaCodice;
    private String nome;
    private String descrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String assegnatario;
    private boolean attivo;
}
