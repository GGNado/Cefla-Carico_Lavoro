package com.giggi.ceflacarico_lavoro.entity;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Collaboratori")
public class Collaboratore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(length = 255)
    private String email;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @OneToOne(mappedBy = "collaborator", fetch = FetchType.LAZY)
    private Utente userAccount;

    @OneToMany(mappedBy = "collaborator", fetch = FetchType.LAZY)
    @Builder.Default
    private List<CaricoLavoro> workloadEntries = new ArrayList<>();
}