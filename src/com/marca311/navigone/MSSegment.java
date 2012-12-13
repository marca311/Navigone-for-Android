package com.marca311.navigone;

import java.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import com.marca311.navigone.AddressClasses.*;

public class MSSegment {
	private String type = null;
	private GregorianCalendar startTime = null;
	private GregorianCalendar endTime = null;
	private String totalTime = null;
	private String walkingTime = null;
	private String waitingTime = null;
	private String ridingTime = null;
	private MSLocation fromLocation = null;
	private MSLocation toLocation = null;
	//Element
	private Element rootElement = null;
	
	//Methods
	//Constructor method
	public MSSegment(Element theElement) {
		rootElement = theElement;
		setSegment();
	}
	//Setter methods
	private void setSegment() {
		setType();
		setTimes();
		setLocations();
	}
	private void setType() {
		//Add navigation to object
		type = rootElement.getAttribute("type");
	}
	private void setTimes() {
		Element workingElement = XMLParser.getElementChildByName("times", rootElement);
		Element timeElement = XMLParser.getElementChildByName("total", workingElement);
		totalTime = timeElement.getTextContent();
		if (type.equals("walk")) {
			timeElement = XMLParser.getElementChildByName("walking", workingElement);
			walkingTime = timeElement.getTextContent();
		} else if (type.equals("transfer")) {
			timeElement = XMLParser.getElementChildByName("waiting", workingElement);
			waitingTime = timeElement.getTextContent();
			timeElement = XMLParser.getElementChildByName("walking", workingElement);
			walkingTime = timeElement.getTextContent();
		} else if (type.equals("ride")) {
			timeElement = XMLParser.getElementChildByName("riding", workingElement);
			ridingTime = timeElement.getTextContent();
		}
		timeElement = XMLParser.getElementChildByName("start", workingElement);
		startTime = MSUtilities.getCalendarFromString(timeElement.getTextContent());
		timeElement = XMLParser.getElementChildByName("end", workingElement);
		endTime = MSUtilities.getCalendarFromString(timeElement.getTextContent());
	}
	private void setLocations() {
		if (type.equals("ride")) {
			return;
		} else {
			Element theElement = XMLParser.getElementChildByName("from", rootElement);
			fromLocation = setLocationClass(theElement);
			theElement = XMLParser.getElementChildByName("to", rootElement);
			toLocation = setLocationClass(theElement);
		}
	}
	private MSLocation setLocationClass(Element theElement) {
		Element childElement = XMLParser.getElementChild(theElement);
		String locationType = childElement.getTagName();
		MSLocation result = null;
		if (locationType.equals("origin") || locationType.equals("destination")) {
			childElement = XMLParser.getElementChild(childElement);
		} if (locationType.equals("address")) {
			result = new MSAddress(childElement);
		} else if (locationType.equals("stop")) {
			result = new MSStop();
		} else if (locationType.equals("monument")) {
			result = new MSMonument();
		} else if (locationType.equals("point")) {
			result = new MSLocation();
		}
		return result;
	}
}
