package com.laudoecia.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ProcedimentoTabela implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private BigDecimal valorpacientez;
	private TabelaDeProcedimentos tabeladeprocedimentos;
	private ProcedimentoMedico procedimentomedico;
	private BigDecimal valornoconvenioz;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValorpacientez() {
		return valorpacientez;
	}
	
	public void setValorpacientez(BigDecimal valorpacientez) {
		this.valorpacientez = valorpacientez;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_deprocedimento_id", referencedColumnName = "codigo")
	public TabelaDeProcedimentos getTabeladeprocedimentos() {
		return tabeladeprocedimentos;
	}

	public void setTabeladeprocedimentos(TabelaDeProcedimentos tabeladeprocedimentos) {
		this.tabeladeprocedimentos = tabeladeprocedimentos;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbl_procmedico_id", referencedColumnName = "codigo")
	public ProcedimentoMedico getProcedimentomedico() {
		return procedimentomedico;
	}

	public void setProcedimentomedico(ProcedimentoMedico procedimentomedico) {
		this.procedimentomedico = procedimentomedico;
	}

	public BigDecimal getValornoconvenioz() {
		return valornoconvenioz;
	}
	
	public void setValornoconvenioz(BigDecimal valornoconvenioz) {
		this.valornoconvenioz = valornoconvenioz;
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
		ProcedimentoTabela other = (ProcedimentoTabela) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}