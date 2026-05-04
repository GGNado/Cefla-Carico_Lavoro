package com.giggi.ceflacarico_lavoro.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false)
    private Boolean accountNonExpired = true;

    @Column(nullable = false)
    private Boolean accountNonLocked = true;

    @Column(nullable = false)
    private Boolean credentialsNonExpired = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator_id")
    private Collaboratore collaborator;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Audit trail: righe create/modificate da questo utente
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    @Builder.Default
    private List<CaricoLavoro> createdEntries = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy", fetch = FetchType.LAZY)
    @Builder.Default
    private List<CaricoLavoro> updatedEntries = new ArrayList<>();
}