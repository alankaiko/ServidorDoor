package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Modality;

@Repository
public interface DispositiveRepository extends JpaRepository<Modality, Long>{
	public Modality findBySeriesCodigo(Long codigo);
}
