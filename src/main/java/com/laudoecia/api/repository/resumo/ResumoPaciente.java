package com.laudoecia.api.repository.resumo;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ResumoPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String pacienteid;
	private String nome;
	private LocalDate datanasc;
	private String idade;
	private String sexo;
	private LocalDate datacriacao;

	

	public ResumoPaciente(Long codigo, String pacienteid, String nome, LocalDate datanasc, String idade, String sexo,LocalDate datacriacao) {
		this.codigo = codigo;
		this.pacienteid = pacienteid;
		this.nome = nome;
		this.datanasc = datanasc;
		this.idade = idade;
		this.sexo = sexo;
		this.datacriacao = datacriacao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public LocalDate getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(LocalDate datanasc) {
		this.datanasc = datanasc;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDate datacriacao) {
		this.datacriacao = datacriacao;
	}

}
