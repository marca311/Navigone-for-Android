package com.marca311.navigone.uielements;

import com.marca311.navigone.uielements.MSSuggestionBox;

import android.content.Context;
import android.view.ViewGroup;

public class MSTopBar extends ViewGroup {
	
	private MSSuggestionBox suggestionsBox;

	public MSTopBar(Context context) {
		super(context);
		suggestionsBox = new MSSuggestionBox(getContext());
		
		this.addView(suggestionsBox);
	}
	public MSTopBar()

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		suggestionsBox.layout(5, 5, r-5, 20);
		
	}

}
