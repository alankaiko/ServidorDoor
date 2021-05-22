package com.laudoecia.api.modelo.enuns;

public enum EnumStatusProcedimento {
	STATUS("Status"), AGENDADO("Agendado"), CONCLUIDO("Concluido"), ENVIADO("Enviado"), CANCELADO("Cancelado");
	
	private String descricao;
	
	private EnumStatusProcedimento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
}
