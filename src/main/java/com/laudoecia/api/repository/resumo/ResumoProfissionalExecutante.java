package com.laudoecia.api.repository.resumo;

import com.laudoecia.api.domain.Contato;

public class ResumoProfissionalExecutante {
	private Long codigo;
	private String nome;
	private String numnoconselho;
	private Contato contato;

	public ResumoProfissionalExecutante(Long codigo, String nome, String numnoconselho, Contato contato) {
		this.codigo = codigo;
		this.nome = nome;
		this.numnoconselho = numnoconselho;
		this.contato = contato;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumnoconselho() {
		return numnoconselho;
	}

	public void setNumnoconselho(String numnoconselho) {
		this.numnoconselho = numnoconselho;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
