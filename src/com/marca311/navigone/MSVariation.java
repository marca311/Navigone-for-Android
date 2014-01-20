package com.marca311.navigone;

import java.util.*;
import org.w3c.dom.*;

public class MSVariation {
	//Time variables
	private String walkingTime = null;
	private String waitingTime = null;
	private String ridingTime = null;
	private String totalTime = null;
	//Date variables
	private GregorianCalendar startTime = null;
	private GregorianCalendar endTime = null;
	//Other variables
	private int numberOfSegments;
	private MSSegment[] segments = null;
	//Element
	private Element rootElement = null;
	
	//Methods
	//Constructor methods
	public MSVariation(Element theElement) {
		rootElement = theElement;
		setTimes();
		setSegments();
	}
	//Setter methods
	private void setTimes() {
		Element workingElement = XMLParser.getElementChildByName("times", rootElement);
		//workingElement = XMLParser.getElementChildByName("durations", workingElement);
		Element timeElement = XMLParser.getElementChildByName("total", workingElement);
		totalTime = timeElement.getTextContent();
		timeElement = XMLParser.getElementChildByName("walking", workingElement);
		walkingTime = timeElement.getTextContent();
		timeElement = XMLParser.getElementChildByName("waiting", workingElement);
		waitingTime = timeElement.getTextContent();
		timeElement = XMLParser.getElementChildByName("riding", workingElement);
		ridingTime = timeElement.getTextContent();
		timeElement = XMLParser.getElementChildByName("start", workingElement);
		startTime = MSUtilities.getCalendarFromString(timeElement.getTextContent());
		timeElement = XMLParser.getElementChildByName("end", workingElement);
		endTime = MSUtilities.getCalendarFromString(timeElement.getTextContent());
	}
	private void setSegments() {
		//Makes nodelist of all segments
		NodeList segmentList = null;
		segmentList = rootElement.getElementsByTagName("segment");
		//Sets the number of segments
		numberOfSegments = segmentList.getLength();
		//Sets the size of segment array
		segments = new MSSegment[numberOfSegments];
		for (int x = 0; x < numberOfSegments; x++) {
			segments[x] = new MSSegment((Element) segmentList.item(x));
		}
	}
	
	//Getter methods
	public String[] getHumanReadable() {
		ArrayList<String> listOfStrings = new ArrayList<String>();
		//Navigating the segments
		for (int i = 0; i < numberOfSegments; i++) {
			String[] currentSegment = segments[i].getHumanReadable();
			//Navigating the parts of the segments
			for (int x = 0; x < currentSegment.length; x++) {
				listOfStrings.add(currentSegment[x]);
			}
		}
		String[] result = new String[listOfStrings.size()];
		result = listOfStrings.toArray(result);
		return result;
	}
}
