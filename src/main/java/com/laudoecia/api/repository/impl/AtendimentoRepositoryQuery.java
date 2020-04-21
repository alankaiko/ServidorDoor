package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Atendimento;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;

public interface AtendimentoRepositoryQuery {
	public Page<Atendimento> Filtrando(AtendimentoFilter filtro, Pageable pageable);
}
