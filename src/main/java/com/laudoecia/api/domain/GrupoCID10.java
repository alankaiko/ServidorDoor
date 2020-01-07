package com.laudoecia.api.domain;

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
	private String sku;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "capitulocid10_codigo", referencedColumnName = "codigo")
	public CapituloCID10 getCapitulocid10() {
		return capitulocid10;
	}

	public void setCapitulocid10(CapituloCID10 capitulocid10) {
		this.capitulocid10 = capitulocid10;
	}

}
