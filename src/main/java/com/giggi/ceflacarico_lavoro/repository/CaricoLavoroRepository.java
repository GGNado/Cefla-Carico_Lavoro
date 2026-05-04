package com.giggi.ceflacarico_lavoro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.ceflacarico_lavoro.entity.CaricoLavoro;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface CaricoLavoroRepository extends JpaRepository<CaricoLavoro, Long> {
}