package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.ProfissionalSolicitante;
import com.laudoecia.api.repository.filtro.ProfissionalSolicitanteFilter;

public interface ProfissionalSolicitanteRepositoryQuery {
	public Page<ProfissionalSolicitante> Filtrando(ProfissionalSolicitanteFilter filtro, Pageable page);
}
