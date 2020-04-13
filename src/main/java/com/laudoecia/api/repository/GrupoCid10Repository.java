package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.GrupoCID10;

@Repository
public interface GrupoCid10Repository extends JpaRepository<GrupoCID10, Long>{
	public GrupoCID10 findByCodigotexto(String codigotexto);
}
