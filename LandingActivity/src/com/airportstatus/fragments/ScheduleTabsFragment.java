package com.airportstatus.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airportstatus.R;
import com.airportstatus.interfaces.TabListener;

public class ScheduleTabsFragment extends Fragment {

	private View parentView;
	public static ActionBar actionBar = null;
	
	ActionBar.Tab Tab1, Tab2, Tab3;
    Fragment fragmentTab1 = new ScheduledFlightsByCarrierFragment();
    Fragment fragmentTab2 = new ScheduledFlightsByAirportFragment();
    Fragment fragmentTab3 = new ScheduledFlightsByRouteFragment();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_schedule_tabs,
				container, false);
		
		actionBar = getActivity().getActionBar();
		 
        actionBar.setDisplayShowHomeEnabled(true);


        actionBar.setDisplayShowTitleEnabled(true);


        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        Tab1 = actionBar.newTab().setText("By Carrier");
        Tab2 = actionBar.newTab().setText("By Aiport");
        Tab3 = actionBar.newTab().setText("By Route");
 

        Tab1.setTabListener(new TabListener(fragmentTab1));
        Tab2.setTabListener(new TabListener(fragmentTab2));
        Tab3.setTabListener(new TabListener(fragmentTab3));

        try{
        	actionBar.removeAllTabs();
        }
        catch(Exception e) {
        	Log.e("Error: ", e.getMessage());
        }
        
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
        actionBar.addTab(Tab3);
		
		return parentView;
	}

}
