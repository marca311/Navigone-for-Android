package com.marca311.navigone;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class MSUtilities {
	public static String getDateForFile() {
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
	
	public static String getReadableTime(int hour, int minute) {
		String amOrPm = null;
		String stringMinute = minute+"";
		if (hour >= 12) {
			amOrPm = "PM";
			hour -= 12;
		} else amOrPm = "AM";
		if (minute < 9) {
			stringMinute = "0"+minute;
		}
		return hour+":"+stringMinute+" "+amOrPm;
	}
	
	public static String getReadableDate(int year, int month, int day) {
		Calendar theCalendar = new GregorianCalendar(year, month, day);
		String monthString = getMonthNames(month);
		String result = day+" "+monthString+" "+year;
		return result;
	}
	
	public static String getMinutePlural(int timeUnit) {
		if (timeUnit == 1) return " minute";
		else return " minutes";
	}
	
	public static String getMonthNames(int monthNumber) {
		switch (monthNumber) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		}
		return null;
	}
}
