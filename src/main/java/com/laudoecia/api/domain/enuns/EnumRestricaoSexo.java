package com.laudoecia.api.domain.enuns;

public enum EnumRestricaoSexo {
	NENHUMA_RESTRICAO("NENHUMA RESTRICÃO"), APENAS_MASCULINO("APENAS MASCULINO"), APENAS_FEMININO("APENAS FEMININO");

	private final String descricao;

	private EnumRestricaoSexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
