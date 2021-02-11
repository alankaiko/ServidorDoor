package com.laudoecia.api.repository.implementa;

import java.util.List;

import com.laudoecia.api.modelo.Estudo;

public interface EstudoRepositoryQuery {
	public List<Estudo> ListarMaximoCom(int primeiro, int maximo);
}
