package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;

public class MSLocation {
	//Variables
	private String locationKey = null;
	private String latitude = null;
	private String longitude = null;
	
	//Methods
	//Constructor method
	public MSLocation(Element theElement) {
		
	}
	
	//Setter methods
	private void setKey(String key) {
		locationKey = key;
	}
	private void setCoordinates(String inputLatitude, String inputLongitude) {
		latitude = inputLatitude;
		longitude = inputLongitude;
	}
	//Getter methods
	public String getKey() {
		return locationKey;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
}
