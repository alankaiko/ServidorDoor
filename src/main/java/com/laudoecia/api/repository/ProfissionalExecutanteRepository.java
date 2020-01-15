package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.repository.impl.ProfissionalExecutanteRepositoryQuery;

@Repository
public interface ProfissionalExecutanteRepository extends JpaRepository<ProfissionalExecutante, Long>, ProfissionalExecutanteRepositoryQuery{

}
