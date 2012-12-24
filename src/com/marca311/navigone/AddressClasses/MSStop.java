package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;

import com.marca311.navigone.XMLParser;

public class MSStop extends MSLocation {
	static String stopNumber;
	static String stopName = null;
	
	//Methods
	//Constructor method
	public MSStop(Element theElement) {
		super(theElement);
		rootElement = theElement;
		setStopNumber();
		setStopName();
	}
	//Setter methods
	private void setStopNumber() {
		Element theElement = XMLParser.getElementChildByName("key", rootElement);
		stopNumber = theElement.getTextContent();
	}
	private void setStopName() {
		Element theElement = XMLParser.getElementChildByName("name", rootElement);
		stopName = theElement.getTextContent();
	}
	
	//Getter methods
	public String getHumanReadable() {
		return stopName + " (" +stopNumber + ")";
	}
}
