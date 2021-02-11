package com.laudoecia.api.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grupocid10")
public class GrupoCID10 implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String codigotexto;
	private String nome;
	private CapituloCID10 capitulocid10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodigotexto() {
		return codigotexto;
	}
	
	public void setCodigotexto(String codigotexto) {
		this.codigotexto = codigotexto;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "capitulocid10_codigo", referencedColumnName = "codigo", nullable = true)
	public CapituloCID10 getCapitulocid10() {
		return capitulocid10;
	}

	public void setCapitulocid10(CapituloCID10 capitulocid10) {
		this.capitulocid10 = capitulocid10;
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
		GrupoCID10 other = (GrupoCID10) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
