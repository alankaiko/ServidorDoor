package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.GrupoProcedimento;
import com.laudoecia.api.repository.filtro.GrupoProcedimentoFilter;

public interface GrupoProcedimentoRepositoryQuery {
	public Page<GrupoProcedimento> resumir(GrupoProcedimentoFilter filtro, Pageable pageable);
	public Long BuscarIdMax();
	public boolean VerificarGrupoProcNome(String nome);
}
