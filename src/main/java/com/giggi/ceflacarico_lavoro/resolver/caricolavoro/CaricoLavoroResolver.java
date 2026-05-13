package com.giggi.ceflacarico_lavoro.resolver.caricolavoro;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Attivita;
import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.entity.Utente;
import com.giggi.ceflacarico_lavoro.repository.AttivitaRepository;
import com.giggi.ceflacarico_lavoro.repository.CaricoLavoroRepository;
import com.giggi.ceflacarico_lavoro.repository.CollaboratoreRepository;
import com.giggi.ceflacarico_lavoro.repository.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CaricoLavoroResolver {
    private final CollaboratoreRepository collaboratoreRepository;
    private final AttivitaRepository attivitaRepository;
    private final UtenteRepository utenteRepository;

    public CaricoLavoroContext resolve(CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO) {
        Collaboratore collaboratore = collaboratoreRepository.findById(caricoLavoroCreateRequestDTO.getIdCollaboratore())
                .orElseThrow(() -> new EntityNotFoundException("Collaboratore non trovato"));

        Attivita attivita = attivitaRepository.findById(caricoLavoroCreateRequestDTO.getIdAttivita())
                .orElseThrow(() -> new EntityNotFoundException("Attivita non trovata"));

        Utente utente = utenteRepository.findById(caricoLavoroCreateRequestDTO.getIdUtente())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        return new CaricoLavoroContext(collaboratore, attivita, utente, caricoLavoroCreateRequestDTO);
    }

}
