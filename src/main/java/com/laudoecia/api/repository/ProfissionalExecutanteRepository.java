package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.repository.impl.ProfissionalExecutanteRepositoryQuery;

@Repository
public interface ProfissionalExecutanteRepository extends JpaRepository<ProfissionalExecutante, Long>, ProfissionalExecutanteRepositoryQuery{
	public List<ProfissionalExecutante> findByConselhoDescricao(String descricao);
	public List<ProfissionalExecutante> findByConselhoSiglaDescricaoAndConselhoEstadoUf(String descricao, String uf);
}
