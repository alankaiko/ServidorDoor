package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.SubcategoriaCid10;
import com.laudoecia.api.repository.impl.SubcategoriaCid10RepositoryQuery;

@Repository
public interface SubcategoriaCid10Repository extends JpaRepository<SubcategoriaCid10, Long>, SubcategoriaCid10RepositoryQuery{

}
