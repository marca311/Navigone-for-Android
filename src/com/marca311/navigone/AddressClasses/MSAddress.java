package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;

import com.marca311.navigone.XMLParser;

import android.sax.RootElement;

public class MSAddress extends MSLocation {
	private String locationKey = null;
	private String streetNumber;
	private String streetName = null;
	
	//Methods
	//Constructor method
	public MSAddress(Element theElement)  {
		super(theElement);
		setKey();
		setAddress();		
	}
	//Setter methods
	private void setKey() {
		Element theElement = XMLParser.getElementChildByName("key", rootElement);
		locationKey = "addresses/" + theElement.getTextContent();
	}
	private void setAddress() {
		setStreetNumber();
		setStreetName();
	}
	private void setStreetNumber() {
		Element theElement = XMLParser.getElementChildByName("street-number", rootElement);
		streetNumber = theElement.getTextContent();
	}
	private void setStreetName() {
		Element theElement = XMLParser.getElementChildByName("name", rootElement);
		streetName = theElement.getTextContent();
	}
	
	//Getter methods
	public String getHumanReadable() {
		String result = streetNumber + " " + streetName;
		return result;
	}
	public String getServerQueryable() {
		return "addresses/"+locationKey;
	}
}
