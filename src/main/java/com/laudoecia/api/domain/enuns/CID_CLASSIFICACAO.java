package com.laudoecia.api.domain.enuns;

public enum CID_CLASSIFICACAO {
	EM_BRANCO(""), // pode ser utilizado em qualquer situação
	POSITIVO("+"), // classificação por etiologia
	ASTERISCO("*"); // classificação por manifestação

	private final String classificacao;

	private CID_CLASSIFICACAO(String classificação) {
		this.classificacao = classificação;
	}

	public String getClassificação() {
		return classificacao;
	}
}
