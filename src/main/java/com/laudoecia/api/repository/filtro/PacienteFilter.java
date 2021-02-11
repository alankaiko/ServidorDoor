package com.laudoecia.api.repository.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteFilter {
	private String codigo;
	private String nome;
	private String idade;
	private boolean servidor;
	private LocalDate dataaniversario;

	public PacienteFilter() {
	}

	public PacienteFilter(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public LocalDate getDataaniversario() {
		return dataaniversario;
	}

	public void setDataaniversario(LocalDate dataaniversario) {
		this.dataaniversario = dataaniversario;
	}

	public boolean isServidor() {
		return servidor;
	}

	public void setServidor(boolean servidor) {
		this.servidor = servidor;
	}

}
