package com.marca311.navigone.customclasses;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MSCalendar {
	private GregorianCalendar mainCalendar = null;
	private String timeString = null;
	private String dateString = null;
	private String serverString = null;
	
	//Constructor methods
	public MSCalendar() {
		mainCalendar = (GregorianCalendar) Calendar.getInstance();
	}
	public MSCalendar(GregorianCalendar theCalendar) {
		mainCalendar = theCalendar;
		//mainCalendar.
	}
	
	//Setter methods
	public void setTime(int hour, int minute) {
		mainCalendar.set(Calendar.HOUR, hour);
		if (hour > 11) mainCalendar.set(Calendar.AM_PM, 1);
		else mainCalendar.set(Calendar.AM_PM, 0);
		mainCalendar.set(Calendar.MINUTE, minute);
	}
	public void setDate(int day, int month, int year) {
		mainCalendar.set(Calendar.DAY_OF_MONTH, day);
		mainCalendar.set(Calendar.MONTH, month);
		mainCalendar.set(Calendar.YEAR, year);
	}
	private void setTimeString() {
		int hour = mainCalendar.get(GregorianCalendar.HOUR_OF_DAY);
		int minute = mainCalendar.get(GregorianCalendar.MINUTE);
		//int amOrPM = mainCalendar.get(GregorianCalendar.AM_PM);
		String ampmString = null;
		String stringMinute = minute+"";
		if (hour > 11) {
			if (hour != 12) hour -= 12;
			ampmString = "PM";
			mainCalendar.set(Calendar.AM_PM, 1);
		} else {
			ampmString = "AM";
			mainCalendar.set(Calendar.AM_PM, 0);
		}
		/*if (amOrPM == 1) {
			ampmString = "PM";
			if (hour != 12) {
				hour -=12;
			}
		} else {
			ampmString = "AM";
			if (hour == 0) hour = 12;
		}*/
		if (minute < 9) {
			stringMinute = "0"+minute;
		}
		timeString = hour+":"+stringMinute+" "+ampmString;
	}
	private void setDateString() {
		int day = mainCalendar.get(GregorianCalendar.DAY_OF_MONTH);
		int month = mainCalendar.get(GregorianCalendar.MONTH);
		int year = mainCalendar.get(GregorianCalendar.YEAR);
		String monthString = getMonthNames(month);
		dateString = day+" "+monthString+" "+year;
	}
	public static String getMonthNames(int monthNumber) {
		switch (monthNumber) {
		case 0:
			return "January";
		case 1:
			return "February";
		case 2:
			return "March";
		case 3:
			return "April";
		case 4:
			return "May";
		case 5:
			return "June";
		case 6:
			return "July";
		case 7:
			return "August";
		case 8:
			return "September";
		case 9:
			return "October";
		case 10:
			return "November";
		case 11:
			return "December";
		}
		return null;
	}
	
	//Getter methods
	public GregorianCalendar getCalendar() {
		return mainCalendar;
	}
	public String getTimeString() {
		setTimeString();
		return timeString;
	}
	public String getDateString() {
		setDateString();
		return dateString;
	}
	public String getServerQueryable() {
		String timeString = mainCalendar.get(Calendar.HOUR)+":"+mainCalendar.get(Calendar.MINUTE);
		String dateString = mainCalendar.get(Calendar.YEAR)+"-"+mainCalendar.get(Calendar.MONTH)+"-"+mainCalendar.get(Calendar.DAY_OF_MONTH);
		String result = "&time="+timeString+"&date="+dateString;
		return result;
	}
}
