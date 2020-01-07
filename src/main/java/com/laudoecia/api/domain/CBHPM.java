package com.laudoecia.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "cbhpm")
public class CBHPM implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String sku;
	private String procedimento;
	private String porte;
	private BigDecimal valorporte;
	private BigDecimal custooperacional;
	private BigDecimal valorcustooperacional;
	private int nauxiliares;
	private String porteanest;
	private BigDecimal valortotal;
	private double filmes;
	private int incidencia;
	private String unidrdfarmaco;
	private SubgrupoCBHPM subgrupo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(unique = true)
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(length = 400)
	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public BigDecimal getValorporte() {
		return valorporte;
	}
	
	public void setValorporte(BigDecimal valorporte) {
		this.valorporte = valorporte;
	}

	public BigDecimal getCustooperacional() {
		return custooperacional;
	}

	public void setCustooperacional(BigDecimal custooperacional) {
		this.custooperacional = custooperacional;
	}

	public BigDecimal getValorcustooperacional() {
		return valorcustooperacional;
	}

	public void setValorcustooperacional(BigDecimal valorcustooperacional) {
		this.valorcustooperacional = valorcustooperacional;
	}

	public int getNauxiliares() {
		return nauxiliares;
	}

	public void setNauxiliares(int nauxiliares) {
		this.nauxiliares = nauxiliares;
	}

	public String getPorteanest() {
		return porteanest;
	}

	public void setPorteanest(String porteanest) {
		this.porteanest = porteanest;
	}

	public BigDecimal getValortotal() {
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public double getFilmes() {
		return filmes;
	}

	public void setFilmes(double filmes) {
		this.filmes = filmes;
	}

	public int getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(int incidencia) {
		this.incidencia = incidencia;
	}

	public String getUnidrdfarmaco() {
		return unidrdfarmaco;
	}

	public void setUnidrdfarmaco(String unidrdfarmaco) {
		this.unidrdfarmaco = unidrdfarmaco;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "subgrupo_codigo", referencedColumnName = "codigo")
	public SubgrupoCBHPM getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(SubgrupoCBHPM subgrupo) {
		this.subgrupo = subgrupo;
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
		CBHPM other = (CBHPM) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
