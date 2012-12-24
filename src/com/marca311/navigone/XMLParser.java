package com.marca311.navigone;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
//import com.marca311.navigone.MSUtilities;

//This class is mostly for processing things that are used in multiple places.
public abstract class XMLParser {
	static String xmlDirectory = "/data/data/com.marca311.navigone/xmls/";
	//Sends the URL and parses it
	public static Document getAndParseXML(String theURL)
	{
		Document result = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			result = db.parse(new URL(theURL).openStream());
		} catch (DOMException exception) {
			
		} catch (MalformedURLException e) {
			System.out.println("There is a problem with the URL");
			e.printStackTrace();
		}  catch (IOException e) {
			System.out.println("There was a problem retrieving the file from the server");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfigurationException");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
		
		return result;
	}
	public static Document getAndParseXML(File theFile) {
		if (!theFile.exists()) {
			System.out.println("The file does not exist");
			return null;
		}
		Document result = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			result = db.parse(theFile);
		} catch (Exception e) {
			System.out.println("Error loading file");
			e.printStackTrace();
		}
		return result;
	}
	public static Element getElementChildByName(String elementName, Element theElement) {
		NodeList elementList = theElement.getElementsByTagName(elementName);
		Node testingNode = elementList.item(0);
		short nodeType = testingNode.getNodeType();
		int i = 0;
		while (nodeType != 1) {
			i++;
			testingNode = elementList.item(i);
			nodeType = testingNode.getNodeType();
		}
		Element result = (Element) testingNode;
		return result;
	}
	public static Element getElementChild(Element theElement) {
		NodeList childNodes = theElement.getChildNodes();
		Node testingNode = childNodes.item(0);
		short nodeType = testingNode.getNodeType();
		int i = 0;
		while (nodeType != 1) {
			i++;
			testingNode = childNodes.item(i);
			nodeType = testingNode.getNodeType();
		}
		Element result = (Element) testingNode;
		return result;
		/*
		Element result = (Element) theElement.getFirstChild();
		Node otherResult = theElement.getFirstChild();
		return result; */
	}
	public static Element getElementSibling(Element theElement) {
		Element result = (Element) theElement.getNextSibling();
		return result;
	}
}
