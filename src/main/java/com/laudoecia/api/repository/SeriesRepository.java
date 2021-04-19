package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Series;
import com.laudoecia.api.repository.implementa.SeriesRepositoryQuery;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>, SeriesRepositoryQuery{
	public List<Series> findByEstudoCodigo(Long codigo);
	public Series findBySeriesinstanceuid(String seriesinstanceuid);
	public List<Series> findAllByEstudoPacienteCodigo(String codigo);
}
