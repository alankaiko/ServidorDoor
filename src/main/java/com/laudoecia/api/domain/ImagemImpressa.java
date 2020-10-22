package com.laudoecia.api.domain;

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
@Table(name = "imagemimpressa")
public class ImagemImpressa{
	private Long codigo;
	private Imagem imagem;
	private int indice;
	private PaginaDeImagens paginadeimagens;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "tbl_imagem_id", referencedColumnName = "codigo")
	public Imagem getImagem() {
		return imagem;
	}
	
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_pagimagens")
	public PaginaDeImagens getPaginadeimagens() {
		return paginadeimagens;
	}
	
	public void setPaginadeimagens(PaginaDeImagens paginadeimagens) {
		this.paginadeimagens = paginadeimagens;
	}
}
