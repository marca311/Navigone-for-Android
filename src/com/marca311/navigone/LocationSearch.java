package com.marca311.navigone;

import org.w3c.dom.*;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.marca311.navigone.XMLParser;

public class LocationSearch {
	//Object variable storage
	String APIKey = "VzHTwXmEnjQ0vUG0U3y9";
	Element rootElement = null;
	SQLiteDatabase database;
	
	//Gets the XML file from the server
	public void getXMLFile(String query) {
		String fixedQuery = query.replace(" ", "+");
		String URL = "http://api.winnipegtransit.com/locations:" + fixedQuery
				+ "?api-key=" + APIKey;
		Document xmlFile = XMLParser.getAndParseXML(URL);
		rootElement = xmlFile.getDocumentElement();
	}
	
	//Gets the Address name from the file 
	public String getAddressName() {
		
		return null;
	}
	public String getAddressKey() {
		
		return null;
	}
}
