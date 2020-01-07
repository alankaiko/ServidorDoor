package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.TextoPessoal;

@Repository
public interface TextoPessoalRepository extends JpaRepository<TextoPessoal, Long>{

}
