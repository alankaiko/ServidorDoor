package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteFilter {
	private String pacienteid;
	private String nome;
	private String idade;
	private boolean dicom;
	private LocalDate datanasc;

	public PacienteFilter() {
	}

	public String getPacienteid() {
		return pacienteid;
	}
	
	public void setPacienteid(String pacienteid) {
		this.pacienteid = pacienteid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDatanasc() {
		return datanasc;
	}
	
	public void setDatanasc(LocalDate datanasc) {
		this.datanasc = datanasc;
	}

	public boolean isDicom() {
		return dicom;
	}
	
	public void setDicom(boolean dicom) {
		this.dicom = dicom;
	}

	@Override
	public String toString() {
		return "PacienteFilter [pacienteid=" + pacienteid + ", nome=" + nome + ", idade=" + idade + ", dicom=" + dicom
				+ ", datanasc=" + datanasc + "]";
	}

}
