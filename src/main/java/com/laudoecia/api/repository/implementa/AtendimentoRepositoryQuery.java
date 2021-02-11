package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.Atendimento;
import com.laudoecia.api.repository.filtro.AtendimentoFilter;

public interface AtendimentoRepositoryQuery {
	public Page<Atendimento> Filtrando(AtendimentoFilter filtro, Pageable pageable);
}
