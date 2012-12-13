package com.marca311.navigone.AddressClasses;

public class MSStop extends MSLocation {
	static int stopNumber;
	static String stopName = null;
	
	//Methods
	//Setter methods
	public void setStop(int inputStopNumber, String inputStopName) {
		stopNumber = inputStopNumber;
		stopName = inputStopName;
	}
	
	//Getter methods
	public int getStopNumber() {
		return stopNumber;
	}
	public String getStopName() {
		return stopName;
	}
}
