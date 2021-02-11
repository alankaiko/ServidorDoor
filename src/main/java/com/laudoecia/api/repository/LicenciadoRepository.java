package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Licenciado;
import com.laudoecia.api.repository.implementa.LicenciadoRepositoryQuery;

@Repository
public interface LicenciadoRepository extends JpaRepository<Licenciado, Long>, LicenciadoRepositoryQuery{

}
