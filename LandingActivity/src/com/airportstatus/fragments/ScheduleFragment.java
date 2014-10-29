package com.airportstatus.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.airportstatus.R;

public class ScheduleFragment extends Fragment {

	private View parentView;

	Button searchByCarrier, searchByAirport, searchByRoute;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_schedule_menu,
				container, false);

		searchByCarrier = (Button) parentView.findViewById(R.id.flightCarrier);
		searchByAirport = (Button) parentView.findViewById(R.id.flightAirport);
		searchByRoute = (Button) parentView.findViewById(R.id.flightRoute);

		searchByCarrier.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ScheduledFlightsByCarrierFragment scheduleCarrierFragment = new ScheduledFlightsByCarrierFragment();

				getFragmentManager()
						.beginTransaction()
						.replace(R.id.flight_information_layout,
								scheduleCarrierFragment).commit();
			}
		});

		return parentView;
	}

}
