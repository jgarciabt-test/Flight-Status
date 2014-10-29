/**
 * 
 */
package com.airportstatus.ui.fragments;

import java.util.ArrayList;

import android.app.ListFragment;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.airportstatus.R;
import com.airportstatus.entities.AirportData;
import com.airportstatus.ui.adapters.AirportPickerAdapter;

/**
 * @author pablo
 *
 */
public class AirportPickerListFragment extends ListFragment {
	private ArrayList<AirportData> airports;
	private Location location;

	/**
	 * @return the airports
	 */
	public ArrayList<AirportData> getAirports() {
		return airports;
	}

	/**
	 * @param airports the airports to set
	 */
	public void setAirports(ArrayList<AirportData> airports) {
		this.airports = airports;
	}

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.airport_picker_fragment, container, false);
		return view;
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getListView().setAdapter(new AirportPickerAdapter(getActivity(), airports));
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				AirportData airport = (AirportData) arg0.getItemAtPosition(arg2);
				AirportFragment airportFragment = new AirportFragment();
				airportFragment.setLocation(getLocation());
				airportFragment.setCurrentAirport(airport);
				getFragmentManager().beginTransaction().replace(R.id.flight_information_layout, airportFragment).commit();
			}
		});
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
