package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.TextoPessoal;
import com.laudoecia.api.repository.impl.TextoPessoalRepositoryQuery;

@Repository
public interface TextoPessoalRepository extends JpaRepository<TextoPessoal, Long>, TextoPessoalRepositoryQuery{
	public List<TextoPessoal> findByCodigo(Long codigo);
}
