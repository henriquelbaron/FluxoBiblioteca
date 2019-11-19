package br.com.fluxo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date addDiaData(Date data, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, dias);
		return c.getTime();
	}

	public static Date addHorasData(Date data, int hora) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.HOUR, hora);
		return c.getTime();
	}

	public static Date addMinutosData(Date data, int minutos) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.MINUTE, minutos);
		return c.getTime();
	}

	public static Date dataAtual() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * formato dd/MM/yyyy HH:mm
	 */
	public static Date stringToDate(String data, String hora) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdf.setLenient(false);
			return sdf.parse(data + " " + hora);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
