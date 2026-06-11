package com.giggi.ceflacarico_lavoro.mapper;

import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.attivita_commessa.AttivitaCommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.attivita_commessa.AttivitaCommessaFindDTO;
import com.giggi.ceflacarico_lavoro.entity.AttivitaCommessa;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttivitaCommessaMapper {

    AttivitaCommessa toEntity(AttivitaCommessaCreateRequestDTO dto);

    @Mapping(source = "commessa.codice", target = "commessaCodice")
    AttivitaCommessaFindDTO toFindDTO(AttivitaCommessa entity);

    @Mapping(source = "commessa.codice", target = "commessaCodice")
    List<AttivitaCommessaFindDTO> toFindDTOList(List<AttivitaCommessa> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(AttivitaCommessaUpdateRequestDTO dto, @MappingTarget AttivitaCommessa entity);
}
