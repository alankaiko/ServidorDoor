package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.PaginaDeImagens;

@Repository
public interface PaginaDeImagensRepository extends JpaRepository<PaginaDeImagens, Long>{

}
