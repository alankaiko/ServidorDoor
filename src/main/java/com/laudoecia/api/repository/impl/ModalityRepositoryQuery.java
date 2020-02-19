package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Modality;
import com.laudoecia.api.repository.filtro.ModalityFilter;

public interface ModalityRepositoryQuery {
	public Page<Modality> Filtrando(ModalityFilter filtro, Pageable pageable);
}
