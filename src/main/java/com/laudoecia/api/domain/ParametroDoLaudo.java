package com.laudoecia.api.domain;

import java.io.Serializable;

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
@Table(name = "parametrodolaudo")
public class ParametroDoLaudo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private int index;
	private String valor;
	private Laudo laudo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Column(name = "valor", nullable = false, length = 100000)
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@JoinColumn(name = "laudo_codigo", referencedColumnName = "codigo")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	@Column(name = "index", nullable = false)
	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "ParametroDoLaudo{" + "codigo=" + codigo + '}';
	}

}
