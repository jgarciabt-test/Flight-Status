/**
 * 
 */
package com.airportstatus.ui.fragments;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.entities.AirportData;
import com.airportstatus.entities.FlightStatus;
import com.airportstatus.helpers.LocationPreferences;
import com.airportstatus.helpers.NetworkTaskCollectionRunner;
import com.airportstatus.ui.adapters.FlightAdapter;

/**
 * @author pablo
 *
 */
public class AirportFragment extends Fragment {
	private ArrayList<FlightStatus> departures, arrivals;
	private LoadingDialogFragment ldf = new LoadingDialogFragment();
	private AirportData currentAirport;
	private Location location;
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_airport, container, false);
		((Button)view.findViewById(R.id.btn_arrivals)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				FlightAdapter adapter = new FlightAdapter(getActivity(), arrivals, true);
				
				((StickyListHeadersListView)getView().findViewById(R.id.lv_flights)).setAdapter(adapter);
			
			}
		});
		((Button)view.findViewById(R.id.btn_departures)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				((StickyListHeadersListView)getView().findViewById(R.id.lv_flights)).setAdapter(new FlightAdapter(getActivity(), departures, false));
				
			}
		});
		return view;
	}
	
	protected void setupNetworkQueries() {
		NetworkTaskCollectionRunner n = new NetworkTaskCollectionRunner(getActivity()) {
			@Override
			public void handleResult(Bundle bundle) {
				// Test here that the value returned from the observed NetworkTaskCollection 
				// is the one that signals success
				if (bundle.containsKey("success")) {
					ldf.dismiss();
					departures = this.getDepartures();
					//The current airport is the departure airport from any departing flight in the list 
					((TextView)getView().findViewById(R.id.tv_airport_code)).setText(currentAirport.getIataCode());
					((TextView)getView().findViewById(R.id.tv_airport_name)).setText(currentAirport.getName());
					((TextView)getView().findViewById(R.id.tv_airport_city)).setText(currentAirport.getCity());
					((TextView)getView().findViewById(R.id.tv_airport_status)).setText(bundle.getString("delays"));
					((TextView)getView().findViewById(R.id.tv_airport_weather)).setText(bundle.getString("weather"));
					((TextView)getView().findViewById(R.id.tv_airport_temp)).setText(bundle.getString("temp"));
					Collections.sort(departures, new Comparator<FlightStatus>(){

						@Override
						public int compare(FlightStatus arg0, FlightStatus arg1) {
							// TODO Auto-generated method stub
							return arg0.getLocalDepartureDate().compareTo(arg1.getLocalDepartureDate());
						}
						
					});
					arrivals = this.getArrivals();
					Collections.sort(arrivals, new Comparator<FlightStatus>(){

						@Override
						public int compare(FlightStatus arg0, FlightStatus arg1) {
							// TODO Auto-generated method stub
							return arg0.getLocalArrivalDate().compareTo(arg1.getLocalArrivalDate());
						}
						
					});
					((StickyListHeadersListView)getView().findViewById(R.id.lv_flights)).setAdapter(new FlightAdapter(getActivity(), departures, false));
				} 
			}
		};
		ldf.show(getFragmentManager(), "loading");
		LocationPreferences.setLastLocationPreferences(getActivity(), getLocation().getLatitude(), getLocation().getLongitude());
		try {
			n.setData(getCurrentAirport().getFsCode(), LocationPreferences.getLastLocationPreferences(getActivity()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		n.run();
	}

	/**
	 * @return the currentAirport
	 */
	public AirportData getCurrentAirport() {
		return currentAirport;
	}

	/**
	 * @param currentAirport the currentAirport to set
	 */
	public void setCurrentAirport(AirportData currentAirport) {
		this.currentAirport = currentAirport;
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

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setupNetworkQueries();
		setHasOptionsMenu(true);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.airport_menu, menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
		case R.id.itm_refresh:
			setupNetworkQueries();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
