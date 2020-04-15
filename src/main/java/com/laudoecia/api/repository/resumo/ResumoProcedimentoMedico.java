package com.laudoecia.api.repository.resumo;

import com.laudoecia.api.domain.GrupoProcedimento;

public class ResumoProcedimentoMedico {
	private Long codigo;
	private String nome;
	private GrupoProcedimento grupo;

	public ResumoProcedimentoMedico(Long codigo, String nome, GrupoProcedimento grupo) {
		this.codigo = codigo;
		this.nome = nome;
		this.grupo = grupo;
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

	public GrupoProcedimento getGrupo() {
		return grupo;
	}
	
	public void setGrupo(GrupoProcedimento grupo) {
		this.grupo = grupo;
	}

}
