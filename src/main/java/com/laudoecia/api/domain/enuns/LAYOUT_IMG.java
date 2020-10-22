package com.laudoecia.api.domain.enuns;

public enum LAYOUT_IMG {
	LAYOUT_1_GRANDE("1 Imagem grande (14 x 10,5 cm)"),
	LAYOUT_1_MEDIA("1 Imagem Média (10 x 7,3 cm) Printer"),
	LAYOUT_2_GRANDE("2 Images grandes (14 x 10,5 cm)"),
	LAYOUT_2_MEDIA("2 Imagens Médias (10 x 7,3 cm) Printer"),
	LAYOUT_3_MEDIA("3 Imagens Médias (8 x 6,6 cm)"),
	LAYOUT_4_GRANDE("4 Imagens Grandes (9 x 6,8 cm)"),
	LAYOUT_4_MEDIA("4 Imagens Médias (8 x 6,6 cm)"),
	LAYOUT_4_PEQUENA("4 Imagens Pequenas (6,5 x 5,0 cm)"),
	LAYOUT_6_MEDIA("6 Imagens Médias (8 x 6,6 cm)"),
	LAYOUT_6_GRANDE("6 Imagens Grandes (9 x 6,8 cm)"),
	LAYOUT_8_PEQUENA("8 Imagens Pequenas (6,5 x 5 cm)"),
	LAYOUT_8_GRANDE("8 Imagens Grandes"),
	LAYOUT_9_PEQUENA("9 Imagens Pequenas (5,5 x 4 cm)"),
	LAYOUT_12_PEQUENA("12 Imagens Pequenas (5,5 x 4 cm)"),
	LAYOUT_15_PEQUENA("15 Imagens Pequenas (5,5 x 4 cm)"),
	LAYOUT_LAUDO_E_4_IMG("Laudo e 4 Imagens Pequenas(5,5 x 4 cm)"),
	LAYOUT_LAUDO_E_5_IMG("Laudo e 5 Imagens Pequenas(5,5 x 4 cm)");

	private final String descricao;

	private LAYOUT_IMG(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
