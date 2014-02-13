package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;

import com.marca311.navigone.XMLParser;

public class MSLocation {
	//Variables
	private String latitude = null;
	private String longitude = null;
	protected Element rootElement = null;
	
	//Methods
	//Constructor method
	public MSLocation(Element theElement) {
		rootElement = theElement;
		setCoordinates();
	}
	
	//Setter methods
	private void setCoordinates() {
		Element theElement = XMLParser.getElementChildByName("geographic", rootElement);
		Element childElement = XMLParser.getElementChildByName("latitude", theElement);
		latitude = childElement.getTextContent();
		childElement = XMLParser.getElementChildByName("longitude", theElement);
		longitude = childElement.getTextContent();
	}
	//Getter methods
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public String toString() {
		return latitude + ", " + longitude;
	}
	public String getServerQueryable() {
		//TODO: fix method, there needs to be something here
		return null;
	}
}
