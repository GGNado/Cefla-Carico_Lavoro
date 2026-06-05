package com.giggi.ceflacarico_lavoro.service.impl;

import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Attivita;
import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.mapper.CaricoLavoroMapper;
import com.giggi.ceflacarico_lavoro.repository.AttivitaRepository;
import com.giggi.ceflacarico_lavoro.repository.CollaboratoreRepository;
import com.giggi.ceflacarico_lavoro.resolver.caricolavoro.CaricoLavoroContext;
import com.giggi.ceflacarico_lavoro.resolver.caricolavoro.CaricoLavoroResolver;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;
import com.giggi.ceflacarico_lavoro.repository.CaricoLavoroRepository;
import com.giggi.ceflacarico_lavoro.service.CaricoLavoroService;

@Service
@Transactional
@RequiredArgsConstructor
public class CaricoLavoroServiceImpl implements CaricoLavoroService {

    private final CaricoLavoroRepository caricoLavoroRepository;
    private final CaricoLavoroMapper caricoLavoroMapper;
    private final CaricoLavoroResolver caricoLavoroResolver;
    private final AttivitaRepository attivitaRepository;
    private final CollaboratoreRepository collaboratoreRepository;

    @Override
    public CaricoLavoro save(CaricoLavoroCreateRequestDTO caricoLavoroCreateRequestDTO) {
        CaricoLavoroContext caricoLavoroContext = caricoLavoroResolver.resolve(caricoLavoroCreateRequestDTO);
        CaricoLavoro caricoLavoro = caricoLavoroMapper.convert(caricoLavoroCreateRequestDTO);
        caricoLavoro.setCollaborator(caricoLavoroContext.collaboratore());
        caricoLavoro.setActivityType(caricoLavoroContext.attivita());
        caricoLavoro.setCreatedBy(caricoLavoroContext.utente());
        return caricoLavoroRepository.save(caricoLavoro);
    }

    @Override
    public CaricoLavoro update(Long id, CaricoLavoroUpdateRequestDTO dto) {
        CaricoLavoro existing = caricoLavoroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CaricoLavoro non trovato con id: " + id));

        if (dto.getInputDate() != null) {
            existing.setInputDate(dto.getInputDate());
        }
        if (dto.getQuantity() != null) {
            existing.setQuantity(dto.getQuantity());
        }
        if (dto.getEstimatedTime() != null) {
            existing.setEstimatedTime(dto.getEstimatedTime());
        }
        if (dto.getNotes() != null) {
            existing.setNotes(dto.getNotes());
        }
        if (dto.getIdAttivita() != null) {
            Attivita attivita = attivitaRepository.findById(dto.getIdAttivita())
                    .orElseThrow(() -> new EntityNotFoundException("Attivita non trovata"));
            existing.setActivityType(attivita);
        }
        if (dto.getIdCollaboratore() != null) {
            Collaboratore collaboratore = collaboratoreRepository.findById(dto.getIdCollaboratore())
                    .orElseThrow(() -> new EntityNotFoundException("Collaboratore non trovato"));
            existing.setCollaborator(collaboratore);
        }

        return caricoLavoroRepository.save(existing);
    }

    @Override
    public void softDeleteById(Long id) {
        CaricoLavoro caricoLavoro = caricoLavoroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CaricoLavoro non trovato con id: " + id));
        caricoLavoro.setDeleted(true);
        caricoLavoro.setDeletedAt(LocalDateTime.now());
        caricoLavoroRepository.save(caricoLavoro);
    }

    @Override
    public List<CaricoLavoro> findAll() {
        return caricoLavoroRepository.findAllByDeletedFalseOrderByCreatedAtDesc();
    }

    @Override
    public CaricoLavoro findById(Long id) {
        return caricoLavoroRepository.findById(id)
                .filter(cl -> !cl.isDeleted())
                .orElseThrow(() -> new EntityNotFoundException("CaricoLavoro non trovato con id: " + id));
    }
}