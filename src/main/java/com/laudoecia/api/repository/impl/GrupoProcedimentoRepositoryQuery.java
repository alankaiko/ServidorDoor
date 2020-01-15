package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.GrupoProcedimento;
import com.laudoecia.api.repository.filtro.GrupoProcedimentoFilter;

public interface GrupoProcedimentoRepositoryQuery {
	public Page<GrupoProcedimento> resumir(GrupoProcedimentoFilter filtro, Pageable pageable);
}
