package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.PaginaImagens;

@Repository
public interface PaginaImagensRepository extends JpaRepository<PaginaImagens, Long>{

}
