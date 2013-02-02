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
import com.marca311.navigone.uielements.DateSetPicker;
import com.marca311.navigone.uielements.TimeSetPicker;


public class NaviGone extends FragmentActivity implements DateSetPicker.onDateSetListener, TimeSetPicker.onTimeSetListener {
	//View variables
	EditText originField = null;
	EditText destinationField = null;
	MSLocation origin = null;
	MSLocation destination = null;
	Button timeField = null;
	Button dateField = null;
	Button submitButton = null;
	//Storage variables
	public MSCalendar queryCalendar = null;
	OnDateChangedListener dateListener;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_gone);
        
        //Set fields to their variables
        originField = (EditText)findViewById(R.id.originField);
        destinationField = (EditText)findViewById(R.id.destinationField);
        timeField = (Button)findViewById(R.id.timeField);
        dateField = (Button)findViewById(R.id.dateField);
        submitButton = (Button)findViewById(R.id.submitButton);
        
        //Show current time on Calendar fields
        queryCalendar = new MSCalendar();
        timeField.setText(queryCalendar.getTimeString());
        dateField.setText(queryCalendar.getDateString());
    }
    public void onStart() {
    	super.onStart();
        //FileModifier.checkDataFolder();
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
    
    public void submitFields(View v) {
    	//Add origin and destination getters
    	String calendarString = queryCalendar.getServerQueryable();
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
    
}
