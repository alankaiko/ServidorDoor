package com.laudoecia.api.modelo.enuns;

public enum TIPO_BACKUP {
	DADOS("Apenas dados"), DADOS_E_IMAGENS("Dados e imagens");

	private final String tipo;

	private TIPO_BACKUP(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}
}
