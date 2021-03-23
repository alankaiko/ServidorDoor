package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.TextoPessoal;
import com.laudoecia.api.repository.filtro.TextoPessoalFilter;

public interface TextoPessoalRepositoryQuery {
	public Page<TextoPessoal> Filtrando(TextoPessoalFilter filtro, Pageable pageable);
	public boolean VerificarTextoPessoalNome(String nome);
}
