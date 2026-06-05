package com.giggi.ceflacarico_lavoro.repository;

import com.giggi.ceflacarico_lavoro.entity.AttivitaCommessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface AttivitaCommessaRepository extends JpaRepository<AttivitaCommessa, Long> {
    List<AttivitaCommessa> findAllByCommessaCodiceAndAttivoTrue(String codiceCommessa);
    Optional<AttivitaCommessa> findByIdAndAttivoTrue(Long id);
}
