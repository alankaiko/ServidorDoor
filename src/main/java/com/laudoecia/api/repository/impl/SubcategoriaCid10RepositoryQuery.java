package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.SubcategoriaCid10;
import com.laudoecia.api.repository.filtro.SubcategoriaCid10Filter;

public interface SubcategoriaCid10RepositoryQuery {
	public Page<SubcategoriaCid10> Filtrando(SubcategoriaCid10Filter filtro, Pageable pageable);
}
