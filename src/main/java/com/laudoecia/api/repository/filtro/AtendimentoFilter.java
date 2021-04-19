package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class AtendimentoFilter {
	private String pacientenome;
	private String solicitantenome;
	private LocalDate datainicial;
	private LocalDate datafinal;
	private LocalDate datanascpaciente;
	
	public String getPacientenome() {
		return pacientenome;
	}
	
	public void setPacientenome(String pacientenome) {
		this.pacientenome = pacientenome;
	}

	public String getSolicitantenome() {
		return solicitantenome;
	}
	
	public void setSolicitantenome(String solicitantenome) {
		this.solicitantenome = solicitantenome;
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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDatanascpaciente() {
		return datanascpaciente;
	}
	
	public void setDatanascpaciente(LocalDate datanascpaciente) {
		this.datanascpaciente = datanascpaciente;
	}
}
