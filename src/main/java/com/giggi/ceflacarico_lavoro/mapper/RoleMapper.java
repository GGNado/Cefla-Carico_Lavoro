package com.giggi.ceflacarico_lavoro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.giggi.ceflacarico_lavoro.entity.Role;
import com.giggi.ceflacarico_lavoro.dto.request.role.RoleCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.role.RoleUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.role.RoleFindDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role convert(RoleCreateRequestDTO dto);

    Role convert(RoleUpdateRequestDTO dto);

    RoleFindDTO convert(Role entity);

    List<RoleFindDTO> convert(List<Role> entities);
}