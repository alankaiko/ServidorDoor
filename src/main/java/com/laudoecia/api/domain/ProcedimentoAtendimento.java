/*
 * ProcDoAtd.java
 *
 * Created on 19/01/2012, 09:51:43
 */
package com.laudoecia.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class ProcedimentoAtendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private ProfissionalExecutante profexecutante;
	private ProcedimentoTabela procedimentotabela;
	private BigDecimal valorpaciente;
	private BigDecimal valorconvenio;
	private Date preventregalaudo;
	private Date dataexecucao;
	private Atendimento atendimento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_profexecutante_id", referencedColumnName = "codigo")
	public ProfissionalExecutante getProfexecutante() {
		return profexecutante;
	}
	
	public void setProfexecutante(ProfissionalExecutante profexecutante) {
		this.profexecutante = profexecutante;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_procedimentotabela_id", referencedColumnName = "codigo")
	public ProcedimentoTabela getProcedimentotabela() {
		return procedimentotabela;
	}

	public void setProcedimentotabela(ProcedimentoTabela procedimentotabela) {
		this.procedimentotabela = procedimentotabela;
	}

	public BigDecimal getValorpaciente() {
		return valorpaciente;
	}

	public void setValorpaciente(BigDecimal valorpaciente) {
		this.valorpaciente = valorpaciente;
	}

	public BigDecimal getValorconvenio() {
		return valorconvenio;
	}

	public void setValorconvenio(BigDecimal valorconvenio) {
		this.valorconvenio = valorconvenio;
	}

	@Temporal(TemporalType.DATE)
	public Date getPreventregalaudo() {
		return preventregalaudo;
	}

	public void setPreventregalaudo(Date preventregalaudo) {
		this.preventregalaudo = preventregalaudo;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataexecucao() {
		return dataexecucao;
	}

	public void setDataexecucao(Date dataexecucao) {
		this.dataexecucao = dataexecucao;
	}

	@ManyToOne
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
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
		ProcedimentoAtendimento other = (ProcedimentoAtendimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
