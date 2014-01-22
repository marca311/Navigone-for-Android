package com.marca311.navigone.uielements;

import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.customclasses.MSSuggestions;
import com.marca311.navigone.customclasses.MSSuggestions.MSSuggestionsUpdater;

import android.content.Context;
import android.view.View;
import android.widget.AutoCompleteTextView;

public class MSSuggestionBox extends AutoCompleteTextView implements MSSuggestionsUpdater {

	public MSSuggestionBox(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void updateSuggestionsBox(MSLocation[] suggestions) {
		// TODO Auto-generated method stub
		
	}
	
}
