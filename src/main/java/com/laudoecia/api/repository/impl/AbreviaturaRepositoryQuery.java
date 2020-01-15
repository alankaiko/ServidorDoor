package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Abreviatura;
import com.laudoecia.api.repository.filtro.AbreviaturaFilter;

public interface AbreviaturaRepositoryQuery {
	public Page<Abreviatura> Filtrando(AbreviaturaFilter filtro, Pageable pageable);
}
