package com.giggi.ceflacarico_lavoro.dto.response.commessa;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CommessaFindDTO {
    private Long id;
    private String codice;
    private String descrizione;
    private String tipo;
    private String responsabile;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private LocalDate stimaInizio;
    private LocalDate stimaFine;
    private Integer giornateStimate;
    private boolean attivo;
}
