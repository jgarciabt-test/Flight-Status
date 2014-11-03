package com.airportstatus.fragments;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airportstatus.R;
import com.airportstatus.interfaces.FlightStatsClient;
import com.airportstatus.models.ScheduleRouteModel;
import com.airportstatus.ui.adapters.ScheduleFlightRouteAdapter;

public class ViewRouteScheduleFlightsFragment extends Fragment {
	private View parentView;

	private ArrayList<ScheduleRouteModel> routeList = null;
	private ScheduleFlightRouteAdapter adapter = null;

	private StickyListHeadersListView routeListview = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.view_route_list, container,
				false);

		this.routeList = FlightStatsClient.getRouteScheduleList();

		initialize();
		
		adapter = new ScheduleFlightRouteAdapter(getActivity(), R.layout.route_row, routeList);
		
		routeListview.setAdapter(adapter);
		

		return parentView;
	}

	private void initialize() {
		
		routeListview = (StickyListHeadersListView) parentView.findViewById(R.id.routeList);
	}

}
