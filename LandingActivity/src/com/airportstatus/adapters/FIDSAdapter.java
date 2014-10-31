package com.airportstatus.adapters;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.entities.FIDS;

public class FIDSAdapter extends BaseAdapter implements StickyListHeadersAdapter {
	
	
	private ArrayList<FIDS> fids;
	private LayoutInflater inflater;
	
	

	public FIDSAdapter(Context context, ArrayList<FIDS> fids) {
		super();
		this.fids = fids;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fids.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return fids.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return fids.indexOf(fids.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			ViewHolder holds;
		
			if (convertView == null)
			{
				holds = new ViewHolder();
	            convertView = inflater.inflate(R.layout.fids_layout, parent, false);
	            holds.ac = (TextView) convertView.findViewById(R.id.tvFIDSAC);
	            holds.fn = (TextView) convertView.findViewById(R.id.tvFIDSFN);
	            holds.status = (TextView) convertView.findViewById(R.id.tvFIDSStatus);
	            holds.city = (TextView) convertView.findViewById(R.id.tvFIDSCity);
	            holds.time = (TextView) convertView.findViewById(R.id.tvFIDSTime);
	            convertView.setTag(holds);
	        } else {
	        	holds = (ViewHolder) convertView.getTag();
			}
			
			FIDS ha = fids.get(position);
			holds.ac.setText(ha.getAirlineCode());
			holds.fn.setText(ha.getFlightNumber());
			holds.status.setText(ha.getRemarks());
			holds.city.setText(ha.getCity());
			holds.time.setText(ha.getCurrentTime());
		 
		return convertView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.fids_layout, parent, false);
		TextView acLabel = (TextView) convertView.findViewById(R.id.tvFIDSAC);
		TextView fnLabel = (TextView) convertView.findViewById(R.id.tvFIDSFN);
		TextView statusLabel = (TextView) convertView.findViewById(R.id.tvFIDSStatus);
		TextView cityLabel = (TextView) convertView.findViewById(R.id.tvFIDSCity);
		TextView timeLabel = (TextView) convertView.findViewById(R.id.tvFIDSTime);
		
		acLabel.setText("Airline Code");
		fnLabel.setText("Flight N.");
		statusLabel.setText("Status");
		cityLabel.setText("City");
		timeLabel.setText("Time");
		
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public class ViewHolder
	{
		TextView ac;
		TextView fn;
		TextView status;
		TextView city;
		TextView time;
	}

}
