/**
 * 
 */
package com.airportstatus.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Pablo Sanchez Alonso
 * @version 1.0
 */
public class FlightStatus {
	private static ArrayList<AirportData> airports = new ArrayList<AirportData>();
	private static ArrayList<Airline> airlines = new ArrayList<Airline>();
	private static ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();
	private double flightId;
	private Airline airline;
	private int flightNumber;
	private AirportData departureAirport;
	private AirportData arrivalAirport;
	private Calendar localDepartureDate;
	private Calendar utcDepartureDate;
	private Calendar localArrivalDate;
	private Calendar utcArrivalDate;
	private String status;
	private String flightType;
	private String serviceClasses;
	private String restrictions;
	private Calendar localPublishedDepartureDate;
	private Calendar utcPublishedDepartureDate;
	private Calendar localPublishedArrivalDate;
	private Calendar utcPublishedArrivalDate;
	private Calendar localScheduleGateDeparture;
	private Calendar utcScheduleGateDeparture;
	private Calendar localScheduleGateArrival;
	private Calendar utcScheduleGateArrival;
	private Calendar localEstimatedGateArrival;
	private Calendar utcEstimatedGateArrival;
	private int duration;
	private String departureTerminal;
	private String arrivalTerminal;
	private String baggage;
	private Aircraft aircraft;
	
