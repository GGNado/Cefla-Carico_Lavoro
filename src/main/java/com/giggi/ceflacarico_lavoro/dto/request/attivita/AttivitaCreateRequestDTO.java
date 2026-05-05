package com.giggi.ceflacarico_lavoro.dto.request.attivita;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AttivitaCreateRequestDTO {
    private String name;
    private BigDecimal averageTime;
}