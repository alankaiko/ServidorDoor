package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.repository.filtro.AbreviaturaFilter;
import com.laudoecia.api.repository.resumo.ResumoAbreviatura;

public interface AbreviaturaRepositoryQuery {
	public Page<ResumoAbreviatura> resumir(AbreviaturaFilter filtro, Pageable pageable);
}
