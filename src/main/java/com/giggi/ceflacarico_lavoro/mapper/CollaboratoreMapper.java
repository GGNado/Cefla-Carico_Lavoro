package com.giggi.ceflacarico_lavoro.mapper;

import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.entity.Attivita;
import jakarta.persistence.MapsId;
import org.mapstruct.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Collaboratore;
import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.collaboratore.CollaboratoreUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.collaboratore.CollaboratoreFindDTO;

@Mapper(componentModel = "spring", uses = {UtenteMapper.class})
public interface CollaboratoreMapper {

    
    Collaboratore convert(CollaboratoreCreateRequestDTO dto);

    Collaboratore convert(CollaboratoreUpdateRequestDTO dto);

    @Mapping(source = "userAccount", target = "utente")
    CollaboratoreFindDTO convert(Collaboratore entity);

    List<CollaboratoreFindDTO> convert(List<Collaboratore> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCollaboratoreFromDTO(CollaboratoreUpdateRequestDTO dto, @MappingTarget Collaboratore collaboratore);

}