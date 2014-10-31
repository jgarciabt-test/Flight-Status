package com.airportstatus.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.entities.Airline;

public class AirlineAdapter extends ArrayAdapter<Airline> {
	
	ArrayList<Airline> airlineList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public AirlineAdapter(Context context, int resource, ArrayList<Airline> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		airlineList = objects;
	}
 
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			
			holder.airlineName = (TextView) v.findViewById(R.id.tvAirline);
			holder.icaoCode = (TextView) v.findViewById(R.id.tvIcaoCode);
			
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		holder.airlineName.setText(airlineList.get(position).getName());
		holder.icaoCode.setText(airlineList.get(position).getIcaoCode());
		
		return v;

	}

	static class ViewHolder {
		
		public TextView airlineName;
		public TextView icaoCode;
		

	}

}
