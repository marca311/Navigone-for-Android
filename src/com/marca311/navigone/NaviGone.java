package com.marca311.navigone;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.System;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.File;
import org.w3c.dom.*;

import com.marca311.navigone.AddressClasses.MSLocation;
import com.marca311.navigone.customclasses.MSCalendar;
import com.marca311.navigone.customclasses.MSQuery;
import com.marca311.navigone.customclasses.MSSuggestions;
import com.marca311.navigone.uielements.DateSetPicker;
import com.marca311.navigone.uielements.TimeSetPicker;
import com.marca311.navigone.uielements.MSAutoCompleteField;


public class NaviGone extends FragmentActivity implements DateSetPicker.onDateSetListener, TimeSetPicker.onTimeSetListener {
	//View variables
	AutoCompleteTextView originField = null;
	AutoCompleteTextView destinationField = null;
	MSLocation origin = null;
	MSLocation destination = null;
	Button timeField = null;
	Button dateField = null;
	Button submitButton = null;
	MSSuggestions suggestions = null;
	//Storage variables
	public MSCalendar queryCalendar = null;
	MSQuery theQuery = null;
	OnDateChangedListener dateListener;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_gone);
        
        timeField = (Button)findViewById(R.id.timeField);
        dateField = (Button)findViewById(R.id.dateField);
        submitButton = (Button)findViewById(R.id.submitButton);
        
        //Show current time on Calendar fields
        queryCalendar = new MSCalendar();
        timeField.setText(queryCalendar.getTimeString());
        dateField.setText(queryCalendar.getDateString());
    }
	@Override
    public void onStart() {
    	super.onStart();
        String xmlLocation = Environment.getExternalStorageDirectory().getPath() + "/trip-planner.xml";
        File theFile = new File(xmlLocation);
        Document theDocument = XMLParser.getAndParseXML(theFile);
        MSRoute currentRoute = new MSRoute(theDocument);
        System.out.println("Route processed");
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
    
    //Submit button
    public void submitFields(View v) {
    	//Note: getText().toString() gets the text from the field
    	theQuery.setFromString(originField.getText().toString());
    	theQuery.setToString(originField.getText().toString());
    	theQuery.setCalendar(queryCalendar);
    }
    
    private void initiateLocationFields() {
    	suggestions = new MSSuggestions();
        
        //Set fields to their variables
        originField = (AutoCompleteTextView)findViewById(R.id.originField);
        originField.addTextChangedListener(suggestions);
        //Threshold is how many characters have to be entered before suggestions appear
        originField.setThreshold(1);
        
        //originField.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, );
        destinationField = (AutoCompleteTextView)findViewById(R.id.destinationField);
        destinationField.setThreshold(1);
    }
    
}
