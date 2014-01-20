package com.marca311.navigone.uielements;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import com.marca311.navigone.MSUtilities;
import com.marca311.navigone.NaviGone;
import com.marca311.navigone.R;
import com.marca311.navigone.customclasses.MSCalendar;

public class DateSetPicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	
	onDateSetListener dateListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = dateListener.getCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dateListener = (onDateSetListener) activity;
	}
	
	public interface onDateSetListener {
		public GregorianCalendar getCalendar();
		public void onDateSet(int year, int month, int day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		dateListener.onDateSet(year,month,day);
	}
}
