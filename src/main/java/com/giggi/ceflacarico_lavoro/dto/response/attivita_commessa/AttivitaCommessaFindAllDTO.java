package com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttivitaCommessaFindAllDTO {
    private List<AttivitaCommessaFindDTO> attivita;
}
