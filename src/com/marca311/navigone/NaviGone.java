package com.marca311.navigone;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.System;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.File;

import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.CloudmadeUtil;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.*;
import org.osmdroid.views.overlay.TilesOverlay;
import org.w3c.dom.*;

import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.customclasses.MSCalendar;
import com.marca311.navigone.customclasses.MSQuery;
import com.marca311.navigone.customclasses.MSSuggestions;
import com.marca311.navigone.listeners.MSSuggestionBoxListener;
import com.marca311.navigone.uielements.DateSetPicker;
import com.marca311.navigone.uielements.TimeSetPicker;

public class NaviGone extends FragmentActivity implements
		DateSetPicker.onDateSetListener, TimeSetPicker.onTimeSetListener {
	// View variables
	MapView mainMap = null;
	TilesOverlay tilesOverlay = null;

	AutoCompleteTextView originField = null;
	AutoCompleteTextView destinationField = null;
	MSLocation origin = null;
	MSLocation destination = null;
	Button timeField = null;
	Button dateField = null;
	Button submitButton = null;
	MSSuggestions suggestions = null;
	RelativeLayout mainLayout = null;
	// Storage variables
	public MSCalendar queryCalendar = null;
	MSQuery theQuery = null;
	OnDateChangedListener dateListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navi_gone);

		// Setup base map
		/*
		 * final RelativeLayout rl = new RelativeLayout(this);
		 * 
		 * CloudmadeUtil.retrieveCloudmadeKey(getApplicationContext());
		 * 
		 * this.mainMap = new MapView(this, 256); rl.addView(this.mainMap, new
		 * RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
		 * LayoutParams.FILL_PARENT));
		 * this.mainMap.setBuiltInZoomControls(true);
		 * 
		 * 
		 * this.mainMap.getController().setZoom(7);
		 * this.mainMap.getController().setCenter(new GeoPoint(51500000,
		 * 5400000));
		 * 
		 * // Add tiles layer MapTileProviderBasic mProvider = new
		 * MapTileProviderBasic(getApplicationContext()); final ITileSource
		 * tileSource = new XYTileSource( this.tilesOverlay = new
		 * TilesOverlay(mProvider, this.getBaseContext());
		 * this.mainMap.getOverlays().add(this.tilesOverlay);
		 * 
		 * this.setContentView(rl);
		 */

		/*
		 * timeField = (Button)findViewById(R.id.timeField); dateField =
		 * (Button)findViewById(R.id.dateField); submitButton =
		 * (Button)findViewById(R.id.submitButton); mainLayout =
		 * (RelativeLayout)findViewById(R.id.mainLayout);
		 * initiateLocationFields();
		 * 
		 * //Show current time on Calendar fields queryCalendar = new
		 * MSCalendar(); timeField.setText(queryCalendar.getTimeString());
		 * dateField.setText(queryCalendar.getDateString());
		 */
	}

	@Override
	public void onStart() {
		super.onStart();
		String xmlLocation = Environment.getExternalStorageDirectory()
				.getPath() + "/trip-planner.xml";
		File theFile = new File(xmlLocation);
		if (theFile.exists()) {
			Document theDocument = XMLParser.getAndParseXML(theFile);
			MSRoute currentRoute = new MSRoute(theDocument);
			System.out.println("Route processed");
		}
		// Temporary way to set the background color to black to determine where
		// views are
		this.getWindow().getDecorView().setBackgroundColor(Color.BLACK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_navi_gone, menu);
		return true;
	}

	public void showTimePickerDialog(View v) {
		DialogFragment timeFragment = new TimeSetPicker();
		timeFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public void showDatePickerDialog(View v) {
		DialogFragment dateFragment = new DateSetPicker();
		dateFragment.show(getSupportFragmentManager(), "datePicker");
	}

	public GregorianCalendar getCalendar() {
		return queryCalendar.getCalendar();
	}

	public void onDateSet(int year, int month, int day) {
		queryCalendar.setDate(day, month, year);
		dateField.setText(queryCalendar.getDateString());
	}

	public void onTimeSet(int hour, int minute) {
		queryCalendar.setTime(hour, minute);
		timeField.setText(queryCalendar.getTimeString());
	}

	// Submit button
	public void submitFields(View v) {
		// Note: getText().toString() gets the text from the field
		theQuery.setFromString(originField.getText().toString());
		theQuery.setToString(originField.getText().toString());
		theQuery.setCalendar(queryCalendar);
	}

	private void initiateLocationFields() {
		// Set fields to their variables
		/*
		 * originField = (AutoCompleteTextView)findViewById(R.id.originField);
		 * MSSuggestionBoxListener originListener = new
		 * MSSuggestionBoxListener(1);
		 * originField.addTextChangedListener(originListener);
		 * originField.setOnFocusChangeListener(originListener);
		 * 
		 * destinationField =
		 * (AutoCompleteTextView)findViewById(R.id.destinationField);
		 * MSSuggestionBoxListener destinationListener = new
		 * MSSuggestionBoxListener(2);
		 * destinationField.addTextChangedListener(destinationListener);
		 * destinationField.setOnFocusChangeListener(destinationListener);
		 */
	}
}
