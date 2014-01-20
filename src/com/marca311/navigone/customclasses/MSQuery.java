package com.marca311.navigone.customclasses;

import java.util.Locale;

import org.w3c.dom.Document;

import com.marca311.navigone.Apikey;
import com.marca311.navigone.MSRoute;
import com.marca311.navigone.XMLParser;
import com.marca311.navigone.AddressClasses.*;

public class MSQuery {
	private String queryName;
	private String fromString;
	private MSLocation fromLocation;
	private String toString;
	private MSLocation toLocation;
	private MSCalendar calendar;
	private String modeString;
	private Boolean easyAccess;
	private String walkSpeed;
	private String maxWalkTime;
	private String minTransferWaitTime;
	private String maxTransferWaitTime;
	private String maxTransfers;
	
	//Class setters
	public void setQueryName(String input) {
		queryName = input;
	}
	public void setFromString(String input) {
		fromString = input;
	}
	public void setFromLocation(MSLocation input) {
		fromLocation = input;
	}
	public void setToString(String input) {
		toString = input;
	}
	public void setToLocation(MSLocation input) {
		toLocation = input;
	}
	public void setCalendar(MSCalendar input) {
		calendar = input;
	}
	public void setMode(String input) {
		modeString = input;
	}
	public void setEasyAccess(Boolean input) {
		easyAccess = input;
	}
	public void setWalkSpeed(String input) {
		walkSpeed = input;
	}
	public void setMaxWalkTime(String input) {
		maxWalkTime = input;
	}
	public void setMinTransferWaitTime(String input) {
		minTransferWaitTime = input;
	}
	public void setMaxTransferWaitTime(String input) {
		maxTransferWaitTime = input;
	}
	public void setMaxTransfers(String input) {
		maxTransfers = input;
	}
	
	//Reformats the mode string to plug it into the server
	private String setServerQueryableMode(String input) {
		String result = input.toLowerCase(Locale.CANADA);
		result = result.replaceAll(" ", "-");
		return result;
	}
	
	//Gets the route from all the query data collected
	public MSRoute getRoute() {
		MSRoute result;
		String origin = fromLocation.getServerQueryable();
		String destination = toLocation.getServerQueryable();
		String time = calendar.getServerQueryableTime();
		String date = calendar.getServerQueryableDate();
		String mode = setServerQueryableMode(modeString);
		String easy_access = easyAccess.toString();
		String walk_speed = walkSpeed;
		String max_walk_time = maxWalkTime;
		String min_transfer_wait = minTransferWaitTime;
		String max_transfer_wait = maxTransferWaitTime;
		String max_transfers = maxTransfers;
		String api_key = Apikey.getApiKey();
		String url = generateURL(origin, destination, time, date, mode, easy_access, walk_speed, max_walk_time, min_transfer_wait, max_transfer_wait, max_transfers, api_key);
		Document theDocument = XMLParser.getAndParseXML(url);
		result = new MSRoute(theDocument);
		return result;
	}
	
	
	public String generateURL(String origin, String destination, String time, String date, String mode, String easy_access, String walk_speed, String max_walk_time, String min_transfer_wait, String max_transfer_wait,String max_transfers, String api_key) {
		String url = "http://api.winnipegtransit.com/trip-planner?origin="+origin
				+"&destination="+destination
				+"&time="+time
				+"&date="+date
				+"&mode="+mode
				+"&easy-access="+easy_access
				+"&walk-speed="+walk_speed
				+"&max-walk-time="+max_walk_time
				+"&min-transfer-wait="+min_transfer_wait
				+"&max-transfer-wait="+max_transfer_wait
				+"&max-transfers="+max_transfers
				+"&api-key="+api_key;
		return url;
	}
}
