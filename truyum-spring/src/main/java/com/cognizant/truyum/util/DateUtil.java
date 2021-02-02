package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date convertToDate(String date)
	{
		Date mDate=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			mDate= sdf.parse(date);
		} 
		catch (ParseException e)
		{
			System.out.println("Date Fromatting error");
		}
		return mDate;
	}

}
