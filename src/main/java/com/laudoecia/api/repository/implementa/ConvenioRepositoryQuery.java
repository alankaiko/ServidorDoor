package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.repository.filtro.ConvenioFilter;
import com.laudoecia.api.repository.resumo.ResumoConvenio;

public interface ConvenioRepositoryQuery {
	public Page<ResumoConvenio> resumir(ConvenioFilter filtro, Pageable pageable);
}
