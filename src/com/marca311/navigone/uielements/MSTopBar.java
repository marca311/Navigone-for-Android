package com.marca311.navigone.uielements;

import com.marca311.navigone.listeners.MSSuggestionBoxListener;
import com.marca311.navigone.uielements.MSSuggestionBox;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

public class MSTopBar extends ViewGroup {

	private TextView topLabel;
	private MSSuggestionBox suggestionBox;
	private MSSuggestionBoxListener suggestionBoxListener;

	public MSTopBar(Context context) {
		super(context);
	}

	public MSTopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		topLabel = new TextView(context);
		topLabel.setText("Origin");
		// topLabel.setBackgroundColor(Color.BLACK);
		this.addView(topLabel);

		suggestionBox = new MSSuggestionBox(context);
		
		// The 1 signifies the origin field
		suggestionBoxListener = new MSSuggestionBoxListener(suggestionBox, 1);
		suggestionBox.setOnItemClickListener(suggestionBoxListener);
		suggestionBox.setOnFocusChangeListener(suggestionBoxListener);
		suggestionBox.addTextChangedListener(suggestionBoxListener);

		// suggestionBox.setBackgroundColor(Color.BLUE);
		this.addView(suggestionBox);
	}

	public MSTopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int width = r - l;
		int height = b - t;
		// Custom spacing in the elements inside the bar.
		topLabel.layout(10, 10, r - l - 10, height / 3);
		suggestionBox.layout(10, height / 3, r - l - 10, b - t - 10);
	}

	public int convertToDIP(float pixels) {
		// Get the screen's density scale
		float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		int dip = (int) (pixels * scale + 0.5f);
		return dip;
	}

}