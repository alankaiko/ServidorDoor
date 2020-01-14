package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.repository.filtro.ProfissionalExecutanteFilter;
import com.laudoecia.api.repository.resumo.ResumoProfissionalExecutante;

public interface ProfissionalExecutanteRepositoryQuery {
	public Page<ResumoProfissionalExecutante> resumir(ProfissionalExecutanteFilter filtro, Pageable page);
}
