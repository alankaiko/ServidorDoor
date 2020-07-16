package com.laudoecia.api.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modelolaudoclientesalvo")
public class ModeloLaudoClienteSalvo {
	private Long codigo;
	private ProcedimentoMedico procedimentomedico;
	private String descricao;
	private String customstring;
	private int prioridade;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_procedimentomedico_id", referencedColumnName = "codigo", nullable = true)
	public ProcedimentoMedico getProcedimentomedico() {
		return procedimentomedico;
	}

	public void setProcedimentomedico(ProcedimentoMedico procedimentomedico) {
		this.procedimentomedico = procedimentomedico;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "customstring", length = 100000)
	public String getCustomstring() {
		return customstring;
	}

	public void setCustomstring(String customstring) {
		this.customstring = customstring;
	}

	@Column(name = "prioridade")
	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

}
