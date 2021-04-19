package com.laudoecia.api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modelodelaudodoproc")
public class ModeloDeLaudoDoProc {
	private Long codigo;
	private ProcedimentoMedico procedimentomedico;
	private ModeloDeLaudo modelodelaudo;
	private String descricao;
	private String textogeral;
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

	@Column(name = "customstring", nullable = true, length = 100000)
	public String getTextogeral() {
		return textogeral;
	}
	
	public void setTextogeral(String textogeral) {
		this.textogeral = textogeral;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToOne
	@JoinColumn(name = "modelolaudo_codigo")
	public ModeloDeLaudo getModelodelaudo() {
		return modelodelaudo;
	}
	
	public void setModelodelaudo(ModeloDeLaudo modelodelaudo) {
		this.modelodelaudo = modelodelaudo;
	}

	@ManyToOne
	@JoinColumn(name = "procmedico_codigo")
	public ProcedimentoMedico getProcedimentomedico() {
		return procedimentomedico;
	}
	
	public void setProcedimentomedico(ProcedimentoMedico procedimentomedico) {
		this.procedimentomedico = procedimentomedico;
	}
	
	@Column(name = "prioridade", nullable = true)
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
