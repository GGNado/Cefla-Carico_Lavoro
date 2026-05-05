package com.giggi.ceflacarico_lavoro.dto.response.utente;

import com.giggi.ceflacarico_lavoro.dto.response.role.RoleFindDTO;
import com.giggi.ceflacarico_lavoro.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UtenteFindDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private Set<RoleFindDTO> roles;
}