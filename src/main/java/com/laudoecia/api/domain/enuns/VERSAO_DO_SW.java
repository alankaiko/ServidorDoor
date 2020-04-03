package com.laudoecia.api.domain.enuns;

public enum VERSAO_DO_SW {
	V1("1.0.0", 0), V1_0_1("1.0.1", 1), V1_0_2("1.0.2", 2), V1_1_0("1.1.0", 3), V1_1_1("1.1.1", 4), V1_2_0("1.2.0", 5),
	V1_3_0("1.3.0", 6), V1_3_1("1.3.1", 7), V1_4_0("1.4.0", 8), V1_5_0("1.5.0", 9), V1_6_0("1.6.0", 10),
	V1_7_0("1.7.0", 11), V1_8_0("1.8.0", 12), V1_9_0("1.9.0", 13), V1_9_1("1.9.1", 14), V1_9_2("1.9.2", 15),
	V1_9_3("1.9.3", 16), V1_9_4("1.9.4", 16), V1_9_4_1("1.9.4.1", 16), V1_9_4_2("1.9.4.2", 16), V1_9_4_3("1.9.4.3", 16),
	V1_9_4_4("1.9.4.4", 16), V1_9_5("1.9.5", 16), V1_9_5_1("1.9.5.1", 16), V1_9_6("1.9.6", 16), V1_9_6_1("1.9.6.1", 16),
	V1_9_6_2("1.9.6.2", 16), V1_9_7_0("1.9.7.0", 16), V1_9_8_0("1.9.8.0", 16), V1_9_9_0("1.9.9.0", 16),
	V1_9_9_1("1.9.9.1", 16), V1_9_9_2("1.9.9.2", 16);

	private final String tipo;
	private final int versaoInt;

	private VERSAO_DO_SW(String tipo, int versaoInt) {
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
