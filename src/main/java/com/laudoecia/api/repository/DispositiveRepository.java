package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Dispositive;

@Repository
public interface DispositiveRepository extends JpaRepository<Dispositive, Long>{
	public Dispositive findBySeriesIdseries(Long idseries);
}
