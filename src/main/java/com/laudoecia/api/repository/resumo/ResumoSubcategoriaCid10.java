package com.laudoecia.api.repository.resumo;

import com.laudoecia.api.modelo.CategoriaCID10;

public class ResumoSubcategoriaCid10 {
	private String codigotexto;
	private String nome;
	private CategoriaCID10 categoriacid;
	
	

	public ResumoSubcategoriaCid10(String codigotexto, String nome, CategoriaCID10 categoriacid) {
		super();
		this.codigotexto = codigotexto;
		this.nome = nome;
		this.categoriacid = categoriacid;
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

	public CategoriaCID10 getCategoriacid() {
		return categoriacid;
	}
	
	public void setCategoriacid(CategoriaCID10 categoriacid) {
		this.categoriacid = categoriacid;
	}
}
