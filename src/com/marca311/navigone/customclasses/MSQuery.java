package com.marca311.navigone.customclasses;

import com.marca311.navigone.AddressClasses.MSLocation;

public class MSQuery {
	private String theQuery = null;
	private MSLocation theLocation = null;
	
	//Constructor method
	public MSQuery() {
		//This class might not need a constructor
	}
	
	//Setter methods
	public void setQuery(String query) {
		theQuery = query;
	}
	public void setLocation(MSLocation location) {
		theLocation = location;
	}
	
	//Getter methods
	public void getSuggestionBox() {
		
	}
	public String getLocationName() {
		return null;
	}
	public MSLocation getLocation() {
		return theLocation;
	}
}
