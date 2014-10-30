package com.airportstatus.fragments;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.entities.Rating;

public class CustomDialog {

	
static public void shodDialog(Context context, Rating rate, String title){
		

		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.rate_dialog);
		dialog.setTitle(title);

		// set the custom dialog components 
		TextView observations= (TextView) dialog.findViewById(R.id.txt_observations1);
		observations.setText(rate.getObservations());
		
		TextView onTime = (TextView) dialog.findViewById(R.id.txt_on_time1);
		onTime.setText(rate.getOnTime());
		
		TextView late15 = (TextView) dialog.findViewById(R.id.txt_late15);
		late15.setText(rate.getLate15());
		
		TextView late30= (TextView) dialog.findViewById(R.id.txt_late30);
		late30.setText(rate.getLate30());
		
		TextView late45 = (TextView) dialog.findViewById(R.id.txt_late45);
		late45.setText(rate.getLate45());
		
		TextView cancelled = (TextView) dialog.findViewById(R.id.txt_cancelled1);
		cancelled.setText(rate.getCancelled());
		
		if(!rate.getDiverted().matches("")){
			TextView diverted = (TextView) dialog.findViewById(R.id.txt_diverted1);
			diverted.setText(rate.getDiverted());
		}
		
		if(!rate.getDelayMin().matches("")){
			TextView delayMin = (TextView) dialog.findViewById(R.id.txt_min_delay_1);
			delayMin.setText(rate.getDelayMin());
		}
		
		if(!rate.getDelayMax().matches("")){
			TextView delayMax = (TextView) dialog.findViewById(R.id.txt_max_delay_1);
			delayMax.setText(rate.getDelayMax());
		}
		
		if(!rate.getOnTimeStars().matches("")){
			TextView stars = (TextView) dialog.findViewById(R.id.txt_stars1);
			stars.setText(rate.getOnTimeStars());
		}
		
		

		Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
		
	}
	
	
}
