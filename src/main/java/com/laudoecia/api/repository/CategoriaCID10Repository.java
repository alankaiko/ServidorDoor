package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.CategoriaCID10;

@Repository
public interface CategoriaCID10Repository extends JpaRepository<CategoriaCID10, Long> {
	public CategoriaCID10 findByCodigotexto(String codigotexto);
}
