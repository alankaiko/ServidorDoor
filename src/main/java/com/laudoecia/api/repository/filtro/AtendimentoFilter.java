package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.laudoecia.api.domain.Patient;
import com.laudoecia.api.domain.ProfissionalSolicitante;

public class AtendimentoFilter {
	private Patient patient;
	private ProfissionalSolicitante solicitante;
	private LocalDate datainicial;
	private LocalDate datafinal;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ProfissionalSolicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(ProfissionalSolicitante solicitante) {
		this.solicitante = solicitante;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(LocalDate datafinal) {
		this.datafinal = datafinal;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDatainicial() {
		return datainicial;
	}

	public void setDatainicial(LocalDate datainicial) {
		this.datainicial = datainicial;
	}
}
