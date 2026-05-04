package com.giggi.ceflacarico_lavoro.dto.response.caricolavoro;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CaricoLavoroFindAllDTO {
    private List<CaricoLavoroFindDTO> CaricoLavoroFindAllDTO;
}