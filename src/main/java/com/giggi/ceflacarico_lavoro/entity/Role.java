package com.giggi.ceflacarico_lavoro.entity;

import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 255)
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Utente> users = new HashSet<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}