package com.airportstatus.activities;

/**
 * @author Kent
 * 
 */

//import android.R;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.airportstatus.R;
import com.airportstatus.helpers.NetworkTask;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.RequestParams;

public class ShowFIDS extends Activity {

	/*
	 * String[] ac = new String[] { "apple", "orange", "grape" }; String[] fn =
	 * new String[] { "1", "2", "3" }; String[] status = new String[] { "ok",
	 * "uhhuh", "nah" }; String[] city = new String[] { "here", "there",
	 * "anywhere" }; String[] time = new String[] { "now", "later", "never" };
	 */
	EditText etAirportCode;
	EditText etAirlineCode;
	Button go;
	String urlAirpC;
	String urlAirlC;

	// private String airportCode = "boom";
	// private NetworkTask fidsTask;
	private static final String BASE_URL = "https://api.flightstats.com/flex/";
	private static final String FIDS_URL = "fids/rest/v1/json/"; // String
																	// created
																	// by KENT
	private static final String appId = "dab73acb";
	private static final String appKey = "c6a876803a6fe7ed2b9f2eccf25415a0";

	ArrayList<HashMap<String, String>> fidsList;
	JSONArray fids = null;

	/*
	 * private static RequestParams makeParams() { RequestParams params = new
	 * RequestParams(); params.put("appId", appId); params.put("appKey",
	 * appKey); params.put("codeType", "IATA"); return params; }
	 */
	// BA, AA, SN, EI
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.fids_show);
		setContentView(R.layout.fids_show);

		etAirportCode = (EditText) findViewById(R.id.etAirportCode);
		etAirlineCode = (EditText) findViewById(R.id.etAirlineCode);
		go = (Button) findViewById(R.id.btnGo);

		fidsList = new ArrayList<HashMap<String, String>>();

		/**
		 * Button click to start the process of fetching information
		 */
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RequestQueue rq = Volley.newRequestQueue(ShowFIDS.this);
				urlAirpC = etAirportCode.getText().toString();
				urlAirlC = etAirlineCode.getText().toString();
				// Log.i("ET1", urlAirpC);
				// Log.i("ET2", urlAirlC);
				final String url = getURL(urlAirpC, urlAirlC);
				// Log.i("FIDS", url);
				// JsonObjectRequest jor = new JsonObjectRequest(url,
				// jsonRequest,
				// listener, errorListener)

				// final ProgressDialog pDialog =
				// ProgressDialog.show(getBaseContext(),
				// "Loading", "Loading the rating");
				// Execute the request
				rq.add(new StringRequest(url, new Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.d("Response", response);

						if (response != null) {
							new getFlights().execute(response);
						} else {
							Log.i("NAHWORK", "couldnt get data");
						}

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO
						// Handle your error

					}
				}));

			}
		});

		/*
		 * ArrayList<HashMap<String,String>> fidders = new
		 * ArrayList<HashMap<String,String>>();
		 * 
		 * for (int i= 0; i < 3 ; i++) { HashMap<String,String> putter = new
		 * HashMap<String,String>(); putter.put("ac", ac[i]); putter.put("fn",
		 * fn[i]); putter.put("status", status[i]); putter.put("city", city[i]);
		 * putter.put("time", time[i]); fidders.add(putter); }
		 */

		String[] from = { "ac", "fn", "status", "city", "time" };

		int[] to = { R.id.tvFIDSAC, R.id.tvFIDSFN, R.id.tvFIDSStatus,
				R.id.tvFIDSCity, R.id.tvFIDSTime };

		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), fidsList,
				R.layout.fids_layout, from, to);

		ListView lview = (ListView) findViewById(R.id.lvFIDS);
		lview.setAdapter(adapter);

	}

	/**
	 * Does the JSON Parsing on another thread
	 */
	public class getFlights extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

			super.onPreExecute();
			fidsList.clear();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub

			try {
				JSONObject jsonobj = new JSONObject(params[0]);
				fids = jsonobj.getJSONArray("fidsData");

				for (int k = 0; k < fids.length(); k++) {
					JSONObject w = fids.getJSONObject(k);
					String ac = w.getString("airlineCode");
					String fn = w.getString("flightNumber");
					String rem = w.getString("remarks");
					String cy = w.getString("city");
					String ct = w.getString("currentTime");

					/*
					 * Log.i("FID1", ac); Log.i("FID2", fn); Log.i("FID3", rem);
					 * Log.i("FID4", cy); Log.i("FID5", ct);
					 */
					HashMap<String, String> unofid = new HashMap<String, String>();

					unofid.put("ac", ac);
					unofid.put("fn", fn);
					unofid.put("status", rem);
					unofid.put("city", cy);
					unofid.put("time", ct);
					fidsList.add(unofid);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
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
	public String getURL(String pAirportCode, String pAirlineCode) {
		return BASE_URL
				+ FIDS_URL
				+ pAirportCode
				+ "/departures"
				+ "?appId="
				+ appId
				+ "&appKey="
				+ appKey
				+ "&requestedFields="
				+ "airlineCode%2CflightNumber%2Ccity%2CcurrentTime%2Cgate%2Cremarks"
				+ "&includeAirlines=" + pAirlineCode + "&lateMinutes=" + "15"
				+ "&useRunwayTimes=" + "false" + "&excludeCargoOnlyFlights="
				+ "false"; // + + lateMinute+"&score=3";

		// fidsRequestParams.put("requestedFields",
		// "airlineCode,flightNumber,city,currentTime,gate,remarks");
		// fidsRequestParams.put("includeAirlines", "BA");
		// fidsRequestParams.put("lateMinutes", "15");
		// fidsRequestParams.put("useRunwayTimes", "false");
		// fidsRequestParams.put("excludeCargoOnlyFlights", "false");
	}

}
