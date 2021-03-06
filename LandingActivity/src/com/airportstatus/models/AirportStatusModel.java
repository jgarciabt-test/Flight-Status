package com.airportstatus.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.airportstatus.entities.Aircraft;
import com.airportstatus.entities.Airline;
import com.airportstatus.entities.AirportData;
import com.airportstatus.entities.FlightStatus;

public class AirportStatusModel extends BaseModel {
	
	public String getName() {
		return getString("name");
	}
	
	public String getAvgDelay() {
    	return getStatusValue("avgDelay");
    }

	public String getWeather() {
    	return getWeatherValue();
    }
	
	public String getVisibility() {
		return getVisibilityValue();
	}

    public String getIATA() {
        return getString("IATA");
    }

	public boolean getDelay() {
        return getBoolean("delay");
    }

    public String getDelayType() {
    	return getStatusValue("type");
    }
    
    public String getClosureBegin() {
    	return getStatusValue("closureBegin");
    }
    
    public String getClosureEnd() {
    	return getStatusValue("closureEnd");
    }
    
    public String getEndTime() {
    	return getStatusValue("endTime");
    }
    
    public String getMaxDelay() {
    	return getStatusValue("maxDelay");
    }
    
    public String getMinDelay() {
    	return getStatusValue("minDelay");
    }

    public static AirportStatusModel fromJson(JSONObject json) {
        AirportStatusModel airport = new AirportStatusModel();

        try {
            airport.jsonObject = json;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airport;
    }
    
    private JSONObject getWeatherObject() {
    	return getJSON("weather");
    }
    
    private String getWeatherValue() {
    	try {
    		return getWeatherObject().getString("weather");
    	} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
    }
    
    private String getVisibilityValue() {
    	try {
    		return getWeatherObject().getString("visibility");
    	} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
    }
    
    public ArrayList<FlightStatus> getFlights(JSONObject json)
	{
		ArrayList<FlightStatus> flights = new ArrayList<FlightStatus>();
		try {
			JSONObject appendix = json.getJSONObject("appendix");
			JSONArray airlines = appendix.getJSONArray("airlines");
			JSONArray airports = appendix.getJSONArray("airports");
			JSONArray aircrafts = appendix.getJSONArray("equipments");
			JSONArray flightStatuses = json.getJSONArray("flightStatuses");
			//Airlines
			FlightStatus.getAirlines().clear();
			for (int counter = 0; counter < airlines.length();counter++)
			{
				FlightStatus.getAirlines().add(new Airline(airlines.getJSONObject(counter)));
			}
			
			//Airports
			FlightStatus.getAirports().clear();
			for (int counter = 0; counter < airports.length(); counter++)
				FlightStatus.getAirports().add(new AirportData(airports.getJSONObject(counter)));
			
			//Aircrafts
			FlightStatus.getAircrafts().clear();
			for (int counter = 0; counter < aircrafts.length(); counter++)
				FlightStatus.getAircrafts().add(new Aircraft(aircrafts.getJSONObject(counter)));
			
			//Flight statuses
			for (int counter = 0; counter < flightStatuses.length(); counter++)
				flights.add(new FlightStatus(flightStatuses.getJSONObject(counter)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flights;
	}

    private String getStatusValue(String name) {
    	try {
    		return getJSON("status").getString(name);
    	} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
    }
}