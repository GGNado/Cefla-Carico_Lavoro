package com.giggi.ceflacarico_lavoro.dto.response.commessa;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CommessaFindAllDTO {
    private List<CommessaFindDTO> commesse;
}
