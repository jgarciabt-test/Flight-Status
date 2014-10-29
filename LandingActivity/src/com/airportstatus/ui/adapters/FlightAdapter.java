/**
 * 
 */
package com.airportstatus.ui.adapters;

import java.util.ArrayList;
import java.util.Calendar;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.entities.FlightStatus;

/**
 * @author pablo
 *
 */
public class FlightAdapter extends ArrayAdapter<FlightStatus> implements StickyListHeadersAdapter {
	private ArrayList<FlightStatus> flights;
	private boolean arrival;
	public FlightAdapter(Context context, ArrayList<FlightStatus> flights, boolean arrival) {
		super(context, R.layout.element_flight_status);
		this.flights = flights;
		this.arrival = arrival;
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return flights.size();
	}
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getItem(int)
	 */
	@Override
	public FlightStatus getItem(int position) {
		// TODO Auto-generated method stub
		return flights.get(position);
	}
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null)
		{
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.element_flight_status, parent, false);
		}
		((TextView)view.findViewById(R.id.tv_airline)).setText(getItem(position).getAirline().getFsCode());
		((TextView)view.findViewById(R.id.tv_flight_number)).setText(Integer.toString(getItem(position).getFlightNumber()));
		if (arrival)
		{
			((TextView)view.findViewById(R.id.tv_date)).setText(getItem(position).getLocalArrivalDate().get(Calendar.DATE) + "/" + getItem(position).getLocalArrivalDate().get(Calendar.MONTH) + "/" + getItem(position).getLocalArrivalDate().get(Calendar.YEAR));
			((TextView)view.findViewById(R.id.tv_time)).setText(getItem(position).getLocalArrivalDate().get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", getItem(position).getLocalArrivalDate().get(Calendar.MINUTE)));
			((TextView)view.findViewById(R.id.tv_terminal)).setText(getItem(position).getArrivalTerminal());
			((TextView)view.findViewById(R.id.tv_city)).setText(getItem(position).getDepartureAirport().getCity() + "(" + getItem(position).getDepartureAirport().getIataCode() + ")");
		}
		else
		{
			((TextView)view.findViewById(R.id.tv_date)).setText(getItem(position).getLocalDepartureDate().get(Calendar.DATE) + "/" + getItem(position).getLocalDepartureDate().get(Calendar.MONTH) + "/" + getItem(position).getLocalDepartureDate().get(Calendar.YEAR));
			((TextView)view.findViewById(R.id.tv_time)).setText(getItem(position).getLocalDepartureDate().get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", getItem(position).getLocalDepartureDate().get(Calendar.MINUTE)));
			((TextView)view.findViewById(R.id.tv_terminal)).setText(getItem(position).getDepartureTerminal());
			((TextView)view.findViewById(R.id.tv_city)).setText(getItem(position).getArrivalAirport().getCity() + "(" + getItem(position).getArrivalAirport().getIataCode() + ")");
		}
		String rawStatus = getItem(position).getStatus();
		String readable;
		if (rawStatus.equals("S"))
			readable = getContext().getString(R.string.scheduled);
		else if (rawStatus.equals("A"))
			readable = getContext().getString(R.string.active);
		else if (rawStatus.equals("C"))
			readable = getContext().getString(R.string.cancel);
		else if (rawStatus.equals("D"))
			readable = getContext().getString(R.string.diverted);
		else if (rawStatus.equals("DN"))
			readable = getContext().getString(R.string.datasource_needed);
		else if (rawStatus.equals("L"))
			readable = getContext().getString(R.string.landed);
		else if (rawStatus.equals("NO"))
			readable = getContext().getString(R.string.not_operational);
		else if (rawStatus.equals("R"))
			readable = getContext().getString(R.string.redirected);
		else if (rawStatus.equals("U"))
			readable = getContext().getString(R.string.unknown);
		else
			readable = "";
		((TextView) view.findViewById(R.id.tv_status)).setText(readable);
		return view;
	}
	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null)
		{
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.header_flight_status, parent, false);
			((TextView)view.findViewById(R.id.tv_airline)).setText("AL");
			((TextView)view.findViewById(R.id.tv_flight_number)).setText("Num.");
			((TextView)view.findViewById(R.id.tv_date)).setText("Date");
			((TextView)view.findViewById(R.id.tv_time)).setText("Time");
			if (arrival)
				((TextView)view.findViewById(R.id.tv_city)).setText("From");
			else
				((TextView)view.findViewById(R.id.tv_city)).setText("To");
			((TextView)view.findViewById(R.id.tv_status)).setText("Status");
			((TextView)view.findViewById(R.id.tv_terminal)).setText("T");
		}
		return view;
	}
	@Override
	public long getHeaderId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
