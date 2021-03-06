package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.ProcedimentoAtendimento;
import com.laudoecia.api.repository.filtro.ProcedimentoAtendimentoFilter;

public interface ProcedimentoAtendimentoQuery {
	public Page<ProcedimentoAtendimento> Filtrando(ProcedimentoAtendimentoFilter filtro, Pageable pageable);
}
