package com.laudoecia.api.repository.implementa;

import java.util.List;

import com.laudoecia.api.modelo.Series;


public interface SeriesRepositoryQuery {
	public List<Series> ListarMaximoCom(int primeiro, int maximo);
	public Series BuscarPorSeriesinstanceuid(String seriesinstanceuid, Integer seriesnumber);
}
