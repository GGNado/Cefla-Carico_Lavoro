package com.giggi.ceflacarico_lavoro.dto.response.attivita;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AttivitaFindDTO {
    private Long id;
    private String name;
    private BigDecimal averageTime;
}