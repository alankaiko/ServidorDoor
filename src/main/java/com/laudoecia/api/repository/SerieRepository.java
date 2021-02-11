package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Serie;
import com.laudoecia.api.repository.implementa.SerieRepositoryQuery;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>, SerieRepositoryQuery{
	public List<Serie> findByStudyCodigo(Long codigo);
	public Serie findBySeriesinstanceuid(String seriesinstanceuid);
	public List<Serie> findAllByStudyPacienteCodigo(String codigo);
}
