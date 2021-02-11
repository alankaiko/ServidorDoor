package com.laudoecia.api.modelo.enuns;

public enum CID_CAUSA_OBITO {
	EM_BRANCO("NÃO HÁ RESTRIÇÃO"), // Não há restrição
	N("POUCA PROBABILIDADE"); // Pouca probabilidade de causar óbito

	private final String causaObito;

	private CID_CAUSA_OBITO(String causaObito) {
		this.causaObito = causaObito;
	}

	public String getCausaObito() {
		return causaObito;
	}

	@Override
	public String toString() {
		return causaObito;
	}

}
