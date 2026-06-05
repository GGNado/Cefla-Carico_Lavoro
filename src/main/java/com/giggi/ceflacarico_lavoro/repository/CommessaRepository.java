package com.giggi.ceflacarico_lavoro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.ceflacarico_lavoro.entity.Commessa;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface CommessaRepository extends JpaRepository<Commessa, Long> {
    List<Commessa> findAllByAttivoTrueOrderByCodiceAsc();

    Optional<Commessa> findByCodice(String codice);

    boolean existsByCodice(String codice);

    List<Commessa> findAllByTipoAndAttivoTrue(String tipo);
}
