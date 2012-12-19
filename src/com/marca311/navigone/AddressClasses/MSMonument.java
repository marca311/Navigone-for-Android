package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;
import com.marca311.navigone.XMLParser;

public class MSMonument extends MSLocation {
	private String locationKey = null;
	private String monumentName = null;
	private String monumentCatagory = null;
	private String streetNumber = null;
	private String streetName = null;
	
	//Methods
	//Constructor method
	public MSMonument(Element theElement) {
		super(theElement);
	}
	//Setter methods
	private void setMonument() {
		setKey();
		setMonumentName();
		setMonumentCatagory();
		setStreetNumber();
		setStreetName();
	}
	private void setKey() {
		Element theElement = XMLParser.getElementChildByName("key", rootElement);
		locationKey = "monuments/" + theElement.getTextContent();
	}
	private void setMonumentName() {
		Element theElement = XMLParser.getElementChildByName("name", rootElement);
		monumentName = theElement.getTextContent();
	}
	private void setMonumentCatagory() {
		Element theElement = XMLParser.getElementChildByName("catagory", rootElement);
		monumentCatagory = theElement.getTextContent();
	}
	private void setStreetNumber() {
		Element addressElement = XMLParser.getElementChildByName("address", rootElement);
		Element childElement = XMLParser.getElementChildByName("street-number", addressElement);
		streetNumber = childElement.getTextContent();
	}
	private void setStreetName() {
		Element addressElement = XMLParser.getElementChildByName("address", rootElement);
		Element childElement = XMLParser.getElementChildByName("name", addressElement);
		streetName = childElement.getTextContent();
	}
	
	//Getter methods
	public String getKey() {
		return locationKey;
	}
	public String getMonumentName() {
		return monumentName;
	}
	public String getMonumentCatagory() {
		return monumentCatagory;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public String getHumanReadable() {
		String result = monumentName + "(" + streetNumber + " " + streetName + ")";
		return result;
	}
}
