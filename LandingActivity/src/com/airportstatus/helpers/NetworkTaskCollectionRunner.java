package com.airportstatus.helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.airportstatus.R;
import com.airportstatus.entities.FlightStatus;
import com.airportstatus.interfaces.FlightStatsClient;
import com.airportstatus.models.AirportStatusModel;
import com.airportstatus.models.TravelTimeEstimate;
import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class NetworkTaskCollectionRunner implements Observer {
	
	NetworkTaskCollection myTasks;
	private Context context;
	private Location location; 
	String airportCode;
	private ArrayList<FlightStatus> arrivals = new ArrayList<FlightStatus>();
	private ArrayList<FlightStatus> departures = new ArrayList<FlightStatus>();
	
	public NetworkTaskCollectionRunner(Context c) {
		 this.setContext(c);
		 myTasks = new NetworkTaskCollection();
	}
	
	public void setData(String airportCode, Location location) {
		this.airportCode = airportCode;
		this.setLocation(location);
	}
	
	public void run() {
		myTasks.addObserver(this);
		myTasks.addTask(new NetworkTask() {
			@Override
			public void setHandler() {
				this.handler = new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						int totalTripMins;
						try {
							totalTripMins = TravelTimeEstimate.parseDirections(response);
							myTasks.finishWithResult(StatusKeys.TRAVEL_TIME_DRIVING, TravelTimeEstimate.getFormattedDuration(totalTripMins));
						} catch (Exception e) {
							myTasks.finishOneTask();
							Log.e("TASK", "Task failed 1");							
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(Throwable error, JSONObject obj) {
						myTasks.finishOneTask();
					}
				};
			}

			@Override
			public void execute() {
				TravelTimeEstimate.getDrivingTime(TravelTimeEstimate.getCoordinates(getLocation()), airportCode, this.handler);
			}
		});
		
		myTasks.addTask(new NetworkTask() {
			@Override
			public void setHandler() {
				this.handler = new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						int totalTripMins;
						try {
							totalTripMins = TravelTimeEstimate.parseDirections(response);
							myTasks.finishWithResult(StatusKeys.TRAVEL_TIME_TRANSIT, TravelTimeEstimate.getFormattedDuration(totalTripMins));
						} catch (Exception e) {
							myTasks.finishOneTask();
							Log.e("TASK", "Task failed 2 ");							
							e.printStackTrace();
						}
					}
					
					@Override
					public void onFailure(Throwable error, JSONObject obj) {
						myTasks.finishOneTask();
					}
				};
			}
			
			@Override
			public void execute() {
				TravelTimeEstimate.getTransitTime(TravelTimeEstimate.getCoordinates(getLocation()), airportCode, this.handler);
			}
		});
		
		myTasks.addTask(new NetworkTask() {
			@Override
			public void setHandler() {
				this.handler = new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						String currentWeatherAtAirport;
						try {
							currentWeatherAtAirport = FlightStatsClient.getWeatherString(response);
						} catch (Exception e) {
							currentWeatherAtAirport = "";
						}
						try {
							Double tempC = FlightStatsClient.getTempCelsius(response); // Result only comes back in degrees Celsius
							Double tempF = (tempC * 1.8) + 32;
							myTasks.addResult(StatusKeys.TEMPERATURE, String.valueOf(tempF));
						} catch (Exception e) {
							Log.e("WEATHER", e.getMessage() + " " + response.toString());
						}
						myTasks.finishWithResult(StatusKeys.WEATHER, currentWeatherAtAirport);
					}
					
					@Override
					public void onFailure(Throwable error, JSONObject obj) {
						Log.e("WEATHER", error.getMessage());
						myTasks.finishOneTask();
					}
				};
			}
			
			@Override
			public void execute() {
				FlightStatsClient.getWeatherConditions(airportCode, this.handler);
			}
		});
		
		myTasks.addTask(new NetworkTask() {
			@Override
			public void setHandler() {
				this.handler = new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						String[] outcomes = getContext().getResources().getStringArray(R.array.txtDelayLabels);
						try {
							int delaySeverityIndex = FlightStatsClient.getDelayIndex(response);
							Log.d("DELAY SEVERITY", outcomes[delaySeverityIndex]);
							myTasks.finishWithResult(StatusKeys.DELAYS, outcomes[delaySeverityIndex]);
						} catch (Exception e) {
							myTasks.finishWithResult(StatusKeys.DELAYS, getContext().getResources().getString(R.string.txtDelaysError));
						}
					}
					
					@Override
					public void onFailure(Throwable error, JSONObject obj) {
						Log.e("DELAYS", error.getMessage());
						myTasks.finishOneTask();
					}
				};
			}
			
			@Override
			public void execute() {
				FlightStatsClient.getDelayDegree(airportCode, this.handler);
			}
		});
		
		//Getting flights information
		myTasks.addTask(new NetworkTask(){

			@Override
			public void setHandler() {
				this.handler = new JsonHttpResponseHandler(){

					/* (non-Javadoc)
					 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(org.json.JSONObject)
					 */
					@Override
					public void onSuccess(JSONObject arg0) {
						departures = new AirportStatusModel().getFlights(arg0);
						myTasks.finishWithResult("departures", "got");
					}

					/* (non-Javadoc)
					 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(java.lang.Throwable, org.json.JSONObject)
					 */
					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						Log.d("Departures", arg0.getMessage());
						myTasks.finishOneTask();
					}
					
				};
			}

			@Override
			public void execute() {
				Log.d("TASK", "Checking departures");
				FlightStatsClient.getFlightsInformation(airportCode, Calendar.getInstance(), false, this.handler);
			}
			
		});
		
		myTasks.addTask(new NetworkTask(){

			@Override
			public void setHandler() {
				this.handler = new JsonHttpResponseHandler(){

					/* (non-Javadoc)
					 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(org.json.JSONObject)
					 */
					@Override
					public void onSuccess(JSONObject arg0) {
						arrivals = new AirportStatusModel().getFlights(arg0);
						myTasks.finishWithResult("arrivals", "got");
					}

					/* (non-Javadoc)
					 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(java.lang.Throwable, org.json.JSONObject)
					 */
					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						Log.d("Arrivals", arg0.getMessage());
						arrivals = new ArrayList<FlightStatus>();
						myTasks.finishOneTask();
					}
					
				};
			}

			@Override
			public void execute() {
				Log.d("TASK", "Checking arrivals");
				FlightStatsClient.getFlightsInformation(airportCode, Calendar.getInstance(), true, this.handler);
			}
			
		});
		
		myTasks.startAll();
		Log.d("NetworkTaskCollectionRunner", "got this far");
		
	} 

	protected abstract void handleResult(Bundle response);
	
	@Override
	public void update(Observable observable, Object response) {
		this.handleResult((Bundle) response);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the arrivals
	 */
	public ArrayList<FlightStatus> getArrivals() {
		return arrivals;
	}

	/**
	 * @param arrivals the arrivals to set
	 */
	public void setArrivals(ArrayList<FlightStatus> arrivals) {
		this.arrivals = arrivals;
	}

	/**
	 * @return the departures
	 */
	public ArrayList<FlightStatus> getDepartures() {
		return departures;
	}

	/**
	 * @param departures the departures to set
	 */
	public void setDepartures(ArrayList<FlightStatus> departures) {
		this.departures = departures;
	} 
}
