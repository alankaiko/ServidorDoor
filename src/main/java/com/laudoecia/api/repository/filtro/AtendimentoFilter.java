package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class AtendimentoFilter {
	private String patientname;
	private String solicitantename;
	private LocalDate datainicial;
	private LocalDate datafinal;

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getSolicitantename() {
		return solicitantename;
	}

	public void setSolicitantename(String solicitantename) {
		this.solicitantename = solicitantename;
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
