package com.airportstatus.interfaces;

import com.loopj.android.http.*;

public class GoogleClient {

	private static final String MAPS_URL = "https://maps.google.com/maps?";
	private static final String DIRECTIONS_URL = "http://maps.googleapis.com/maps/api/directions/json?";
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void getDirections(RequestParams params, AsyncHttpResponseHandler handler) {
		client.get(DIRECTIONS_URL, params, handler);
	}
	
	public static String getMapsUrl() {
		return MAPS_URL;
	}

}
