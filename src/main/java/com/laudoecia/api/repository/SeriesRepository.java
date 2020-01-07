package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Series;
import com.laudoecia.api.repository.impl.SeriesRepositoryQuery;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>, SeriesRepositoryQuery{
	public List<Series> findByStudyIdstudy(Long idstudy);
	public Series findBySeriesinstanceuid(String seriesinstanceuid);
	public List<Series> findAllByStudyPatientIdpatient(Long idpatient);
}
