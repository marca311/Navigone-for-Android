package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;

public class MSStop extends MSLocation {
	static int stopNumber;
	static String stopName = null;
	
	//Methods
	//Constructor method
	public MSStop(Element theElement) {
		super(theElement);
	}
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
