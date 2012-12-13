package com.marca311.navigone.AddressClasses;

public class MSMonument extends MSLocation {
	static String monumentName = null;
	static String monumentCatagory = null;
	static int streetNumber;
	static String streetName = null;
	
	//Methods
	//Setter methods
	public void setMonument(String inputMonumentName, String inputMonumentCatagory,
			int inputMonumentNumber, String inputMonumentStreet) {
		monumentName = inputMonumentName;
		monumentCatagory = inputMonumentCatagory;
		streetNumber = inputMonumentNumber;
		streetName = inputMonumentStreet;
	}
	
	//Getter methods
	public String humanReadable() {
		String result = monumentName + "(" + streetNumber + " " + streetName + ")";
		return result;
	}
}
