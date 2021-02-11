package com.laudoecia.api.modelo.enuns;

public enum TIPO_LICENCA {

	APENAS_LAUDO("Apenas Laudo", 0), APENAS_IMAGEM("Apenas imagens", 1), LAUDO_E_IMAGEM("Laudo e imagem", 2);

	private final String tipo;
	private final int versaoInt;

	private TIPO_LICENCA(String tipo, int versaoInt) {
		this.tipo = tipo;
		this.versaoInt = versaoInt;
	}

	public String getTipo() {
		return tipo;
	}

	public int getVersaoInt() {
		return versaoInt;
	}

	@Override
	public String toString() {
		return tipo;
	}

}
