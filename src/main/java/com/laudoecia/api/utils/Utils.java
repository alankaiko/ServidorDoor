package com.laudoecia.api.utils;

import java.awt.Dimension;
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

import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.Weeks;

public class Utils {
	public static EntityManagerFactory entidade;

	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

		int original_width = imgSize.width;
		int original_height = imgSize.height;
		int bound_width = boundary.width;
		int bound_height = boundary.height;
		int new_width = original_width;
		int new_height = original_height;

		if (original_width > bound_width) {
			new_width = bound_width;
			new_height = (new_width * original_height) / original_width;
		}

		if (new_height > bound_height) {
			new_height = bound_height;
			new_width = (new_height * original_width) / original_height;
		}

		return new Dimension(new_width, new_height);
	}

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
		
		if(teste.length == 1)
			data += " 00:00:00";
		
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse(data);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		} catch(Exception e) {
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

	public static boolean beforeConsiderandoSohHorario(Date data1, Date data2) {
		return compararHorarios(data1, data2) < 0;
	}

	public static int compararHorarios(Date data1, Date data2) {
		Integer horas1, minutos1, segundos1, milisegundos1;
		Integer horas2, minutos2, segundos2, milisegundos2;

		horas1 = getHourOfDay(data1);
		minutos1 = getMinutes(data1);
		segundos1 = getSeconds(data1);
		milisegundos1 = getMilliseconds(data1);

		horas2 = getHourOfDay(data2);
		minutos2 = getMinutes(data2);
		segundos2 = getSeconds(data2);
		milisegundos2 = getMilliseconds(data2);

		Integer[] horario1 = new Integer[] { horas1, minutos1, segundos1, milisegundos1 };
		Integer[] horario2 = new Integer[] { horas2, minutos2, segundos2, milisegundos2 };

		for (int i = 0; i < horario1.length; i++) {
			int result = horario1[i].compareTo(horario2[i]);
			if (result != 0)
				return result;
		}
		return 0;
	}

	public static Date setTime(Date data, Date horario) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, getHourOfDay(horario));
		c.set(Calendar.MINUTE, getMinutes(horario));
		c.set(Calendar.SECOND, getSeconds(horario));
		c.set(Calendar.MILLISECOND, getMilliseconds(horario));
		return c.getTime();
	}

	public static int diasEntreAsDatas(Date inicio, Date fim) {
		return Days.daysBetween(new DateMidnight(inicio.getTime()), new DateMidnight(fim.getTime())).getDays();
	}

	public static int semanasEntreAsDatas(Date inicio, Date fim) {
		return Weeks.weeksBetween(new DateMidnight(inicio.getTime()), new DateMidnight(fim.getTime())).getWeeks();
	}

	public static Date subtrairMinutosNaData(Date data, int numMinutos) {
		return adicionarMinutosNaData(data, -1 * numMinutos);
	}

	public static Date adicionarMinutosNaData(Date data, int numMinutos) {
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.MINUTE, numMinutos);
		return calendarData.getTime();
	}

	public static Date adicionarSegundosNaData(Date data, int numSegundos) {
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.SECOND, numSegundos);
		return calendarData.getTime();
	}

	public static Date addMiliSegNaData(Date data, int numMiliSeg) {
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		calendarData.add(Calendar.MILLISECOND, numMiliSeg);
		return calendarData.getTime();
	}

	public static Date dataAtualSemHorario() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
		String dataAtual = sdf.format(new Date());
		try {
			return sdf.parse(dataAtual);
		} catch (ParseException ex) {
			return null;
		}
	}

	public static Date adicionarNaData(Date data, int campo, int qtde) {
		if (data == null) {
			return null;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(data);
			c.add(campo, qtde);
			return c.getTime();
		}
	}

	public static int getDayOfMonth(Date date) {
		return getField(date, Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		return getField(date, Calendar.MONTH);
	}

	public static int getYear(Date date) {
		return getField(date, Calendar.YEAR);
	}

	public static int getHourOfDay(Date d) {
		return getField(d, Calendar.HOUR_OF_DAY);
	}

	public static int getMinutes(Date d) {
		return getField(d, Calendar.MINUTE);
	}

	public static int getSeconds(Date d) {
		return getField(d, Calendar.SECOND);
	}

	public static int getMilliseconds(Date d) {
		return getField(d, Calendar.MILLISECOND);
	}

	private static int getField(Date d, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(field);
	}

}
