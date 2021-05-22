package com.laudoecia.api.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import com.laudoecia.api.modelo.enuns.EnumSexo;

public class Utils {
	public static EntityManagerFactory entidade;

	public static LocalDate ConverterToLocalDate(Date date) {
		try {
			LocalDate localdata = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return localdata;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date ConverterToDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	public static Date TransformandoEmDate(String data) {
		Date dat = null;

		if (data.replaceAll("[_/]", "").isEmpty()) {
		} else {
			try {
				DateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
				dat = (Date) formata.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dat;
	}

	public static Date TransformaDoBancoEmDate(String data) {
		String[] teste = data.split(" ");

		if (teste.length == 1)
			data += " 00:00:00";

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(data);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String dateToString(Date date, String formato) {
		return new SimpleDateFormat(formato, new Locale("pt", "BR")).format(date);
	}

	public static String dataExtenso(Date date) {
		return new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR")).format(date);
	}

	public static Date subtrairDiasNaData(Date data, int numeroDiasParaSubtrair) {
		return adicionarDiasNaData(data, numeroDiasParaSubtrair * -1);
	}

	public static Date adicionarDiasNaData(Date data, int numeroDiasParaAdicionar) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, numeroDiasParaAdicionar);
		return calendar.getTime();
	}
	
	public static EnumSexo VerificaSexo(String sexo) {
		if(sexo.startsWith("m") || sexo.startsWith("M"))
			return EnumSexo.MASCULINO;
		else if(sexo.startsWith("f") || sexo.startsWith("F"))
			return EnumSexo.FEMININO;
		else
			return EnumSexo.INDEFINIDO;
	}

}
