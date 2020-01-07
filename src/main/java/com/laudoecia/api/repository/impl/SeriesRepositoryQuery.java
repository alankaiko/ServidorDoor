package com.laudoecia.api.repository.impl;

import java.util.List;

import com.laudoecia.api.domain.Series;


public interface SeriesRepositoryQuery {
	public List<Series> ListarMaximoCom(int primeiro, int maximo);
	public Series BuscarPorSeriesinstanceuid(String seriesinstanceuid, Integer seriesnumber);
}
