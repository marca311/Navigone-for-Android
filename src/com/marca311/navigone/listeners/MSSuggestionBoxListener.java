package com.marca311.navigone.listeners;

import com.marca311.navigone.customclasses.MSSuggestions;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;

public class MSSuggestionBoxListener implements TextWatcher, OnFocusChangeListener  {
	private int currentField; //1 - Origin, 2 - Destination
	private MSSuggestions suggestions = null;
	
	public MSSuggestionBoxListener(int currentField) {
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
	}

	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		
	}
	
}
