/**
 * 
 */
package com.airportstatus.ui.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.airportstatus.R;
import com.airportstatus.entities.AirportData;
import com.airportstatus.helpers.LocationPreferences;
import com.airportstatus.helpers.LocationResult;
import com.airportstatus.helpers.NetworkTask;
import com.airportstatus.interfaces.FlightStatsClient;
import com.airportstatus.ui.fragments.AirportFragment;
import com.airportstatus.ui.fragments.AirportPickerListFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author pablo
 *
 */
public class FlightInformationActivity extends Activity {
	private Location location;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flight_information);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.flight_information_menu, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.itm_city_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setQueryHint(getResources().getString(R.string.search));
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.itm_location_search:
			if (isOnline())
			{
				LocationResult locResult = new LocationResult() {
	
					@Override
					public void receivedLocation(Location location) {
						if (location == null)
						{
							FlightInformationActivity.this.location = new Location("reversegeopoint");
							FlightInformationActivity.this.location.setLatitude(51);
							FlightInformationActivity.this.location.setLongitude(0);
						}
						else
							FlightInformationActivity.this.location = location;
						// TODO Auto-generated method stub
						NetworkTask nt = new NetworkTask(){
	
							@Override
							public void setHandler() {
								this.handler = new JsonHttpResponseHandler() {
	
									/* (non-Javadoc)
									 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(java.lang.Throwable, org.json.JSONObject)
									 */
									@Override
									public void onFailure(Throwable arg0,
											JSONObject arg1) {
										// TODO Auto-generated method stub
										super.onFailure(arg0, arg1);
									}
	
									/* (non-Javadoc)
									 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(int, org.json.JSONObject)
									 */
									@Override
									public void onSuccess(int arg0, JSONObject arg1) {
										try {
											JSONArray jsonAirports = arg1.getJSONArray("airports");
											ArrayList<AirportData> airports = new ArrayList<AirportData>();
											for (int counter = 0; counter < jsonAirports.length();counter++)
												airports.add(new AirportData(jsonAirports.getJSONObject(counter)));
											if (airports.isEmpty())
												Toast.makeText(FlightInformationActivity.this, getResources().getString(R.string.airports_not_found), Toast.LENGTH_LONG).show();
											else if (airports.size() == 1)
											{
												AirportFragment airportFragment = new AirportFragment();
												airportFragment.setCurrentAirport(airports.get(0));
												airportFragment.setLocation(FlightInformationActivity.this.location);
												getFragmentManager().beginTransaction().replace(R.id.flight_information_layout, airportFragment).commit();
											}
											else
											{
												AirportPickerListFragment picker = new AirportPickerListFragment();
												picker.setAirports(airports);
												picker.setLocation(FlightInformationActivity.this.location);
												getFragmentManager().beginTransaction().replace(R.id.flight_information_layout, picker).commit();
												
											}
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								};
							}
	
							@Override
							public void execute() {
								FlightStatsClient.findNearbyAirports(FlightInformationActivity.this.location, 150, this.handler);
								
							}
							
						};
						nt.setHandler();
						nt.execute();
					}
					
				};
				LocationPreferences lp = new LocationPreferences();
				boolean received = lp.getCurrentLocation(this, locResult);
				if (!received)
					Toast.makeText(this, getResources().getString(R.string.no_location), Toast.LENGTH_LONG).show();
			}
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	protected boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}
	
}
