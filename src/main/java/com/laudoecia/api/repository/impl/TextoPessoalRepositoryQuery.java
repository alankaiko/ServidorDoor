package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.TextoPessoal;
import com.laudoecia.api.repository.filtro.TextoPessoalFilter;

public interface TextoPessoalRepositoryQuery {
	public Page<TextoPessoal> Filtrando(TextoPessoalFilter filtro, Pageable pageable);
}
