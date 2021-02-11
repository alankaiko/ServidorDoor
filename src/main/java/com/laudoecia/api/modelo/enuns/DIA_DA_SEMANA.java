package com.laudoecia.api.modelo.enuns;

import java.util.Calendar;
import java.util.Date;

import com.laudoecia.api.utils.Utils;

public enum DIA_DA_SEMANA {
	DOMINGO("Domingo", 0), SEGUNDA("Segunda-feira", 1), TERCA("Terça-feira", 2), QUARTA("Quarta-feira", 3),
	QUINTA("Quinta-feira", 4), SEXTA("Sexta-feira", 5), SABADO("Sábado", 6);

	private final String porExtenso;
	private final int porNumero;

	private DIA_DA_SEMANA(String porExtenso, int porNumero) {
		this.porExtenso = porExtenso;
		this.porNumero = porNumero;
	}

	public int getPorNumero() {
		return porNumero;
	}

	public String getPorExtenso() {
		return porExtenso;
	}

	@Override
	public String toString() {
		return porExtenso;
	}

	public DIA_DA_SEMANA proximoDiaDaSemana() {
		switch (this) {
		case DOMINGO:
			return SEGUNDA;
		case SEGUNDA:
			return TERCA;
		case TERCA:
			return QUARTA;
		case QUARTA:
			return QUINTA;
		case QUINTA:
			return SEXTA;
		case SEXTA:
			return SABADO;
		case SABADO:
			return DOMINGO;
		default:
			return null;
		}
	}

	public Date proximaOcorrencia() {
		Date data = new Date();
		while (diaDaSemana(data) != this) {
			data = Utils.adicionarDiasNaData(data, 1);
		}
		return data;
	}

	public static DIA_DA_SEMANA hoje() {
		return diaDaSemana(new Date());
	}

	public static DIA_DA_SEMANA diaDaSemana(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			return DIA_DA_SEMANA.DOMINGO;
		case Calendar.MONDAY:
			return DIA_DA_SEMANA.SEGUNDA;
		case Calendar.TUESDAY:
			return DIA_DA_SEMANA.TERCA;
		case Calendar.WEDNESDAY:
			return DIA_DA_SEMANA.QUARTA;
		case Calendar.THURSDAY:
			return DIA_DA_SEMANA.QUINTA;
		case Calendar.FRIDAY:
			return DIA_DA_SEMANA.SEXTA;
		case Calendar.SATURDAY:
			return DIA_DA_SEMANA.SABADO;
		default:
			throw new AssertionError();
		}
	}

}
