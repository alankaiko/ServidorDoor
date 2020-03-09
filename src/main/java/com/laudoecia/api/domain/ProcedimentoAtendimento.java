package com.laudoecia.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="procedimentoatendimento")
public class ProcedimentoAtendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private ProfissionalExecutante profexecutante;
	private ProcedimentoMedico procedimentomedico;
	private BigDecimal valorpaciente;
	private BigDecimal valorconvenio;
	private LocalDate preventregalaudo;
	private LocalDate dataexecucao;
	private Atendimento atendimento;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_profexecutante_id", referencedColumnName = "codigo")
	public ProfissionalExecutante getProfexecutante() {
		return profexecutante;
	}

	public void setProfexecutante(ProfissionalExecutante profexecutante) {
		this.profexecutante = profexecutante;
	}

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_procedimentomedico_id", referencedColumnName = "codigo")
	public ProcedimentoMedico getProcedimentomedico() {
		return procedimentomedico;
	}

	public void setProcedimentomedico(ProcedimentoMedico procedimentomedico) {
		this.procedimentomedico = procedimentomedico;
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

	public LocalDate getDataexecucao() {
		return dataexecucao;
	}

	public void setDataexecucao(LocalDate dataexecucao) {
		this.dataexecucao = dataexecucao;
	}

	public LocalDate getPreventregalaudo() {
		return preventregalaudo;
	}

	public void setPreventregalaudo(LocalDate preventregalaudo) {
		this.preventregalaudo = preventregalaudo;
	}

	@ManyToOne
	@JoinColumn(name = "codigo_atendimento")
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

	@Override
	public String toString() {
		return "ProcedimentoAtendimento [codigo=" + codigo + ", profexecutante=" + profexecutante
				+ ", procedimentomedico=" + procedimentomedico + ", valorpaciente=" + valorpaciente + ", valorconvenio="
				+ valorconvenio + ", preventregalaudo=" + preventregalaudo + ", dataexecucao=" + dataexecucao
				+ ", atendimento=" + atendimento + "]";
	}
	
	

}