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
		
		//If there is information display it, if not hide everything
		TextView diverted = (TextView) dialog.findViewById(R.id.txt_diverted1);
		if(!rate.getDiverted().matches("")){
			diverted.setText(rate.getDiverted());
		} else{
			diverted.setVisibility(View.GONE);
			TextView diverted2 = (TextView) dialog.findViewById(R.id.txt_diverted);
			diverted2.setVisibility(View.GONE);
		}
		
		//If there is information display it, if not hide everything
		TextView delayMin = (TextView) dialog.findViewById(R.id.txt_min_delay_1);
		if(!rate.getDelayMin().matches("")){
			delayMin.setText(rate.getDelayMin());
		} else{
			TextView delayMin2 = (TextView) dialog.findViewById(R.id.txt_min_delay);
			delayMin.setVisibility(View.GONE);
			delayMin2.setVisibility(View.GONE);
		}
		
		//If there is information display it, if not hide everything
		TextView delayMax = (TextView) dialog.findViewById(R.id.txt_max_delay_1);
		if(!rate.getDelayMax().matches("")){
			delayMax.setText(rate.getDelayMax());
		} else{
			TextView delayMax2 = (TextView) dialog.findViewById(R.id.txt_max_delay);
			delayMax.setVisibility(View.GONE);
			delayMax2.setVisibility(View.GONE);
		}
		
		//If there is information display it, if not hide everything
		TextView stars = (TextView) dialog.findViewById(R.id.txt_stars1);
		if(!rate.getOnTimeStars().matches("")){
			stars.setText(rate.getOnTimeStars());
		} else{
			TextView stars2 = (TextView) dialog.findViewById(R.id.txt_stars);
			stars.setVisibility(View.GONE);
			stars2.setVisibility(View.GONE);
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
