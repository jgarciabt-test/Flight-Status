/**
 * 
 */
package com.airportstatus.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Pablo Sanchez Alonso
 * @version 1.0
 * This class is the entity class for an airport. It throws all available data
 * for a given airport. As these data are received as a JSON object, the
 * constructor receives a JSON object as a parameter. 
 */
public class AirportData {
	private String fsCode;
	private String iataCode;
	private String icaoCode;
	private String name;
	private String city;
	private String cityCode;
	private String stateCode;
	private String countryCode;
	private String countryName;
	private String regionName;
	private double latitude;
	private double longitude;
	private double elevationFeet;
	private int classification;
	private boolean active;
	/**
	 * @return the fsCode
	 */
	public String getFsCode() {
		return fsCode;
	}
	/**
	 * @param fsCode the fsCode to set
	 */
	public void setFsCode(String fsCode) {
		this.fsCode = fsCode;
	}
	/**
	 * @return the iataCode
	 */
	public String getIataCode() {
		return iataCode;
	}
	/**
	 * @param iataCode the iataCode to set
	 */
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	/**
	 * @return the icaoCode
	 */
	public String getIcaoCode() {
		return icaoCode;
	}
	/**
	 * @param icaoCode the icaoCode to set
	 */
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the elevationFeet
	 */
	public double getElevationFeet() {
		return elevationFeet;
	}
	/**
	 * @param elevationFeet the elevationFeet to set
	 */
	public void setElevationFeet(double elevationFeet) {
		this.elevationFeet = elevationFeet;
	}
	/**
	 * @return the clasification
	 */
	public int getClassification() {
		return classification;
	}
	/**
	 * @param clasification the clasification to set
	 */
	public void setClassification(int clasification) {
		this.classification = clasification;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public AirportData(JSONObject json)
	{
		try
		{
			setFsCode(json.getString("fs"));
			try{
				setIataCode(json.getString("iata"));
			} catch(JSONException ex){
				
			}
			try
			{
				setIcaoCode(json.getString("icao"));
			}
			catch(JSONException ex)
			{
				//Do nothing.
			}
			setName(json.getString("name"));
			setCity(json.getString("city"));
			try
			{
				setCityCode(json.getString("cityCode"));
			}
			catch(JSONException ex)
			{
				//Do nothing.
			}
			try
			{
			setStateCode(json.getString("stateCode"));
			}
			catch(JSONException ex)
			{
				setStateCode("");
			}
			setCountryCode(json.getString("countryCode"));
			setCountryName(json.getString("countryName"));
			setRegionName(json.getString("regionName"));
			setLatitude(json.getDouble("latitude"));
			setLongitude(json.getDouble("longitude"));
			try
			{
			setElevationFeet(json.getDouble("elevationFeet"));
			}
			catch (JSONException ex)
			{
				setElevationFeet(0);
			}
			setClassification(json.getInt("classification"));
			setActive(json.getBoolean("active"));
		}
		catch (JSONException ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
}
