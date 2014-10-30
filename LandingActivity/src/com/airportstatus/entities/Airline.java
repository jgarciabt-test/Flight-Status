/**
 * 
 */
package com.airportstatus.entities;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Pablo Sanchez Alonso
 * @version 1.0 This class represents an airline. Using the API, airlines
 *          information is sent using JSON so the class is constructed using a
 *          JSON object which contains all the attributes for a particular
 *          airline.
 */
public class Airline implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String fsCode;
	private String iataCode;
	private String name;
	private String icaoCode;
	private boolean active;

	/**
	 * @return the code of the airline for Flight Stats.
	 */
	public String getFsCode()
	{
		return fsCode;
	}

	/**
	 * @param fsCode
	 *            The code of the airline for Flight Stats.
	 */
	public void setFsCode(String fsCode)
	{
		this.fsCode = fsCode;
	}

	/**
	 * @return the code that IATA uses for the airline.
	 */
	public String getIataCode()
	{
		return iataCode;
	}

	/**
	 * @param iataCode
	 *            is the code of the airline that IATA uses.
	 */
	public void setIataCode(String iataCode)
	{
		this.iataCode = iataCode;
	}

	/**
	 * @return the name of the airline.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name of the airline.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the code for ICAO.
	 */
	public String getIcaoCode()
	{
		return icaoCode;
	}

	/**
	 * @param icaoCode
	 *            the code for ICAO.
	 */
	public void setIcaoCode(String icaoCode)
	{
		this.icaoCode = icaoCode;
	}

	/**
	 * @return true if the airline is active, false otherwise.
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * @param active
	 *            should be true if the airline is active or false otherwise.
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	public Airline(JSONObject json)
	{
		try
		{
			setFsCode(json.getString("fs"));
			setIataCode(json.getString("iata"));
			setIcaoCode(json.getString("icao"));
			setName(json.getString("name"));
			setActive(json.getBoolean("active"));
		} catch (JSONException ex)
		{
			ex.printStackTrace();
		}
	}
}
