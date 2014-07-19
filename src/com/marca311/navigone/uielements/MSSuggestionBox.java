package com.marca311.navigone.uielements;

import com.marca311.navigone.AddressClasses.MSLocation;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.support.v4.widget.*;

public class MSSuggestionBox extends EditText {
	
	ArrayAdapter<String> locationArray;
	public ListPopupWindow popupWindow;

	public MSSuggestionBox(Context context) {
		super(context); 
		
		popupWindow = new ListPopupWindow(getContext());
		popupWindow.setAnchorView(this);
		String[] initialArray = { "Search History" };

		locationArray = new ArrayAdapter<String>(getContext(),
				android.R.layout.simple_dropdown_item_1line, initialArray);
		popupWindow.setAdapter(locationArray);

		setSingleLine();
	}

	public MSSuggestionBox(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MSSuggestionBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * This is called when anything that signals the field closing is done.
	 * (done button, center dpad button...)
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		clearFocus();
		return super.onKeyUp(keyCode, event);
	}

	/**
	 * Updates the suggestion box with fresh suggestions. Is often
	 * multithreaded.
	 * 
	 * @param suggestions
	 *            The suggestions in mslocation format
	 */
	public void updateSuggestions(final MSLocation[] suggestions) {
		Activity activity = (Activity)getContext();
		activity.runOnUiThread(new Runnable() {
			public void run() {
				locationArray.clear();
				for (int i = 0; i < suggestions.length; i++) {
					locationArray.add(suggestions[i].toString());
				}
				locationArray.add("Search History");
			}
		});
	}
	
	/**
	 * Populates the suggestion box with an array of strings
	 * @param items The strings to be added
	 */
	public void updateSuggestionsBox(String[] items) {
		locationArray.clear();
		for (int i = 0; i < items.length; i++) {
			locationArray.add(items[i]);
		}
		locationArray.add("Search History");
	}
	
	/**
	 * Sets a listener to receive events when a list item is clicked.
	 * @param clickListener Listener to register
	 */
	public void setOnItemClickListener(OnItemClickListener clickListener) {
		popupWindow.setOnItemClickListener(clickListener);
	}
}