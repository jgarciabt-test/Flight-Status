/**
 * 
 */
package com.airportstatus.entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Pablo Sanchez Alonso
 * @version 1.0
 */
public class Aircraft {
	private String iataCode;
	private String name;
	private boolean turboProp;
	private boolean jet;
	private boolean widebody;
	private boolean regional;
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
	 * @return the turboProp
	 */
	public boolean isTurboProp() {
		return turboProp;
	}
	/**
	 * @param turboProp the turboProp to set
	 */
	public void setTurboProp(boolean turboProp) {
		this.turboProp = turboProp;
	}
	/**
	 * @return the jet
	 */
	public boolean isJet() {
		return jet;
	}
	/**
	 * @param jet the jet to set
	 */
	public void setJet(boolean jet) {
		this.jet = jet;
	}
	/**
	 * @return the widebody
	 */
	public boolean isWidebody() {
		return widebody;
	}
	/**
	 * @param widebody the widebody to set
	 */
	public void setWidebody(boolean widebody) {
		this.widebody = widebody;
	}
	/**
	 * @return the regional
	 */
	public boolean isRegional() {
		return regional;
	}
	/**
	 * @param regional the regional to set
	 */
	public void setRegional(boolean regional) {
		this.regional = regional;
	}
	
	public Aircraft(JSONObject json)
	{
		try
		{
			setIataCode(json.getString("iata"));
			setName(json.getString("name"));
			setTurboProp(json.getBoolean("turboProp"));
			setJet(json.getBoolean("jet"));
			setWidebody(json.getBoolean("widebody"));
			setRegional(json.getBoolean("regional"));
		}
		catch(JSONException ex)
		{
			ex.printStackTrace();
		}
	}
}
