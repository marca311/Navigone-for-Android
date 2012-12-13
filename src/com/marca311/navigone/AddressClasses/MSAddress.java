package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;

public class MSAddress extends MSLocation {
	private int streetNumber;
	private String streetName = null;
	private Element rootElement = null;
	
	//Methods
	//Constructor method
	public MSAddress(Element theElement)  {
		rootElement = theElement;
	}
	//Setter methods
	private void setAddress() {
		setStreetNumber();
		setStreetName();
	}
	private void setStreetNumber() {
		
	}
	private void setStreetName() {
		
	}
	
	//Getter methods
	public String humanReadable() {
		String result = streetNumber + " " + streetName;
		return result;
	}
}
