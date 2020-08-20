package com.laudoecia.api.domain.enuns;

public enum STATUS_LAUDO {
	PENDENTE("pendente"),
	PRONTO("pronto");

	private final String status;

	private STATUS_LAUDO(String status) {
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
