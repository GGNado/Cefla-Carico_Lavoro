package com.giggi.ceflacarico_lavoro.entity;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Attivitas")
public class Attivita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome attività (es. ORDINE, OFFERTA, SPEDIZIONE).
     * Unique: non possono esistere due attività con lo stesso nome.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /**
     * Tempo medio di riferimento per unità (es. 0.41 ore).
     * Nullable: attività come ASSENZA, FIERA, RIUNIONI non hanno tempo medio.
     * Il valore viene usato come suggerimento per pre-compilare estimatedTime
     * nella WorkloadEntry, ma l'utente può sovrascriverlo.
     */
    @Column(name = "average_time", precision = 6, scale = 2)
    private BigDecimal averageTime;

    /**
     * Flag soft-disable: se false l'attività non è selezionabile nei form
     * ma le WorkloadEntry storiche la mantengono.
     */
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "activityType", fetch = FetchType.LAZY)
    @Builder.Default
    private List<CaricoLavoro> workloadEntries = new ArrayList<>();
}