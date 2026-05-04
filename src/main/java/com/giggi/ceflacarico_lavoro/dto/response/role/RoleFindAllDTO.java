package com.giggi.ceflacarico_lavoro.dto.response.role;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RoleFindAllDTO {
    private List<RoleFindDTO> RoleFindAllDTO;
}