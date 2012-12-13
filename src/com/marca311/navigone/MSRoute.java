package com.marca311.navigone;
import org.w3c.dom.*;

import java.io.*;
import com.marca311.navigone.MSVariation;

/*
 * Methods:
 * getFile, saveFile, parse, 
 */

public class MSRoute {
	private Document theXML = null;
	private File theFile = null;
	private int numberOfVariations;
	private MSVariation[] variations = null;
	private Element rootElement = null;
	
	//Methods
	//Constructor methods
	public MSRoute(Document theDocument) {
		setRoute(theDocument);
	}
	//Setter methods
	private void setRoute(Document theDocument) {
		setDocument(theDocument);
		setRootElement();
		setVariations(rootElement);
	}
	private void setDocument(Document theDocument) {
		theXML = theDocument;
	}
	private void setRootElement() {
		rootElement = theXML.getDocumentElement();
	}
	private void setFile() {
		
	}
	private void setVariations(Element theElement) {
		//Makes nodelist of all variations
		NodeList planList = null;
		planList = theElement.getElementsByTagName("plan");
		//Sets the number of variations
		numberOfVariations = planList.getLength();
		//Sets the size of variation array
		variations = new MSVariation[numberOfVariations];
		for (int x = 0; x < numberOfVariations; x++) {
			variations[x] = new MSVariation((Element) planList.item(x));
		}
	}
	
	//Getter methods
	
}
