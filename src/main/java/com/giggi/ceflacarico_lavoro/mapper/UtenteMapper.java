package com.giggi.ceflacarico_lavoro.mapper;

import com.giggi.ceflacarico_lavoro.dto.request.auth.RegisterRequest;
import com.giggi.ceflacarico_lavoro.dto.request.utente.UtenteCreateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.request.utente.UtenteUpdateRequestDTO;
import com.giggi.ceflacarico_lavoro.dto.response.utente.UtenteFindDTO;
import com.giggi.ceflacarico_lavoro.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.giggi.ceflacarico_lavoro.entity.Utente;

@Mapper(componentModel = "spring")
public interface UtenteMapper {

    Utente convert(UtenteCreateRequestDTO dto);

    Utente convert(UtenteUpdateRequestDTO dto);

    Utente convert(RegisterRequest dto);

    Utente convert(UtenteFindDTO dto);

    UtenteFindDTO conver(Utente entity);

    List<UtenteFindDTO> convert(List<Utente> entities);

    // Metodo di mapping personalizzato
    default Set<Role> map(Set<String> value) {
        if (value == null) return null;
        return value.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .collect(Collectors.toSet());
    }

}