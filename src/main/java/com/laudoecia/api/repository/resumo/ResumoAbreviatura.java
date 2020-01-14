package com.laudoecia.api.repository.resumo;

import java.io.Serializable;

public class ResumoAbreviatura implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String titulo;
	private String texto;

	public ResumoAbreviatura(Long codigo, String titulo, String texto) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.texto = texto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
