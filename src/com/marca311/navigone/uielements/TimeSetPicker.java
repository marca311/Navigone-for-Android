package com.marca311.navigone.uielements;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.marca311.navigone.MSUtilities;
import com.marca311.navigone.R;
import com.marca311.navigone.uielements.DateSetPicker.onDateSetListener;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.Activity;
import android.content.res.Resources;

public class TimeSetPicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	
	onTimeSetListener timeListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = timeListener.getCalendar();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
	
		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute, false);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		timeListener = (onTimeSetListener) activity;
	}
	
	public interface onTimeSetListener {
		public GregorianCalendar getCalendar();
		public void onTimeSet(int hour, int minute);
	}
	
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		timeListener.onTimeSet(hourOfDay, minute);
	}

}
