package com.laudoecia.api.modelo.enuns;

public enum EnumTitulo {
	DR("DR"), DRA("DRA"), Nenhum("Nenhum");
	
	private String descricao;
	
	private EnumTitulo(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
}
