package com.wpp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import org.apache.commons.beanutils.Converter;

public class StringTransformDate implements Converter {

	@Override
	public Date convert(Class arg0, Object arg1) {
		String time=(String)arg1;
		SimpleDateFormat simple = new SimpleDateFormat("yy-mm-dd");
		Date date=null;
		try {
			date = simple.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
