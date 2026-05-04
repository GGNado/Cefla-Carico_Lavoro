package com.giggi.ceflacarico_lavoro.dto.response.collaboratore;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CollaboratoreFindAllDTO {
    private List<CollaboratoreFindDTO> CollaboratoreFindAllDTO;
}