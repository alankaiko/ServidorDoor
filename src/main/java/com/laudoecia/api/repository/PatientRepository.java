package com.laudoecia.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Paciente;
import com.laudoecia.api.repository.impl.PatientRepositoryQuery;

@Repository
public interface PatientRepository extends JpaRepository<Paciente, Long>, PatientRepositoryQuery{
	public Paciente findByPatientid(String patientid);
	public List<Paciente> findByIdpatient(Long idpatient); 
}
