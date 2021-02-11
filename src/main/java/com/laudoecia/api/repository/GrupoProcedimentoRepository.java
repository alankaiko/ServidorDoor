package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.GrupoProcedimento;
import com.laudoecia.api.repository.implementa.GrupoProcedimentoRepositoryQuery;

@Repository
public interface GrupoProcedimentoRepository extends JpaRepository<GrupoProcedimento, Long>, GrupoProcedimentoRepositoryQuery{
	public List<GrupoProcedimento> findByCodigo(Long codigo); 
}
