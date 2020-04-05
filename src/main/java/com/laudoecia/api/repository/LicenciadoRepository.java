package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Licenciado;
import com.laudoecia.api.repository.impl.LicenciadoRepositoryQuery;

@Repository
public interface LicenciadoRepository extends JpaRepository<Licenciado, Long>, LicenciadoRepositoryQuery{

}
