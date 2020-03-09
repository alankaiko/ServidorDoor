package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.repository.filtro.ProcedimentoMedicoFilter;
import com.laudoecia.api.repository.resumo.ResumoProcedimentoMedico;

public interface ProcedimentoMedicoRepositoryQuery {
	public Page<ResumoProcedimentoMedico> resumir(ProcedimentoMedicoFilter filtro, Pageable pageable);
}