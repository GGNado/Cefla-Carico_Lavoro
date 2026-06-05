package com.giggi.ceflacarico_lavoro.service.impl;

import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindAllDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindDTO;
import com.giggi.ceflacarico_lavoro.entity.AttivitaCommessa;
import com.giggi.ceflacarico_lavoro.entity.Commessa;
import com.giggi.ceflacarico_lavoro.mapper.AttivitaCommessaMapper;
import com.giggi.ceflacarico_lavoro.repository.AttivitaCommessaRepository;
import com.giggi.ceflacarico_lavoro.repository.CommessaRepository;
import com.giggi.ceflacarico_lavoro.service.AttivitaCommessaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttivitaCommessaServiceImpl implements AttivitaCommessaService {

    private final AttivitaCommessaRepository attivitaCommessaRepository;
    private final CommessaRepository commessaRepository;
    private final AttivitaCommessaMapper attivitaCommessaMapper;

    @Override
    public AttivitaCommessaFindDTO create(String codiceCommessa, AttivitaCommessaCreateRequestDTO dto) {
        Commessa commessa = commessaRepository.findByCodice(codiceCommessa)
                .orElseThrow(() -> new RuntimeException("Commessa non trovata: " + codiceCommessa));

        AttivitaCommessa attivita = attivitaCommessaMapper.toEntity(dto);
        attivita.setCommessa(commessa);
        
        AttivitaCommessa saved = attivitaCommessaRepository.save(attivita);
        return attivitaCommessaMapper.toFindDTO(saved);
    }

    @Override
    public AttivitaCommessaFindDTO update(Long id, AttivitaCommessaUpdateRequestDTO dto) {
        AttivitaCommessa attivita = attivitaCommessaRepository.findByIdAndAttivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Attività non trovata: " + id));

        attivitaCommessaMapper.updateEntityFromDTO(dto, attivita);
        
        AttivitaCommessa updated = attivitaCommessaRepository.save(attivita);
        return attivitaCommessaMapper.toFindDTO(updated);
    }

    @Override
    public void softDelete(Long id) {
        AttivitaCommessa attivita = attivitaCommessaRepository.findByIdAndAttivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Attività non trovata: " + id));
        attivita.setAttivo(false);
        attivitaCommessaRepository.save(attivita);
    }

    @Override
    @Transactional(readOnly = true)
    public AttivitaCommessaFindAllDTO findAllByCommessa(String codiceCommessa) {
        List<AttivitaCommessa> list = attivitaCommessaRepository.findAllByCommessaCodiceAndAttivoTrue(codiceCommessa);
        List<AttivitaCommessaFindDTO> dtoList = attivitaCommessaMapper.toFindDTOList(list);
        return new AttivitaCommessaFindAllDTO(dtoList);
    }
}
