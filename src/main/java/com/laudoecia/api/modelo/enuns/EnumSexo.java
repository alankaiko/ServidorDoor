package com.laudoecia.api.modelo.enuns;

public enum EnumSexo {
	INDEFINIDO("Indefinido"), MASCULINO("Masculino"), FEMININO("Feminino");

	private String valor;

	private EnumSexo(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}
}