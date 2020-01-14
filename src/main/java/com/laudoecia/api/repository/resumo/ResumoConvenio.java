package com.laudoecia.api.repository.resumo;

import java.io.Serializable;

public class ResumoConvenio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String telefone;
	private boolean ativo;

	public ResumoConvenio(Long codigo, String nome, String telefone, boolean ativo) {
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
		this.ativo = ativo;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
