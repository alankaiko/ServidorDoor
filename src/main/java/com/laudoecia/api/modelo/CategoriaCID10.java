package com.laudoecia.api.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="categoriacid10")
public class CategoriaCID10 {
	private Long codigo;
	private String codigotexto;
	private String nome;
	private GrupoCID10 grupocid10;

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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodigotexto(String codigotexto) {
		this.codigotexto = codigotexto;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "grupocid10_codigo", referencedColumnName = "codigo")
	public GrupoCID10 getGrupocid10() {
		return grupocid10;
	}

	public void setGrupocid10(GrupoCID10 grupocid10) {
		this.grupocid10 = grupocid10;
	}

}