	/**
	 * @return the airports
	 */
	public static ArrayList<AirportData> getAirports() {
		return airports;
	}
	/**
	 * @return the airlines
	 */
	public static ArrayList<Airline> getAirlines() {
		return airlines;
	}
	/**
	 * @return the aircraft
	 */
	public static ArrayList<Aircraft> getAircrafts() {
		return aircrafts;
	}
	/**
	 * @return the flightId
	 */
	public double getFlightId() {
		return flightId;
	}
	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(double flightId) {
		this.flightId = flightId;
	}
	/**
	 * @return the airline
	 */
	public Airline getAirline() {
		return airline;
	}
	/**
	 * @param airline the airline to set
	 */
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	/**
	 * @return the flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
	}
	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	/**
	 * @return the departureAirport
	 */
	public AirportData getDepartureAirport() {
		return departureAirport;
	}
	/**
	 * @param departureAirport the departureAirport to set
	 */
	public void setDepartureAirport(AirportData departureAirport) {
		this.departureAirport = departureAirport;
	}
	/**
	 * @return the arrivalAirport
	 */
	public AirportData getArrivalAirport() {
		return arrivalAirport;
	}
	/**
	 * @param arrivalAirport the arrivalAirport to set
	 */
	public void setArrivalAirport(AirportData arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	/**
	 * @return the localDepartureDate
	 */
	public Calendar getLocalDepartureDate() {
		return localDepartureDate;
	}
	/**
	 * @param localDepartureDate the localDepartureDate to set
	 */
	public void setLocalDepartureDate(Calendar localDepartureDate) {
		this.localDepartureDate = localDepartureDate;
	}
	/**
	 * @return the utcDepartureDate
	 */
	public Calendar getUtcDepartureDate() {
		return utcDepartureDate;
	}
	/**
	 * @param utcDepartureDate the utcDepartureDate to set
	 */
	public void setUtcDepartureDate(Calendar utcDepartureDate) {
		this.utcDepartureDate = utcDepartureDate;
	}
	/**
	 * @return the localArrivalDate
	 */
	public Calendar getLocalArrivalDate() {
		return localArrivalDate;
	}
	/**
	 * @param localArrivalDate the localArrivalDate to set
	 */
	public void setLocalArrivalDate(Calendar localArrivalDate) {
		this.localArrivalDate = localArrivalDate;
	}
	/**
	 * @return the utcArrivalDate
	 */
	public Calendar getUtcArrivalDate() {
		return utcArrivalDate;
	}
	/**
	 * @param utcArrivalDate the utcArrivalDate to set
	 */
	public void setUtcArrivalDate(Calendar utcArrivalDate) {
		this.utcArrivalDate = utcArrivalDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the flightType
	 */
	public String getFlightType() {
		return flightType;
	}
	/**
	 * @param flightType the flightType to set
	 */
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	/**
	 * @return the serviceClasses
	 */
	public String getServiceClasses() {
		return serviceClasses;
	}
	/**
	 * @param serviceClasses the serviceClasses to set
	 */
	public void setServiceClasses(String serviceClasses) {
		this.serviceClasses = serviceClasses;
	}
	/**
	 * @return the restrictions
	 */
	public String getRestrictions() {
		return restrictions;
	}
	/**
	 * @param restrictions the restrictions to set
	 */
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	/**
	 * @return the localPublishedDepartureDate
	 */
	public Calendar getLocalPublishedDepartureDate() {
		return localPublishedDepartureDate;
	}
	/**
	 * @param localPublishedDepartureDate the localPublishedDepartureDate to set
	 */
	public void setLocalPublishedDepartureDate(Calendar localPublishedDepartureDate) {
		this.localPublishedDepartureDate = localPublishedDepartureDate;
	}
	/**
	 * @return the utcPublishedDepartureDate
	 */
	public Calendar getUtcPublishedDepartureDate() {
		return utcPublishedDepartureDate;
	}
	/**
	 * @param utcPublishedDepartureDate the utcPublishedDepartureDate to set
	 */
	public void setUtcPublishedDepartureDate(Calendar utcPublishedDepartureDate) {
		this.utcPublishedDepartureDate = utcPublishedDepartureDate;
	}
	/**
	 * @return the localPublishedArrivalDate
	 */
	public Calendar getLocalPublishedArrivalDate() {
		return localPublishedArrivalDate;
	}
	/**
	 * @param localPublishedArrivalDate the localPublishedArrivalDate to set
	 */
	public void setLocalPublishedArrivalDate(Calendar localPublishedArrivalDate) {
		this.localPublishedArrivalDate = localPublishedArrivalDate;
	}
	/**
	 * @return the utcPublishedArrivalDate
	 */
	public Calendar getUtcPublishedArrivalDate() {
		return utcPublishedArrivalDate;
	}
	/**
	 * @param utcPublishedArrivalDate the utcPublishedArrivalDate to set
	 */
	public void setUtcPublishedArrivalDate(Calendar utcPublishedArrivalDate) {
		this.utcPublishedArrivalDate = utcPublishedArrivalDate;
	}
	/**
	 * @return the localScheduleGateDeparture
	 */
	public Calendar getLocalScheduleGateDeparture() {
		return localScheduleGateDeparture;
	}
	/**
	 * @param localScheduleGateDeparture the localScheduleGateDeparture to set
	 */
	public void setLocalScheduleGateDeparture(Calendar localScheduleGateDeparture) {
		this.localScheduleGateDeparture = localScheduleGateDeparture;
	}
	/**
	 * @return the utcScheduleGateDeparture
	 */
	public Calendar getUtcScheduleGateDeparture() {
		return utcScheduleGateDeparture;
	}
	/**
	 * @param utcScheduleGateDeparture the utcScheduleGateDeparture to set
	 */
	public void setUtcScheduleGateDeparture(Calendar utcScheduleGateDeparture) {
		this.utcScheduleGateDeparture = utcScheduleGateDeparture;
	}
	/**
	 * @return the localScheduleGateArrival
	 */
	public Calendar getLocalScheduleGateArrival() {
		return localScheduleGateArrival;
	}
	/**
	 * @param localScheduleGateArrival the localScheduleGateArrival to set
	 */
	public void setLocalScheduleGateArrival(Calendar localScheduleGateArrival) {
		this.localScheduleGateArrival = localScheduleGateArrival;
	}
	/**
	 * @return the utcScheduleGateArrival
	 */
	public Calendar getUtcScheduleGateArrival() {
		return utcScheduleGateArrival;
	}
	/**
	 * @param utcScheduleGateArrival the utcScheduleGateArrival to set
	 */
	public void setUtcScheduleGateArrival(Calendar utcScheduleGateArrival) {
		this.utcScheduleGateArrival = utcScheduleGateArrival;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the departureTerminal
	 */
	public String getDepartureTerminal() {
		return departureTerminal;
	}
	/**
	 * @param departureTerminal the departureTerminal to set
	 */
	public void setDepartureTerminal(String departureTerminal) {
		this.departureTerminal = departureTerminal;
	}
	/**
	 * @return the arrivalTerminal
	 */
	public String getArrivalTerminal() {
		return arrivalTerminal;
	}
	/**
	 * @param arrivalTerminal the arrivalTerminal to set
	 */
	public void setArrivalTerminal(String arrivalTerminal) {
		this.arrivalTerminal = arrivalTerminal;
	}
	/**
	 * @return the baggage
	 */
	public String getBaggage() {
		return baggage;
	}
	/**
	 * @param baggage the baggage to set
	 */
	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}
	/**
	 * @return the aircraft
	 */
	public Aircraft getAircraft() {
		return aircraft;
	}
	/**
	 * @param aircraft the aircraft to set
	 */
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	public FlightStatus(JSONObject json)
	{
		try {
			setFlightId(json.getDouble("flightId"));
			setAirline(findAirlineByFsCode(json.getString("carrierFsCode")));
			setFlightNumber(json.getInt("flightNumber"));
			setDepartureAirport(findAirportDataByFsCode(json.getString("departureAirportFsCode")));
			setArrivalAirport(findAirportDataByFsCode(json.getString("arrivalAirportFsCode")));
			//Departure Date
			JSONObject jsonAux = json.getJSONObject("departureDate");
			setLocalDepartureDate(convertDate(jsonAux.getString("dateLocal")));
			setUtcDepartureDate(convertDate(jsonAux.getString("dateUtc")));
			//Arrival Date
			jsonAux = json.getJSONObject("arrivalDate");
			setLocalArrivalDate(convertDate(jsonAux.getString("dateLocal")));
			setUtcArrivalDate(convertDate(jsonAux.getString("dateUtc")));
			
			setStatus(json.getString("status"));
			//Schedule
			try
			{
			jsonAux = json.getJSONObject("schedule");
			setFlightType(jsonAux.getString("flightType"));
			setServiceClasses(jsonAux.getString("serviceClasses"));
			setRestrictions(jsonAux.getString("restrictions"));
			}
			catch(JSONException ex)
			{
				//Do nothing.
			}
			//Operational times
			jsonAux = json.getJSONObject("operationalTimes");
			//Published departure date
			/*JSONObject jsonAux2 = jsonAux.getJSONObject("publishedDeparture");
			setLocalPublishedDepartureDate(convertDate(jsonAux2.getString("dateLocal")));
			setUtcPublishedDepartureDate(convertDate(jsonAux2.getString("dateUtc")));
			//Published arrival date
			jsonAux2 = jsonAux.getJSONObject("publishedArrival");
			setLocalPublishedArrivalDate(convertDate(jsonAux2.getString("dateLocal")));
			setUtcPublishedArrivalDate(convertDate(jsonAux2.getString("dateUtc")));
			//Schedule gate departure
			jsonAux2 = jsonAux.getJSONObject("scheduledGateDeparture");
			setLocalScheduleGateDeparture(convertDate(jsonAux2.getString("dateLocal")));
			setUtcScheduleGateDeparture(convertDate(jsonAux2.getString("dateUtc")));
			//Schedule gate arrival
			jsonAux2 = jsonAux.getJSONObject("scheduledGateArrival");
			setLocalScheduleGateArrival(convertDate(jsonAux2.getString("dateLocal")));
			setUtcScheduleGateArrival(convertDate(jsonAux2.getString("dateUtc")));
			//Estimated gate arrival
			/*jsonAux2 = jsonAux.getJSONObject("estimatedGateArrival");
			setLocalEstimatedGateArrival(convertDate(jsonAux2.getString("dateLocal")));
			setUtcEstimatedGateArrival(convertDate(jsonAux2.getString("dateUtc")));*/
			//Flight duration
			try
			{
				jsonAux = json.getJSONObject("flightDurations");
				setDuration(jsonAux.getInt("scheduledBlockMinutes"));
			}
			catch(JSONException ex)
			{
				//Do nothing.
			}
			//Flight equipment
			try
			{
				jsonAux = json.getJSONObject("flightEquipment");
				setAircraft(findAircraftByIataCode(jsonAux.getString("scheduledEquipmentIataCode")));
			}
			catch (JSONException ex)
			{
				//Do nothing.
			}
			try
			{
				jsonAux = json.getJSONObject("airportResources");
				try
				{
					setDepartureTerminal(jsonAux.getString("departureTerminal"));
				}
				catch (JSONException ex)
				{
					//Do nothing
				}
				try
				{
					setArrivalTerminal(jsonAux.getString("arrivalTerminal"));
				}
				catch (JSONException ex)
				{
					//Do nothing
				}
				setBaggage(jsonAux.getString("baggage"));
			}
			catch (JSONException ex)
			{
				//Do nothing
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Airline findAirlineByFsCode(String fsCode)
	{
		Airline airline = null;
		Iterator<Airline> itrAirlines = getAirlines().iterator();
		while(itrAirlines.hasNext() && airline == null)
		{
			Airline currentAirline = itrAirlines.next();
			if (currentAirline.getFsCode().equals(fsCode))
				airline = currentAirline;
		}
		return airline;
	}
	
	private AirportData findAirportDataByFsCode(String fsCode)
	{
		AirportData airport = null;
		Iterator<AirportData> itrAirports = getAirports().iterator();
		while (itrAirports.hasNext() && airport == null)
		{
			AirportData currentAirport = itrAirports.next();
			if (currentAirport.getFsCode().equals(fsCode))
				airport = currentAirport;
		}
		return airport;
	}
	
	private Calendar convertDate(String dateString)
	{
		String[] dateTimeStrings = dateString.split("T");
		String[] dateElements = dateTimeStrings[0].split("-");
		String[] timeElements = dateTimeStrings[1].split(":");
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(dateElements[0]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[2]), Integer.parseInt(timeElements[0]), Integer.parseInt(timeElements[1]));
		return date;
	}
	/**
	 * @return the localEstimatedGateArrival
	 */
	public Calendar getLocalEstimatedGateArrival() {
		return localEstimatedGateArrival;
	}
	/**
	 * @param localEstimatedGateArrival the localEstimatedGateArrival to set
	 */
	public void setLocalEstimatedGateArrival(Calendar localEstimatedGateArrival) {
		this.localEstimatedGateArrival = localEstimatedGateArrival;
	}
	/**
	 * @return the utcEstimatedGateArrival
	 */
	public Calendar getUtcEstimatedGateArrival() {
		return utcEstimatedGateArrival;
	}
	/**
	 * @param utcEstimatedGateArrival the utcEstimatedGateArrival to set
	 */
	public void setUtcEstimatedGateArrival(Calendar utcEstimatedGateArrival) {
		this.utcEstimatedGateArrival = utcEstimatedGateArrival;
	}
	
	private Aircraft findAircraftByIataCode(String iataCode)
	{
		Aircraft aircraft = null;
		Iterator<Aircraft> itrAircrafts = getAircrafts().iterator();
		while (itrAircrafts.hasNext() && aircraft == null)
		{
			Aircraft currentAircraft = itrAircrafts.next();
			if (currentAircraft.getIataCode().equals(iataCode))
				aircraft = currentAircraft;
		}
		return aircraft;
	}
}
