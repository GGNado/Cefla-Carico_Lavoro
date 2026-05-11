package com.giggi.ceflacarico_lavoro.dto.request.attivita;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AttivitaUpdateRequestDTO {
    private Long id;
    private String name;
    private BigDecimal averageTime;
    private boolean active;

}