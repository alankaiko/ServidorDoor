package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Sigla;
import com.laudoecia.api.repository.filtro.SiglaFilter;

public interface SiglaRepositoryQuery {
	public Page<Sigla> Filtrando(SiglaFilter filtro, Pageable pageable);
}
