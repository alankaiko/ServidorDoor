package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProfissionalSolicitante;

@Repository
public interface ProfissionaisSolicitantesRepository extends JpaRepository<ProfissionalSolicitante, Long>{

}
