package com.giggi.ceflacarico_lavoro.dto.request.caricolavoro;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class CaricoLavoroUpdateRequestDTO {
    private Long id;
    private Long idAttivitaCommessa;
    private Long idCollaboratore;
    private LocalDate inputDate;
    private Integer quantity;
    private BigDecimal estimatedTime;
    private String notes;
}