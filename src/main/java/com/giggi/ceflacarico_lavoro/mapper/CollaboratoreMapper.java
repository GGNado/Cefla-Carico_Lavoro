package com.giggi.ceflacarico_lavoro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
}