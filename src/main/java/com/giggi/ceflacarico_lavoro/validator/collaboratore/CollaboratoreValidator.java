package com.giggi.ceflacarico_lavoro.validator.collaboratore;

import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.entity.Utente;
import com.giggi.ceflacarico_lavoro.repository.CollaboratoreRepository;
import com.giggi.ceflacarico_lavoro.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CollaboratoreValidator {
    private final CollaboratoreRepository collaboratoreRepository;
    private final UtenteRepository utenteRepository;

    public void validate(CollaboratoreCreateRequestDTO collaboratoreCreateRequestDTO) {
        if (collaboratoreCreateRequestDTO.getEmail().isEmpty()) {
            log.info("Email is empty, nessun account collegato");
            return;
        }

        Utente utente = utenteRepository.findByEmail(collaboratoreCreateRequestDTO.getEmail());

        if (utente == null) {
            throw new IllegalArgumentException("Utente non trovato");
        }

        Collaboratore collaboratore = collaboratoreRepository.findByUserAccount_Email(collaboratoreCreateRequestDTO.getEmail());
        if (collaboratore != null){
            throw new IllegalArgumentException("Esiste già un collaboratore con questa email");
        }
    }

    public void validate(CollaboratoreUpdateRequestDTO collaboratoreUpdateRequestDTO) {

        if (collaboratoreUpdateRequestDTO.getEmail() == null) {
            log.info("Email is null, devo scollegare l'account utente");
            return;
        }

        Utente utente = utenteRepository.findByEmail(collaboratoreUpdateRequestDTO.getEmail());

        if (utente == null) {
            throw new IllegalArgumentException("Utente non trovato");
        }

        log.info("Utente trovato: {}", utente.getEmail());
    }
}
