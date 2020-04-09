package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.ModeloDeLaudoDoProc;
import com.laudoecia.api.repository.filtro.ModeloDeLaudoDoProcFilter;

public interface ModeloDeLaudoDoProcRepositoryQuery {
	public Page<ModeloDeLaudoDoProc> Filtrando(ModeloDeLaudoDoProcFilter filtro, Pageable pageable);
}
