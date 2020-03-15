package com.laudoecia.api.utils;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.dcm4che3.data.Attributes;

public class Utils {

	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
	
	public static LocalDate ConverterToLocalDate(Date date) {
		try {
			LocalDate localdata = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
			return localdata;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date ConverterToDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);
	}
	
	public static Date TransformandoEmDate(String data){
		Date dat = null;
		
		if(data.replaceAll("[_/]", "").isEmpty()){
			//return null;
		}else{
			try {
				DateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
				dat = (Date)formata.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dat;
	}
	
}
