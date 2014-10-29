package com.airportstatus.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.interfaces.FlightStatsClient;
import com.airportstatus.models.CarrierShedules;

public class ViewCarrierScheduledFlightsFragment extends Fragment {
	private View parentView;

	private CarrierShedules carrierSchedule = null;
	TextView numStops, cDepCode, cDepTerminal, cDepTime, cArrCode,
			cArrTerminal, cArrTime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_schedule_carrier,
				container, false);

		this.carrierSchedule = FlightStatsClient.getCarrierSchedules();

		initialize();
		numStops.setText("Number of Stops : "
				+ this.carrierSchedule.getNumberOfStops());
		cDepCode.setText("Departure Airport FS Code : "
				+ this.carrierSchedule.getDepartureAirportCode());
		cDepTerminal.setText("Departure Teminal : "
				+ this.carrierSchedule.getDepartureTerminal());
		cDepTime.setText("Departure Time : "
				+ this.carrierSchedule.getDepartureTime());
		cArrCode.setText("Arrival Airport FS Code : "
				+ this.carrierSchedule.getArrivalAirportCode());
		cArrTerminal.setText("Arrival Terminal : "
				+ this.carrierSchedule.getArrivalTerminal());
		cArrTime.setText("Arrival Time : "
				+ this.carrierSchedule.getArrivalTime());

		return parentView;
	}

	private void initialize() {
		numStops = (TextView) parentView.findViewById(R.id.numOfStops);
		cDepCode = (TextView) parentView.findViewById(R.id.carrierDepCode);
		cDepTerminal = (TextView) parentView
				.findViewById(R.id.carrierDepTerminal);
		cDepTime = (TextView) parentView.findViewById(R.id.carrierDepTime);
		cArrCode = (TextView) parentView.findViewById(R.id.carrierArrCode);
		cArrTerminal = (TextView) parentView
				.findViewById(R.id.carrierArrTerminal);
		cArrTime = (TextView) parentView.findViewById(R.id.carrierArrTime);
	}

}
