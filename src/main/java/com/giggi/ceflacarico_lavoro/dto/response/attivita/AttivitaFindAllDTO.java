package com.giggi.ceflacarico_lavoro.dto.response.attivita;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AttivitaFindAllDTO {
    private List<AttivitaFindDTO> AttivitaFindAllDTO;
}