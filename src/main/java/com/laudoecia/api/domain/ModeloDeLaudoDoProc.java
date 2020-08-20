package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "modelodelaudodoproc")
public class ModeloDeLaudoDoProc implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private ProcedimentoMedico procedimentomedico;
	private ModeloDeLaudo modelodeLaudo;
	private String descricao;
	private String customString;
	private int prioridade = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(name = "customstring", nullable = false, length = 100000)
	public String getCustomString() {
		return customString == null ? "" : customString;
	}

	public void setCustomString(String customString) {
		this.customString = customString == null ? "" : customString;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "modelolaudo_codigo", referencedColumnName = "codigo", nullable = true)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public ModeloDeLaudo getModelodeLaudo() {
		return modelodeLaudo;
	}
	
	public void setModelodeLaudo(ModeloDeLaudo modelodeLaudo) {
		this.modelodeLaudo = modelodeLaudo;
	}

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "procmedico_codigo", referencedColumnName = "codigo", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public ProcedimentoMedico getProcedimentomedico() {
		return procedimentomedico;
	}
	
	public void setProcedimentomedico(ProcedimentoMedico procedimentomedico) {
		this.procedimentomedico = procedimentomedico;
	}
	
	@Column(name = "prioridade", nullable = false)
	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
