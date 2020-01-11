package com.laudoecia.api.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.repository.filtro.PatientFilter;
import com.laudoecia.api.repository.filtro.ResumoPatient;

public interface PatientRepositoryQuery {
	public List<Patient> ListarMaximoCom(int primeiro, int maximo);
	public Page<Patient> Filtrar(PatientFilter filter, Pageable pageable);
	public Page<ResumoPatient> Resumir(PatientFilter filtro, Pageable pageable);
}
