package com.laudoecia.api.repository.implementa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laudoecia.api.modelo.Paciente;
import com.laudoecia.api.repository.filtro.PacienteFilter;
import com.laudoecia.api.repository.resumo.ResumoPaciente;

public interface PacienteRepositoryQuery {
	public List<Paciente> ListarMaximoCom(int primeiro, int maximo);
	public Page<Paciente> Filtrar(PacienteFilter filter, Pageable pageable);
	public Page<ResumoPaciente> Resumir(PacienteFilter filtro, Pageable pageable);
	public Paciente BuscarUnicoPacPorCodigo(String codigo);
	public List<Paciente> ListarPacientePorCodigo(String codigo); 
}
