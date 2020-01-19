package com.laudoecia.api.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Atendimento;

public interface AtendimentoRepositoryQuery {
	public Page<Atendimento> FiltroPaginado(Atendimento atendimento, Pageable page);
}
