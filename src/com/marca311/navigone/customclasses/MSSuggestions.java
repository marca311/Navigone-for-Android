package com.marca311.navigone.customclasses;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;

import com.marca311.navigone.MSSegment;
import com.marca311.navigone.XMLParser;
import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.Apikey;

public class MSSuggestions extends AsyncTask<String, Integer, MSLocation[]> implements TextWatcher {
	
	private String query;
	private MSLocation[] locations;
	private Document queryDocument;
	
	public void queryServer(String input) {
		System.out.println("SERVER QUERIED");
		query = input;
		String url = "http://api.winnipegtransit.com/locations:"+query
		+"?api-key="+Apikey.getApiKey();
		System.out.println("URL: "+url);
		queryDocument = XMLParser.getAndParseXML(url);
		setLocationArray();
	}
	private void setLocationArray() {
		Element rootElement = queryDocument.getDocumentElement();
		Element locationElement = queryDocument.getDocumentElement();
		ArrayList<MSLocation> locationsList = new ArrayList<MSLocation>();
		do {
			MSLocation test = MSSegment.setLocationClass(locationElement);
			 locationsList.add(test);
			 if (test == null) {
				 System.out.println("IS NULL");
			 } else {
				 System.out.println(test.getHumanReadable());
			 }
		} while ((locationElement = XMLParser.getElementSibling(locationElement)) != null);
		locations = (MSLocation[]) locationsList.toArray();
	}
	public void afterTextChanged(Editable s) {
		System.out.println("Text has changed");
	}
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		System.out.println("Text is about to change");
	}
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		//queryServer(s.toString());
		new MSSuggestions().execute(s.toString());
	}
	@Override
	protected MSLocation[] doInBackground(String... params) {
		queryServer(params[0]);
		return null;
	}
	@Override
	protected void onPostExecute(MSLocation[] locations) {
		for (MSLocation loc : locations) {
			loc.getHumanReadable();
		}
	}
}
