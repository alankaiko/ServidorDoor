package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "procedimentomedico")
public class ProcedimentoMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private int diasparaentregadolaudo;
	private int margemtop = 10;
	private int margembottom = 10;
	private RESTRICAO_SEXO restricaosexo = RESTRICAO_SEXO.NENHUMA_RESTRICAO;
	private String imagem1;
	private String imagem2;
	private boolean laudomodelo;
	private GrupoProcedimento grupo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public int getDiasparaentregadolaudo() {
		return diasparaentregadolaudo;
	}

	public void setDiasparaentregadolaudo(int diasparaentregadolaudo) {
		this.diasparaentregadolaudo = diasparaentregadolaudo;
	}

	@Column(name = "margemtop", nullable = false)
	public int getMargemtop() {
		return margemtop;
	}

	public void setMargembottom(int margembottom) {
		this.margembottom = margembottom;
	}

	@Column(name = "margembottom", nullable = false)
	public int getMargembottom() {
		return margembottom;
	}

	public void setMargemtop(int margemtop) {
		this.margemtop = margemtop;
	}

	@Enumerated(EnumType.STRING)
	public RESTRICAO_SEXO getRestricaosexo() {
		return restricaosexo;
	}
	
	public void setRestricaosexo(RESTRICAO_SEXO restricaosexo) {
		this.restricaosexo = restricaosexo;
	}

	public String getImagem1() {
		return imagem1;
	}

	public void setImagem1(String imagem1) {
		this.imagem1 = imagem1;
	}

	public String getImagem2() {
		return imagem2;
	}

	public void setImagem2(String imagem2) {
		this.imagem2 = imagem2;
	}

	public boolean isLaudomodelo() {
		return laudomodelo;
	}
	
	public void setLaudomodelo(boolean laudomodelo) {
		this.laudomodelo = laudomodelo;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "grupo_codigo", referencedColumnName = "codigo", nullable = false)
	public GrupoProcedimento getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProcedimento grupo) {
		this.grupo = grupo;
	}

}
