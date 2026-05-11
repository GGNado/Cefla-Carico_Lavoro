package com.giggi.ceflacarico_lavoro.dto.request.collaboratore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CollaboratoreUpdateRequestDTO {
    private Long id;
    private String fullName;
    private String email;
}