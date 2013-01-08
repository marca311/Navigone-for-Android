package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;
import com.marca311.navigone.XMLParser;

public class MSIntersection {
	private Element rootElement = null;
	private String intersectionKey = null;
	private String intersectionName = null;
	private String streetKey = null;
	private String streetName = null;
	private String streetType = null;
	private String streetAbbr = null;
	private String crossStreetKey = null;
	private String crossStreetName = null;
	private String crossStreetType = null;
	private String crossStreetAbbr = null;
	
	//Methods
	//Constructor method
	public MSIntersection(Element theElement) {
		rootElement = theElement;
	}
	
	//Setter methods
	private void setIntersectionKey() {
		Element theElement = XMLParser.getElementChildByName("key", rootElement);
		String fullKey = theElement.getTextContent();
		String[] splitKey = fullKey.split(":");
		intersectionKey = splitKey[0];
	}
	//Set this after the street and cross street are identified
	private void setIntersectionName() {
		
	}
	private void setStreetKey() {
		Element theElement = XMLParser.getElementChildByName("street", rootElement);
		theElement = XMLParser.getElementChildByName("key", theElement);
		streetKey = theElement.getTextContent();
	}
	private void setStreetName() {
		//Set this after type maybe? I'll do this later.
	}
	private void setStreetType() {
		//Element theElement = XMLParser.getElementChildByName(", theElement)
	}
	private void setStreetAbbr() {
		
	}
	private void crossStreetKey() {
		
	}
	private void crossStreetName() {
		
	}
	private void crossStreetType() {
		
	}
	private void crossStreetAbbr() {
		
	}
	
	//Getter methods
}
