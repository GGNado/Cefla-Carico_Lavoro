package com.giggi.ceflacarico_lavoro.mapper;

import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindDTO;
import com.giggi.ceflacarico_lavoro.entity.AttivitaCommessa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttivitaCommessaMapper {

    AttivitaCommessa toEntity(AttivitaCommessaCreateRequestDTO dto);

    AttivitaCommessaFindDTO toFindDTO(AttivitaCommessa entity);

    List<AttivitaCommessaFindDTO> toFindDTOList(List<AttivitaCommessa> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(AttivitaCommessaUpdateRequestDTO dto, @MappingTarget AttivitaCommessa entity);
}
