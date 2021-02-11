package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.ProfissionalSolicitante;
import com.laudoecia.api.repository.implementa.ProfissionalSolicitanteRepositoryQuery;

@Repository
public interface ProfissionalSolicitanteRepository extends JpaRepository<ProfissionalSolicitante, Long>, ProfissionalSolicitanteRepositoryQuery{
	public List<ProfissionalSolicitante> findByConselhoDescricao(String descricao);
}
