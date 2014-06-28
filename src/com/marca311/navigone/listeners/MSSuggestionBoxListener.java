package com.marca311.navigone.listeners;

import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.customclasses.MSSuggestions;
import com.marca311.navigone.customclasses.MSSuggestions.MSSuggestionsUpdater;
import com.marca311.navigone.uielements.MSSuggestionBox;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;

public class MSSuggestionBoxListener implements TextWatcher,
		OnFocusChangeListener {
	private int currentField; // 1 - Origin, 2 - Destination
	private MSSuggestionBox suggestionBox;
	private MSSuggestions suggestions = null;
	private MSSuggestionsUpdater suggestionBoxUpdater;

	public MSSuggestionBoxListener(MSSuggestionBox suggestionBox,
			int currentField) {
		this.suggestionBox = suggestionBox;
		this.currentField = currentField;
	}

	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		suggestions.execute(s.toString());
		suggestions.registerUpdater(suggestionBoxUpdater);

	}

	public void updateSuggestionsBox(MSLocation[] suggestions) {
		String[] locationStrings = new String[suggestions.length];
		// Convert the location array to an array of strings
		for (int i = 0; i < suggestions.length; i++) {
			locationStrings[i] = suggestions[i].toString();
		}
		ArrayAdapter<String> locationArray = new ArrayAdapter<String>(
				suggestionBox.getContext(),
				android.R.layout.simple_list_item_1, locationStrings);
		suggestionBox.setAdapter(locationArray);
	}

	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {

		}
	}
}