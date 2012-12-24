package com.marca311.navigone;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class MSUtilities {
	public static String getDateForFile()
	{
		Calendar currentTime = Calendar.getInstance();
		int month = currentTime.get(2);
		int date = currentTime.get(5);
		int year = currentTime.get(1);
		int hour = currentTime.get(11);
		int minute = currentTime.get(12);
		int second = currentTime.get(13);
		String result = year + "." + month + "." + date + "/" + hour + ":" + minute + ":" + second;
		return result;
	}
	public static GregorianCalendar getCalendarFromString(String theString) {
		String[] formatSplit = theString.split("T");
		String[] dateSplit = formatSplit[0].split("-");
		String[] timeSplit = formatSplit[1].split(":");
		int year = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int day = Integer.parseInt(dateSplit[2]);
		int hour = Integer.parseInt(timeSplit[0]);
		int minute = Integer.parseInt(timeSplit[1]);
		int second = Integer.parseInt(timeSplit[2]);
		GregorianCalendar result = new GregorianCalendar(year, month, day, hour, minute, second);
		return result;
	}
	public static String getMinutePlural(int timeUnit) {
		if (timeUnit == 1) return " minute";
		else return " minutes";
	}
}
