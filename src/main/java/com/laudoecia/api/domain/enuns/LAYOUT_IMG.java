package com.laudoecia.api.domain.enuns;

public enum LAYOUT_IMG {
	LAYOUT_1_IMG("1 Imagem grande (14 x 10,5 cm)", 1), LAYOUT_1_IMG_PRINTER("1 Imagem Média (10 x 7,3 cm) Printer", 1),
	LAYOUT_2_IMG_GRANDES("2 Images grandes (14 x 10,5 cm)", 2),
	LAYOUT_2_IMG("2 Imagens Médias (10 x 7,3 cm) Printer", 2), LAYOUT_3_IMG("3 Imagens Médias (8 x 6,6 cm)", 3),
	LAYOUT_4_IMG_GRANDES("4 Imagens Grandes (9 x 6,8 cm)", 4), LAYOUT_4_IMG_MEDIAS("4 Imagens Médias (8 x 6,6 cm)", 4),
	LAYOUT_4_IMG_PEQUENAS("4 Imagens Pequenas (6,5 x 5,0 cm)", 4), LAYOUT_6_IMG("6 Imagens Médias (8 x 6,6 cm)", 6),
	LAYOUT_6_IMG_GRANDES("6 Imagens Grandes (9 x 6,8 cm)", 6), LAYOUT_8_IMG("8 Imagens Pequenas (6,5 x 5 cm)", 8),
	LAYOUT_8_IMG_GRANDES("8 Imagens Grandes", 8), LAYOUT_9_IMG("9 Imagens Pequenas (5,5 x 4 cm)", 9),
	LAYOUT_12_IMG("12 Imagens Pequenas (5,5 x 4 cm)", 12), LAYOUT_15_IMG("15 Imagens Pequenas (5,5 x 4 cm)", 15),
	LAYOUT_LAUDO_E_4_IMG("Laudo e 4 Imagens Pequenas(5,5 x 4 cm)", 4, true),
	LAYOUT_LAUDO_E_5_IMG("Laudo e 5 Imagens Pequenas(5,5 x 4 cm)", 5, true);

	private final String layout;
	private final int qtdeImagens;
	private final boolean possuiLaudo;

	private LAYOUT_IMG(String layout, int qtdeImagens) {
		this(layout, qtdeImagens, false);
	}

	private LAYOUT_IMG(String layout, int qtdeImagens, boolean possuiLaudo) {
		this.layout = layout;
		this.qtdeImagens = qtdeImagens;
		this.possuiLaudo = possuiLaudo;
	}

	public String getLayout() {
		return layout;
	}

	public boolean isPossuiLaudo() {
		return possuiLaudo;
	}

	public int getQtdeImagens() {
		return qtdeImagens;
	}

	@Override
	public String toString() {
		return layout;
	}

}
