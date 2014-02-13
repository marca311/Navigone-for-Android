package com.marca311.navigone.customclasses;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.view.*;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;

import com.marca311.navigone.MSSegment;
import com.marca311.navigone.R;
import com.marca311.navigone.XMLParser;
import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.Apikey;

public class MSSuggestions extends AsyncTask<String, Integer, MSLocation[]> implements TextWatcher, OnFocusChangeListener {
		
	public interface MSSuggestionsUpdater {
		public void updateSuggestionsBox(MSLocation[] suggestions);
	}
	
	public void registerUpdater(MSSuggestionsUpdater updater) {
		suggestionsUpdater = updater;
	}
	
	//Variables
	private MSSuggestionsUpdater suggestionsUpdater;
	private String query;
	private MSLocation[] locations;
	private Document queryDocument;
	private ListView suggestionList;
	
	private MSLocation[] queryServer(String input) {
		System.out.println("SERVER QUERIED");
		query = input;
		query = query.replace(" ", "+");
		String url = "http://api.winnipegtransit.com/locations:"+query
		+"?api-key="+Apikey.getApiKey();
		System.out.println("URL: "+url);
		queryDocument = XMLParser.getAndParseXML(url);
		return setLocationArray();
	}
	private MSLocation[] setLocationArray() {
		Element rootElement = queryDocument.getDocumentElement();
		Element locationElement = queryDocument.getDocumentElement();
		ArrayList<MSLocation> locationsList = new ArrayList<MSLocation>();
		NodeList locationNodeList = rootElement.getChildNodes();
		for (int i=0; i < locationNodeList.getLength(); i++) {
			Node currentNode = locationNodeList.item(i);
			//Check if node is of type "Element" (1).
			if (currentNode.getNodeType() == 1) {
				Element currentElement = (Element) currentNode;
				MSLocation newLocation = MSSegment.setLocationClass(currentElement);
				locationsList.add(newLocation);
			}
		}
		locations = new MSLocation[locationsList.size()];
		for (int i = 0; i < locationsList.size(); i++) {
			locations[i] = locationsList.get(i);
		}
		return locations;
		/*
		do {
			MSLocation test = MSSegment.setLocationClass(locationElement);
			locationsList.add(test);
			if (test == null) {
				System.out.println("IS NULL");
			} else {
				 System.out.println(test.getHumanReadable());
			}
		} while ((locationElement = XMLParser.getElementSibling(locationElement)) != null);
		for (int i = 0; i < locationsList.size(); i++) {
			locations[i] = locationsList.get(i);
		} */
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
		//new MSSuggestions().execute(s.toString());
	}
	@Override
	protected MSLocation[] doInBackground(String... params) {
		return queryServer(params[0]);
	}
	@Override
	protected void onPostExecute(MSLocation[] locations) {
		for (MSLocation loc : locations) {
			loc.toString();
			//TODO: get rid of this line when not necessary.
			System.out.println(loc.toString());
		}
	}
	public void onFocusChange(View v, boolean hasFocus) {
		/*
		if (hasFocus) {
			EditText textBox = (EditText)parentActivity.findViewById(v.getId());
			LayoutParams boxParams = textBox.getLayoutParams();
			ArrayAdapter<String> array = new ArrayAdapter<String>(parentActivity, android.R.layout.simple_list_item_1 , new String[]{"a","b","c","d","e"});
			
			suggestionList.setAdapter(array);
			suggestionList.setLayoutParams(boxParams);
		} */
	}
}
