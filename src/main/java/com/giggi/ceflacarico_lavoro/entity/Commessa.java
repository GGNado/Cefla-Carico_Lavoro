package com.giggi.ceflacarico_lavoro.entity;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "commesse")
public class Commessa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codice;

    @Column(length = 500)
    private String descrizione;

    @Column(length = 50)
    private String tipo;

    @Column(length = 150)
    private String responsabile;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @Column(name = "stima_inizio")
    private LocalDate stimaInizio;

    @Column(name = "stima_fine")
    private LocalDate stimaFine;

    @Column(name = "giornate_stimate")
    private Integer giornateStimate;

    @Builder.Default
    @Column(nullable = false)
    private boolean attivo = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
