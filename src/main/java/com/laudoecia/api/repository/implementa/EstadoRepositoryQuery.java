package com.laudoecia.api.repository.implementa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.Estado;
import com.laudoecia.api.repository.filtro.EstadoFilter;

public interface EstadoRepositoryQuery {
	public Page<Estado> Filtrando(EstadoFilter filtro, Pageable pageable);
	public Long BuscarIdMax();
	public boolean VerificarEstadoNome(String nome);
}
