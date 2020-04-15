package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.ProfissionalExecutante;
import com.laudoecia.api.repository.filtro.ProfissionalExecutanteFilter;

public interface ProfissionalExecutanteRepositoryQuery {
	public Page<ProfissionalExecutante> resumir(ProfissionalExecutanteFilter filtro, Pageable page);
	public Page<ProfissionalExecutante> filtrando(ProfissionalExecutanteFilter filtro, Pageable page);
}
