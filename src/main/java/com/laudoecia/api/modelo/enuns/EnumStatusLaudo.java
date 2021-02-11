package com.laudoecia.api.modelo.enuns;

public enum EnumStatusLaudo {
	PENDENTE("pendente"), PRONTO("pronto");

	private final String status;

	private EnumStatusLaudo(String status) {
		this.status = status;
	}

	public String getAcao() {
		return status;
	}

	@Override
	public String toString() {
		return status;
	}

	public static String[] getStatusComOpcaoAdicional(String opcaoAdicional) {
		String[] model = new String[values().length + 1];
		model[0] = opcaoAdicional;
		for (int i = 0; i < values().length; i++) {
			model[i + 1] = values()[i].toString();
		}
		return model;
	}

}
