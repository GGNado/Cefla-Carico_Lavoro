package com.giggi.ceflacarico_lavoro.dto.request.caricolavoro;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class CaricoLavoroCreateRequestDTO {
    private Long idAttivita;
    private Long idCollaboratore;
    private Long idUtente;
    private LocalDate inputDate;
    private Integer quantity;
    private BigDecimal estimatedTime;
    private String notes;

}