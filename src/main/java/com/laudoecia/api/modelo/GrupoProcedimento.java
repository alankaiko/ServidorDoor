package com.laudoecia.api.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupoprocedimento")
public class GrupoProcedimento {
	private Long codigo;
	private String nomegrupo;

	@Id
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(nullable = false)
	public String getNomegrupo() {
		return nomegrupo;
	}
	
	public void setNomegrupo(String nomegrupo) {
		this.nomegrupo = nomegrupo;
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
		GrupoProcedimento other = (GrupoProcedimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoProcedimento [codigo=" + codigo + ", nomegrupo=" + nomegrupo + "]";
	}

	
}
