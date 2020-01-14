package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.repository.filtro.GrupoProcedimentoFilter;
import com.laudoecia.api.repository.resumo.ResumoGrupoProcedimento;

public interface GrupoProcedimentoRepositoryQuery {
	public Page<ResumoGrupoProcedimento> resumir(GrupoProcedimentoFilter filtro, Pageable pageable);
}
