package com.airportstatus.ui.adapters;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.models.ScheduleRouteModel;

public class ScheduleFlightRouteAdapter extends
		BaseAdapter implements StickyListHeadersAdapter {

	private ArrayList<ScheduleRouteModel> list = null;
	private Context context = null;
	private LayoutInflater inflater = null;

	public ScheduleFlightRouteAdapter(Context context, int resource,  ArrayList<ScheduleRouteModel> list) {
		super();
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
		
		//TextView rFsCode = (TextView) convertView.findViewById(R.id.rViewCarrierFsCode);
		TextView rFlightNum = (TextView) convertView.findViewById(R.id.rViewFlightNumber);
		TextView rArrFsCode = (TextView) convertView.findViewById(R.id.rViewArrivalAirportFsCode);
		
		TextView rNumStops = (TextView) convertView.findViewById(R.id.rViewNumStops);
		TextView rDeptTime = (TextView) convertView.findViewById(R.id.rViewDeptTime);
		TextView rArrTime = (TextView) convertView.findViewById(R.id.rViewArrTime);

		
		//rFsCode.setText(" "+currentRoute.getRouteCarrierFsCode());
		rFlightNum.setText(" "+currentRoute.getRouteFlightNumber());
		rArrFsCode.setText(" "+currentRoute.getRouteArrivalFsCode());
		
		rNumStops.setText(" "+currentRoute.getRouteNumberOfStops());
		rDeptTime.setText(" "+currentRoute.getRouteDepartureTime());
		rArrTime.setText(" "+currentRoute.getRouteArrivalTime());

		
		return convertView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return list.indexOf(getItem(position));
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {

		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.route_row, parent, false);
			
			//TextView rFsCode = (TextView) convertView.findViewById(R.id.rViewCarrierFsCode);
			TextView rFlightNum = (TextView) convertView.findViewById(R.id.rViewFlightNumber);
			TextView rArrFsCode = (TextView) convertView.findViewById(R.id.rViewArrivalAirportFsCode);
			
			TextView rNumStops = (TextView) convertView.findViewById(R.id.rViewNumStops);
			TextView rDeptTime = (TextView) convertView.findViewById(R.id.rViewDeptTime);
			TextView rArrTime = (TextView) convertView.findViewById(R.id.rViewArrTime);

			
			//rFsCode.setText("FS");
			rFlightNum.setText("Flight");
			rArrFsCode.setText("Arrival");
			
			rNumStops.setText("Stops");
			rDeptTime.setText("Dep. Time");
			rArrTime.setText("Arr. Time");
		}
		
		


		
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		return 0;
	}

}
