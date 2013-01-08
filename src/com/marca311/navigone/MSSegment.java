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
	private int totalTime;
	private int walkingTime;
	private int waitingTime;
	private int ridingTime;
	private MSLocation fromLocation = null;
	private MSLocation toLocation = null;
	private String busVariation = null;
	private String busNumber = null;
	private String routeName = null;
	private String variationDestination = null;
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
		if (type.equals("ride")) {
			setBusVariation();
			setBusNumber();
			setRouteName();
			setVariationDestination();
		} else {
			setLocations();
		}
	}
	private void setType() {
		//Add navigation to object
		type = rootElement.getAttribute("type");
	}
	private void setTimes() {
		Element workingElement = XMLParser.getElementChildByName("times", rootElement);
		Element timeElement = XMLParser.getElementChildByName("total", workingElement);
		totalTime = Integer.parseInt(timeElement.getTextContent());
		if (type.equals("walk")) {
			timeElement = XMLParser.getElementChildByName("walking", workingElement);
			walkingTime = Integer.parseInt(timeElement.getTextContent());
		} else if (type.equals("transfer")) {
			timeElement = XMLParser.getElementChildByName("waiting", workingElement);
			waitingTime = Integer.parseInt(timeElement.getTextContent());
			timeElement = XMLParser.getElementChildByName("walking", workingElement);
			walkingTime = Integer.parseInt(timeElement.getTextContent());
		} else if (type.equals("ride")) {
			timeElement = XMLParser.getElementChildByName("riding", workingElement);
			ridingTime = Integer.parseInt(timeElement.getTextContent());
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
	//This method is accessed in MSRoute to set origin and destination of route
	public static MSLocation setLocationClass(Element theElement) {
		Element childElement = XMLParser.getElementChild(theElement);
		String locationType = childElement.getTagName();
		MSLocation result = null;
		if (locationType.equals("origin") || locationType.equals("destination")) {
			childElement = XMLParser.getElementChild(childElement);
		}
		locationType = childElement.getTagName();
		if (locationType.equals("address")) {
			result = new MSAddress(childElement);
		} else if (locationType.equals("stop")) {
			result = new MSStop(childElement);
		} else if (locationType.equals("monument")) {
			result = new MSMonument(childElement);
		} else if (locationType.equals("point")) {
			result = new MSLocation(childElement);
		} else if (locationType.equals("intersection")) {
			
		}
		return result;
	}
	
	private void setBusVariation() {
		Element theElement = XMLParser.getElementChildByName("variant", rootElement);
		theElement = XMLParser.getElementChildByName("key", theElement);
		busVariation = theElement.getTextContent();
	}
	private void setBusNumber() {
		Element theElement = XMLParser.getElementChildByName("variant", rootElement);
		theElement = XMLParser.getElementChildByName("key", theElement);
		String variation = theElement.getTextContent();
		String[] splitVariation = variation.split("-");
		busNumber = splitVariation[0];
	}
	private void setRouteName() {
		Element theElement = XMLParser.getElementChildByName("variant", rootElement);
		theElement = XMLParser.getElementChildByName("name", theElement);
		String variation = theElement.getTextContent();
		String[] splitVariation = variation.split(" to ");
		routeName = splitVariation[0];
	}
	private void setVariationDestination() {
		Element theElement = XMLParser.getElementChildByName("variant", rootElement);
		theElement = XMLParser.getElementChildByName("name", theElement);
		String variation = theElement.getTextContent();
		String[] splitVariation = variation.split(" to ");
		variationDestination = splitVariation[1];
	}
	
	//Getter methods
	public String[] getHumanReadable() {
		String[] result = null;
		if (type.equals("walk")) {
			result = new String[3];
			result[0] = fromLocation.getHumanReadable();
			result[1] = "Walk " + walkingTime + MSUtilities.getMinutePlural(walkingTime);
			result[2] = toLocation.getHumanReadable();
		} else if (type.equals("transfer")) {
			if (walkingTime > 0 && waitingTime == 0) {
				result = new String[3];
				result[0] = fromLocation.getHumanReadable();
				result[1] = "Walk " + walkingTime + MSUtilities.getMinutePlural(walkingTime);
				result[2] = toLocation.getHumanReadable();
			} else if (waitingTime > 0 && walkingTime == 0) {
				result = new String[1];
				result[0] = "Wait " + waitingTime + MSUtilities.getMinutePlural(waitingTime) + " at " + fromLocation.getHumanReadable();
			}
			else if (waitingTime > 0 && walkingTime > 0) {
				result = new String[3];
				result[0] = fromLocation.getHumanReadable();
				result[1] = "Walk " + walkingTime + MSUtilities.getMinutePlural(walkingTime);
				result[2] = "Wait " + waitingTime + MSUtilities.getMinutePlural(waitingTime) + " at " + toLocation.getHumanReadable();
			}
		} else if (type.equals("ride")) {
			result = new String[1];
			result[0] = "Ride " + busNumber + " " + routeName + " (" + variationDestination + ")";
		}
		return result;
	}
}
