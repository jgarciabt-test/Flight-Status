package com.airportstatus.fragments;

import java.util.ArrayList;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.airportstatus.R;
import com.airportstatus.adapters.AirlineAdapter;
import com.airportstatus.entities.Airline;
import com.airportstatus.models.Airlines;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AirlinesFragment extends ListFragment
{

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
		// Same as the airport picker list

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
		// Display the loading dialog before loading
		final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Loading", "Loading the airlines");
		
		Handler h = new Handler(new Handler.Callback()
		{
			
			@Override
			public boolean handleMessage(Message msg)
			{
				ArrayList<Airline> al = (ArrayList<Airline>) msg.obj;
				
				dialog.dismiss();
				
				Crouton.showText(getActivity(), "Loading done", Style.INFO);
				
				// Set the list view
				AirlineAdapter adapter = new AirlineAdapter(getActivity(), R.layout.airlines_row, al);
				getListView().setAdapter(adapter);
				
				return true;
			}
		});
		
		//Get the airlines
		Airlines.getAirlines(getActivity(), h);
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
