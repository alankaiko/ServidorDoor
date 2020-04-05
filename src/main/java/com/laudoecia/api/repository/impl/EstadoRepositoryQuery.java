package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Estado;
import com.laudoecia.api.repository.filtro.EstadoFilter;

public interface EstadoRepositoryQuery {
	public Page<Estado> Filtrando(EstadoFilter filtro, Pageable pageable);
}
