package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.modelo.Crm;

@Repository
public interface CrmRepository extends JpaRepository<Crm, Long>{

}
