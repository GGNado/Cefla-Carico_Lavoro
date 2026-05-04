package com.giggi.ceflacarico_lavoro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;
import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.caricolavoro.CaricoLavoroUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.caricolavoro.CaricoLavoroFindDTO;

@Mapper(componentModel = "spring")
public interface CaricoLavoroMapper {

    CaricoLavoro convert(CaricoLavoroCreateRequestDTO dto);

    CaricoLavoro convert(CaricoLavoroUpdateRequestDTO dto);

    CaricoLavoroFindDTO convert(CaricoLavoro entity);

    List<CaricoLavoroFindDTO> convert(List<CaricoLavoro> entities);
}