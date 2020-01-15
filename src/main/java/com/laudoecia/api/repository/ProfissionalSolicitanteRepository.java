package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProfissionalSolicitante;
import com.laudoecia.api.repository.impl.ProfissionalSolicitanteRepositoryQuery;

@Repository
public interface ProfissionalSolicitanteRepository extends JpaRepository<ProfissionalSolicitante, Long>, ProfissionalSolicitanteRepositoryQuery{

}
