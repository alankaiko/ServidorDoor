package com.laudoecia.api.repository.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.domain.Paciente;
import com.laudoecia.api.repository.filtro.PatientFilter;
import com.laudoecia.api.repository.resumo.ResumoPatient;

public interface PatientRepositoryQuery {
	public List<Paciente> ListarMaximoCom(int primeiro, int maximo);
	public Page<Paciente> Filtrar(PatientFilter filter, Pageable pageable);
	public Page<ResumoPatient> Resumir(PatientFilter filtro, Pageable pageable);
}
