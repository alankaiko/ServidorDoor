package com.laudoecia.api.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.laudoecia.api.modelo.enuns.EnumRestricaoSexo;

@Entity
@Table(name="procedimentomedico")
public class ProcedimentoMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private EnumRestricaoSexo restricaosexo = EnumRestricaoSexo.NENHUMA_RESTRICAO;
	private String caminhoimagem1;
	private String caminhoimagem2;
	private boolean laudomodelo;
	private GrupoProcedimento grupo;
	

	@Id
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome", nullable = false, unique = true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Enumerated(EnumType.STRING)
	public EnumRestricaoSexo getRestricaosexo() {
		return restricaosexo;
	}

	public void setRestricaosexo(EnumRestricaoSexo restricaosexo) {
		this.restricaosexo = restricaosexo;
	}

	public String getCaminhoimagem1() {
		return caminhoimagem1;
	}

	public void setCaminhoimagem1(String caminhoimagem1) {
		this.caminhoimagem1 = caminhoimagem1;
	}

	public String getCaminhoimagem2() {
		return caminhoimagem2;
	}

	public void setCaminhoimagem2(String caminhoimagem2) {
		this.caminhoimagem2 = caminhoimagem2;
	}

	public boolean isLaudomodelo() {
		return laudomodelo;
	}

	public void setLaudomodelo(boolean laudomodelo) {
		this.laudomodelo = laudomodelo;
	}

	@ManyToOne
	@JoinColumn(name = "grupo_codigo", referencedColumnName = "codigo", nullable = false)
	public GrupoProcedimento getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimento grupo) {
		this.grupo = grupo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcedimentoMedico other = (ProcedimentoMedico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProcedimentoMedico [codigo=" + codigo + ", nome=" + nome + ", restricaosexo=" + restricaosexo
				+ ", caminhoimagem1=" + caminhoimagem1 + ", caminhoimagem2=" + caminhoimagem2 + ", laudomodelo="
				+ laudomodelo + ", grupo=" + grupo + "]";
	}

	
}
