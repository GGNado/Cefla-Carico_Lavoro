package com.giggi.ceflacarico_lavoro.mapper;

import org.mapstruct.*;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Commessa;
import com.giggi.ceflacarico_lavoro.dto.request.commessa.CommessaCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.commessa.CommessaUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.commessa.CommessaFindDTO;

@Mapper(componentModel = "spring")
public interface CommessaMapper {

    Commessa convert(CommessaCreateRequestDTO dto);

    Commessa convert(CommessaUpdateRequestDTO dto);

    CommessaFindDTO convert(Commessa entity);

    List<CommessaFindDTO> convert(List<Commessa> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommessaFromDTO(CommessaUpdateRequestDTO dto, @MappingTarget Commessa commessa);
}
