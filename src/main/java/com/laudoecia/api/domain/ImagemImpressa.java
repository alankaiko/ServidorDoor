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
@Table(name = "imagemimpressa")
public class ImagemImpressa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private int codigo;

	@JoinColumn(name = "imagem_codigo", referencedColumnName = "codigo", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Imagem imagem;

	@Column(name = "indice")
	private int indice;

	@JoinColumn(name = "pagina_codigo", referencedColumnName = "codigo")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PaginaDeImagens pagina;

	@Column
	private String caminhoimagemjpeg;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

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

	public PaginaDeImagens getPagina() {
		return pagina;
	}

	public void setPagina(PaginaDeImagens pagina) {
		this.pagina = pagina;
	}

	public String getCaminhoimagemjpeg() {
		return caminhoimagemjpeg;
	}

	public void setCaminhoimagemjpeg(String caminhoimagemjpeg) {
		this.caminhoimagemjpeg = caminhoimagemjpeg;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ImagemImpressa other = (ImagemImpressa) obj;
		if (this.codigo != other.getCodigo()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + this.codigo;
		return hash;
	}

}
