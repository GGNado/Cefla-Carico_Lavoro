package com.giggi.ceflacarico_lavoro.mapper;

import org.mapstruct.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Attivita;
import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita.AttivitaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita.AttivitaFindDTO;

@Mapper(componentModel = "spring")
public interface AttivitaMapper {

    Attivita convert(AttivitaCreateRequestDTO dto);

    Attivita convert(AttivitaUpdateRequestDTO dto);

    AttivitaFindDTO convert(Attivita entity);

    List<AttivitaFindDTO> convert(List<Attivita> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAttivitaFromDTO(AttivitaUpdateRequestDTO dto, @MappingTarget Attivita attivita);

}