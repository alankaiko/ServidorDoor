package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tabeladeprocedimentos")
public class TabelaDeProcedimentos implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Convenio convenio;

	public TabelaDeProcedimentos() {
	}

	public TabelaDeProcedimentos(Long codigo) {
		this.codigo = codigo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "convenio_codigo", referencedColumnName = "codigo")
	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	@Override
	public String toString() {
		return convenio == null ? "" : convenio.toString();
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
		TabelaDeProcedimentos other = (TabelaDeProcedimentos) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
