package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.ProcedimentoMedico;
import com.laudoecia.api.repository.implementa.ProcedimentoMedicoRepositoryQuery;

@Repository
public interface ProcedimentoMedicoRepository extends JpaRepository<ProcedimentoMedico, Long>, ProcedimentoMedicoRepositoryQuery{
	public List<ProcedimentoMedico> findByCodigo(Long codigo); 
	public List<ProcedimentoMedico> findByGrupoNomegrupoStartingWith(String nomegrupo);
}
