package com.airportstatus.activities;

/**
 * @author Kent
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.airportstatus.R;
import com.airportstatus.adapters.FIDSAdapter;
import com.airportstatus.entities.Airline;
import com.airportstatus.entities.AirportData;
import com.airportstatus.entities.FIDS;
import com.airportstatus.models.Airlines;
import com.airportstatus.models.Airports;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.todddavies.components.progressbar.ProgressWheel;


public class ShowFIDS extends Activity
{

	AutoCompleteTextView etAirportCode;
	AutoCompleteTextView etAirlineCode;
	Button go;
	String urlAirpC;
	String urlAirlC;

	private static final String BASE_URL = "https://api.flightstats.com/flex/";
	private static final String FIDS_URL = "fids/rest/v1/json/"; // String
																	// created
																	// by KENT
	private static final String appId = "dab73acb";
	private static final String appKey = "c6a876803a6fe7ed2b9f2eccf25415a0";

	ArrayList<HashMap<String, String>> fidsList;
	ArrayList<FIDS> fidsObjList;
	FIDSAdapter adapter;
	JSONArray fids = null;
	TextView msgLoading;
	
	ProgressWheel pw;

	/*
	 * private static RequestParams makeParams() { RequestParams params = new
	 * RequestParams(); params.put("appId", appId); params.put("appKey",
	 * appKey); params.put("codeType", "IATA"); return params; }
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.fids_show);
		setContentView(R.layout.fids_show);
		
		fidsObjList = new ArrayList<FIDS>();

		// Define the AC_TV
		etAirportCode = (AutoCompleteTextView) findViewById(R.id.etAirportCode);
		etAirlineCode = (AutoCompleteTextView) findViewById(R.id.etAirlineCode);
		msgLoading = (TextView) findViewById(R.id.txt_msg_loading);

		//final ProgressDialog dialog = ProgressDialog.show(ShowFIDS.this, "Loading", "Loading the airports");
		pw = (ProgressWheel) findViewById(R.id.progressBar);
		msgLoading.setText("Loading Airports");
		pw.spin();
		// Load the airports
		Airports.getAirportDatas(getApplicationContext(), new Handler(new Callback()
		{

			// On Airports received
			@Override
			public boolean handleMessage(Message msg)
			{
				ArrayList<AirportData> al = (ArrayList<AirportData>) msg.obj;
				// Create the String array to display in the listView
				String[] values = new String[al.size()];
				for (int i = 0; i < values.length; i++)
				{
					values[i] = al.get(i).getName() + " - " + al.get(i).getIcaoCode();
				}

				// Set the list view and Dismiss the dialog after loading
				etAirportCode.setAdapter(new ArrayAdapter<String>(ShowFIDS.this, com.airportstatus.R.layout.single_row_bigger, values));
				pw.stopSpinning();
				pw.setVisibility(View.GONE);
				msgLoading.setText("");

				pw.setVisibility(View.VISIBLE);
				pw.spin();
				msgLoading.setText("Loading Airlines");
				// Load the airlines
				Airlines.getAirlines(ShowFIDS.this, new Handler(new Callback()
				{

					// On Airlines received
					@Override
					public boolean handleMessage(Message msg)
					{

						ArrayList<Airline> al = (ArrayList<Airline>) msg.obj;
						// Create the String array to display in the listView
						String[] airlines = new String[al.size()];
						for (int i = 0; i < airlines.length; i++)
						{
							airlines[i] = al.get(i).getName() + " - " + al.get(i).getIcaoCode();
						}
						// Dismiss the dialog after loading
						pw.stopSpinning();
						pw.setVisibility(View.GONE);
						msgLoading.setText("");

						// Set the list view
						etAirlineCode.setAdapter(new ArrayAdapter<String>(ShowFIDS.this,
								com.airportstatus.R.layout.single_row_bigger,
								airlines));

						return true;
					}
				}));
				return true;
			}
		}));

		go = (Button) findViewById(R.id.btnGo);

		fidsList = new ArrayList<HashMap<String, String>>();

		/**
		 * Button click to start the process of fetching information
		 */
		go.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				RequestQueue rq = Volley.newRequestQueue(ShowFIDS.this);
				urlAirpC = etAirportCode.getText().toString();
				urlAirlC = etAirlineCode.getText().toString();
				pw.spin();

				// < 4 because " - ".length() + 1
				if (urlAirlC.length() < 4 || urlAirpC.length() < 4)
				{
					SuperToast.create(ShowFIDS.this, "Invalid airport or airline name", SuperToast.Duration.LONG, 
						    Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
					return;
				}
				urlAirpC = urlAirpC.substring(urlAirpC.indexOf(" - ") + 3);
				urlAirlC = urlAirlC.substring(urlAirlC.indexOf(" - ") + 3);
				Log.d("test", urlAirlC + " & " + urlAirpC);

				// if the codes are not correct
				if (!urlAirlC.matches("^[A-Z]+$") || !urlAirpC.matches("^[A-Z]+$"))
				{
					SuperToast.create(ShowFIDS.this, "Invalid airport or airline name", SuperToast.Duration.LONG, 
						    Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
					return;
				}
				final String url = getURL(urlAirpC, urlAirlC);
				pw.setVisibility(View.VISIBLE);
				pw.spin();
				msgLoading.setText("Loading...");
				rq.add(new StringRequest(url, new Listener<String>()
				{

					@Override
					public void onResponse(String response)
					{
						Log.d("Response", response);

						if (response != null)
						{
							new getFlights().execute(response);
							
						} else
						{
							Log.i("NAHWORK", "couldnt get data");
						}
						pw.stopSpinning();
						pw.setVisibility(View.GONE);
						msgLoading.setText("");
					}
				}, new ErrorListener()
				{

					@Override
					public void onErrorResponse(VolleyError error)
					{
						pw.stopSpinning();
						pw.setVisibility(View.GONE);
						msgLoading.setText("");
						SuperToast.create(ShowFIDS.this, "HTTP error...", SuperToast.Duration.LONG, 
							    Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();

					}
				}));

			}
		});

		this.adapter = new FIDSAdapter(getApplicationContext(), fidsObjList);

		StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.lvFIDS);
		adapter = new FIDSAdapter(getBaseContext(), fidsObjList);
		stickyList.setAdapter(adapter);

	}

	/**
	 * Does the JSON Parsing on another thread
	 */
	public class getFlights extends AsyncTask<String, Void, Void>
	{

		@Override
		protected void onPreExecute()
		{


			super.onPreExecute();
			
			fidsObjList.clear();
		}

		@Override
		protected Void doInBackground(String... params)
		{

			try
			{
				JSONObject jsonobj = new JSONObject(params[0]);
				fids = jsonobj.getJSONArray("fidsData");

				for (int k = 0; k < fids.length(); k++)
				{

					JSONObject w = fids.getJSONObject(k);
					FIDS fidsObj = new FIDS();
					fidsObj.setAirlineCode(w.getString("airlineCode"));
					fidsObj.setFlightNumber(w.getString("flightNumber"));
					fidsObj.setRemarks(w.getString("remarks"));
					fidsObj.setCity(w.getString("city"));
					fidsObj.setCurrentTime(w.getString("currentTime"));

					fidsObjList.add(fidsObj);
				}

			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
		
	}

	/**
	 * 
	 * @param pAirportCode
	 *            the airportcode the user entered
	 * @param pAirlineCode
	 *            the airline code the user entered
	 * @return the completed url
	 */
	public String getURL(String pAirportCode, String pAirlineCode)
	{
		return BASE_URL + FIDS_URL
				+ pAirportCode
				+ "/departures"
				+ "?appId="
				+ appId
				+ "&appKey="
				+ appKey
				+ "&requestedFields="
				+ "airlineCode%2CflightNumber%2Ccity%2CcurrentTime%2Cgate%2Cremarks"
				+ "&includeAirlines="
				+ pAirlineCode
				+ "&lateMinutes="
				+ "15"
				+ "&useRunwayTimes="
				+ "false"
				+ "&excludeCargoOnlyFlights="
				+ "false"; // + + lateMinute+"&score=3";

	}

}
