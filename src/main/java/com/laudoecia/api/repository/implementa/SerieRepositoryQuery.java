package com.laudoecia.api.repository.implementa;

import java.util.List;

import com.laudoecia.api.modelo.Serie;


public interface SerieRepositoryQuery {
	public List<Serie> ListarMaximoCom(int primeiro, int maximo);
	public Serie BuscarPorSeriesinstanceuid(String seriesinstanceuid, Integer seriesnumber);
}
