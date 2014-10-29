/**
 * 
 */
package com.airportstatus.ui.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.entities.AirportData;

/**
 * @author pablo
 *
 */
public class AirportPickerAdapter extends ArrayAdapter<AirportData> {
	ArrayList<AirportData> airports;
	public AirportPickerAdapter(Context context, ArrayList<AirportData> airports) {
		super(context, R.layout.airport_picker_row);
		this.airports = airports;
	}
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return airports.size();
	}
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getItem(int)
	 */
	@Override
	public AirportData getItem(int position) {
		// TODO Auto-generated method stub
		return airports.get(position);
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
			view = inflater.inflate(R.layout.airport_picker_row, parent, false);
		}
		((TextView)view.findViewById(R.id.tv_fs_code)).setText(getItem(position).getFsCode());
		((TextView)view.findViewById(R.id.tv_name)).setText(getItem(position).getName());
		((TextView)view.findViewById(R.id.tv_city)).setText(getItem(position).getCity());
		return view;
	}

}
