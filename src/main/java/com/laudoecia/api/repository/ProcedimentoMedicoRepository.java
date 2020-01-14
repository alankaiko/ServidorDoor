package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.ProcedimentoMedico;
import com.laudoecia.api.repository.impl.ProcedimentoMedicoRepositoryQuery;

@Repository
public interface ProcedimentoMedicoRepository extends JpaRepository<ProcedimentoMedico, Long>, ProcedimentoMedicoRepositoryQuery{

}
