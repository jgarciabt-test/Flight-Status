package com.airportstatus.fragments;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.transform.ErrorListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.airportstatus.R;
import com.airportstatus.interfaces.FlightStatsClient;

public class AirlinesFragment extends ListFragment{

	private static final String AIRLINES_URL = "airlines/rest/v1/json/active";
	private ArrayList<String> airlines;
	private Location location;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//Same as the airport picker list
		
		View view = inflater.inflate(R.layout.airport_picker_fragment, container, false);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onStart()
	 */
	@Override
	public void onStart()
	{
		super.onStart();
		RequestQueue rq = Volley.newRequestQueue(getActivity());
		
		//Create the URL using the keys in FlightStatsClient
		String url = FlightStatsClient.BASE_URL + AIRLINES_URL + "?appId=" + FlightStatsClient.appId + "&appKey=" + FlightStatsClient.appKey + ""  ;

		//Display the loading dialog before loading
		final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Loading", "Loading the airlines");
		
		//Execute the request
		rq.add(new StringRequest(url, new Listener<String>()
		{

			@Override
			public void onResponse(String response)
			{
				airlines = new ArrayList<String>();
				try
				{
					
					//Parse the data (airlines name) and put them in the ArrayList
					JSONArray jarr = new JSONObject(response).getJSONArray("airlines");
					JSONObject jobj;
					for (int i = 0; i < jarr.length(); i++)
					{
						jobj = (JSONObject) jarr.get(i);
						airlines.add(jobj.getString("name"));
					}
					
					
					//Sort by alphabetic order
					Collections.sort(airlines);
				} catch (JSONException e)
				{
					Log.e("AirlinesFragment","error on parsing the JSON");
					e.printStackTrace();
				}
				
				//Loading done: hide the loading dialog
				dialog.dismiss();
				
				
				//Set the list view
				getListView().setAdapter(new ArrayAdapter<String>(getActivity(), com.airportstatus.R.layout.single_row, airlines));
			}
		}, new ErrorListener()
		{

			@Override
			public void onErrorResponse(VolleyError error)
			{
				
			}
		}));
		
	}

	/**
	 * @return the location
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

}
