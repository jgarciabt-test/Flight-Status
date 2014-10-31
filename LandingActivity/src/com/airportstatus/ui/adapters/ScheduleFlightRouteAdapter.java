package com.airportstatus.ui.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.models.ScheduleRouteModel;

public class ScheduleFlightRouteAdapter extends
		ArrayAdapter<ScheduleRouteModel> {

	private ArrayList<ScheduleRouteModel> list = null;
	private Context context = null;
	private LayoutInflater inflater = null;

	public ScheduleFlightRouteAdapter(Context context, int resource,  ArrayList<ScheduleRouteModel> list) {
		super(context, resource, list);
		this.list = list;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(inflater == null) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.route_row, parent, false);
		}
		
		ScheduleRouteModel currentRoute = list.get(position);
		
		TextView rFsCode = (TextView) convertView.findViewById(R.id.rViewCarrierFsCode);
		TextView rFlightNum = (TextView) convertView.findViewById(R.id.rViewFlightNumber);
		
		TextView rDepFsCode = (TextView) convertView.findViewById(R.id.rViewDepartureAirportFsCode);
		TextView rArrFsCode = (TextView) convertView.findViewById(R.id.rViewArrivalAirportFsCode);
		
		TextView rNumStops = (TextView) convertView.findViewById(R.id.rViewNumStops);
		TextView rDeptTime = (TextView) convertView.findViewById(R.id.rViewDeptTime);
		TextView rArrTime = (TextView) convertView.findViewById(R.id.rViewArrTime);
		TextView rFlightEqIataCode = (TextView) convertView.findViewById(R.id.rViewFlightEqIata);
		TextView rIsCodeShare = (TextView) convertView.findViewById(R.id.rViewIsCodeShare);
		TextView rIsWetlease = (TextView) convertView.findViewById(R.id.rViewIsWetlease);
		TextView rServType = (TextView) convertView.findViewById(R.id.rViewServiceType);
		TextView rRefCode = (TextView) convertView.findViewById(R.id.rViewReferenceCode);
		
		rFsCode.setText(" "+currentRoute.getRouteCarrierFsCode());
		rFlightNum.setText(" "+currentRoute.getRouteFlightNumber());
		
		rDepFsCode.setText(" "+currentRoute.getRouteDepartureFsCode());
		rArrFsCode.setText(" "+currentRoute.getRouteArrivalFsCode());
		
		rNumStops.setText(" "+currentRoute.getRouteNumberOfStops());
		rDeptTime.setText(" "+currentRoute.getRouteDepartureTime());
		rArrTime.setText(" "+currentRoute.getRouteArrivalTime());
		rFlightEqIataCode.setText(" "+currentRoute.getRouteFlightEquipmentIataCode());
		rIsCodeShare.setText(" "+currentRoute.getRouteIsCodeShare());
		rIsWetlease.setText(" "+currentRoute.getRouteIsWetlease());
		rServType.setText(" "+currentRoute.getRouteServiceType());
		rRefCode.setText(" "+currentRoute.getRouteReferenceCode());
		
		
		return convertView;
	}

}
