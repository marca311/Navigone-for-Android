package com.marca311.navigone.listeners;

import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.customclasses.MSSuggestions;
import com.marca311.navigone.customclasses.MSSuggestions.MSSuggestionsUpdater;
import com.marca311.navigone.uielements.MSSuggestionBox;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MSSuggestionBoxListener implements TextWatcher, MSSuggestionsUpdater, OnFocusChangeListener, OnItemClickListener {
	private int currentField; // 1 - Origin, 2 - Destination
	private MSSuggestionBox suggestionBox;
	private MSSuggestionsUpdater suggestionBoxUpdater;

	public MSSuggestionBoxListener(MSSuggestionBox suggestionBox, int currentField) {
		this.suggestionBox = suggestionBox;
		this.currentField = currentField;
	}

	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}

	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// Starts new thread lifecycle which terminates when results are
		// returned
		MSSuggestions suggestions = new MSSuggestions();
		//suggestions.execute(s.toString());
		//suggestions.registerUpdater(this);
		String muffin = s.toString();
		suggestionBox.updateSuggestionsBox(new String[] { muffin });
	}

	public void updateSuggestionsBox(MSLocation[] suggestions) {
		suggestionBox.updateSuggestions(suggestions);
		return;
		/*
		String[] locationStrings = new String[suggestions.length];
		// Convert the location array to an array of strings
		for (int i = 0; i < suggestions.length; i++) {
			locationStrings[i] = suggestions[i].toString();
		}
		ArrayAdapter<String> locationArray = new ArrayAdapter<String>(
				suggestionBox.getContext(),
				android.R.layout.simple_list_item_1, locationStrings);
		suggestionBox.setAdapter(locationArray); */
	}

	public void onFocusChange(View v, boolean hasFocus) {
		MSSuggestionBox box = (MSSuggestionBox)v;
		if (hasFocus) {
			//box.popupWindow.show();
		} else {
			//box.popupWindow.show();
		}
		if (!box.popupWindow.isShowing()) {
			box.popupWindow.show();
		}
		System.out.println(box.popupWindow.isShowing());
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		System.out.println("Item clicked at: "+position);
		
	}
}