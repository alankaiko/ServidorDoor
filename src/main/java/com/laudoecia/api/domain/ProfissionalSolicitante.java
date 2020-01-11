package com.laudoecia.api.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profissionalsolicitante")
public class ProfissionalSolicitante implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private String numnoconselho;
	private TISS_Conselho conselho;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumnoconselho() {
		return numnoconselho;
	}
	
	public void setNumnoconselho(String numnoconselho) {
		this.numnoconselho = numnoconselho;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tabela_conselho_id", referencedColumnName = "codigo")
	public TISS_Conselho getConselho() {
		return conselho;
	}

	public void setConselho(TISS_Conselho conselho) {
		this.conselho = conselho;
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
		ProfissionalSolicitante other = (ProfissionalSolicitante) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
