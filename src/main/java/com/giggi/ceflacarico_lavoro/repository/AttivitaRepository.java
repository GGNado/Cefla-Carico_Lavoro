package com.giggi.ceflacarico_lavoro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.ceflacarico_lavoro.entity.Attivita;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface AttivitaRepository extends JpaRepository<Attivita, Long> {
    List<Attivita> findByActiveTrue();
}