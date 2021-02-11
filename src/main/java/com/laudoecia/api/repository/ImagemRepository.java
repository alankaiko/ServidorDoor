package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
	public Imagem findByCodigouid(String codigouid);
}
