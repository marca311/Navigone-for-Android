package com.marca311.navigone.AddressClasses;

import org.w3c.dom.*;
import com.marca311.navigone.XMLParser;

public class MSIntersection extends MSLocation {
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
		super(theElement);
		rootElement = theElement;
		setStreetKey();
		setStreetType();
		setStreetAbbr();
		setStreetName();
		setCrossStreetKey();
		setCrossStreetType();
		setCrossStreetAbbr();
		setCrossStreetName();
		setIntersectionKey();
		setIntersectionName();
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
		intersectionName = streetName+" "+streetType+" @ "+crossStreetName+" "+crossStreetType;
	}
	private void setStreetKey() {
		Element theElement = XMLParser.getElementChildByName("street", rootElement);
		theElement = XMLParser.getElementChildByName("key", theElement);
		streetKey = theElement.getTextContent();
	}
	private void setStreetName() {
		//Call setStreetType() before calling this method
		Element theElement = XMLParser.getElementChildByName("street", rootElement);
		theElement = XMLParser.getElementChildByName("name", theElement);
		streetName = theElement.getTextContent();
		streetName = streetName.replaceAll(" "+streetType, "");
	}
	private void setStreetType() {
		//TODO: double check element names on all methods in this class
		Element theElement = XMLParser.getElementChildByName("street", rootElement);
		theElement = XMLParser.getElementChildByName("type", theElement);
		streetType = theElement.getTextContent();
	}
	private void setStreetAbbr() {
		Element theElement = XMLParser.getElementChildByName("street", rootElement);
		theElement = XMLParser.getElementChildByName("type", theElement);
		streetAbbr = theElement.getAttribute("abbr");
	}
	private void setCrossStreetKey() {
		Element theElement = XMLParser.getElementChildByName("cross-street", rootElement);
		theElement = XMLParser.getElementChildByName("key", theElement);
		crossStreetKey = theElement.getTextContent();
	}
	private void setCrossStreetName() {
		//Call setCrossStreetType() first
		Element theElement = XMLParser.getElementChildByName("cross-street", rootElement);
		theElement = XMLParser.getElementChildByName("name", theElement);
		crossStreetName = theElement.getTextContent();
		crossStreetName = crossStreetName.replaceAll(" "+crossStreetType, "");
	}
	private void setCrossStreetType() {
		Element theElement = XMLParser.getElementChildByName("cross-street", rootElement);
		theElement = XMLParser.getElementChildByName("type", theElement);
		crossStreetType = theElement.getTextContent();
	}
	private void setCrossStreetAbbr() {
		Element theElement = XMLParser.getElementChildByName("cross-street", rootElement);
		theElement = XMLParser.getElementChildByName("type", theElement);
		crossStreetAbbr = theElement.getAttribute("abbr");
	}
	
	//Getter methods
	public String getHumanReadable() {
		return intersectionName;
	}
	public String getServerQueryable() {
		return "intersections/"+intersectionKey;
	}
}
