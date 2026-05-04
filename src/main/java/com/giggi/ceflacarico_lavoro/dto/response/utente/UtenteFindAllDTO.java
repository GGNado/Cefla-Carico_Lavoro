package com.giggi.ceflacarico_lavoro.dto.response.utente;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UtenteFindAllDTO {
    private List<UtenteFindDTO> UtenteFindAllDTO;
}