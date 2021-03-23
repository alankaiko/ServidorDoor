package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.ProfissionalSolicitante;
import com.laudoecia.api.repository.filtro.ProfissionalSolicitanteFilter;

public interface ProfissionalSolicitanteRepositoryQuery {
	public Page<ProfissionalSolicitante> Filtrando(ProfissionalSolicitanteFilter filtro, Pageable page);
	public boolean VerificarProcSolNome(String nome);
}
