package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.ModeloDeLaudoDoProc;
import com.laudoecia.api.repository.filtro.ModeloDeLaudoDoProcFilter;

public interface ModeloDeLaudoDoProcRepositoryQuery {
	public Page<ModeloDeLaudoDoProc> Filtrando(ModeloDeLaudoDoProcFilter filtro, Pageable pageable);
}
