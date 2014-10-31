package com.airportstatus.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.airportstatus.entities.AirportData;
import com.airportstatus.interfaces.FlightStatsClient;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Airports
{
	private static ArrayList<AirportData> airports;
	public static final String AIRLINES_URL = "airports/rest/v1/json/active";

	private static void downloadAirportDatas(final Context context, final Handler callback)
	{
		RequestQueue rq = Volley.newRequestQueue(context);

		// Create the URL using the keys in FlightStatsClient
		String url = FlightStatsClient.BASE_URL + AIRLINES_URL
				+ "?appId="
				+ FlightStatsClient.appId
				+ "&appKey="
				+ FlightStatsClient.appKey
				+ "";

		// Execute the request
		rq.add(new StringRequest(url, new Listener<String>()
		{

			@Override
			public void onResponse(String response)
			{
				airports = new ArrayList<AirportData>();
				try
				{

					// Parse the data (airlines name) and put them in the
					// ArrayList
					JSONArray jarr = new JSONObject(response).getJSONArray("airports");
					JSONObject jobj;
					for (int i = 0; i < jarr.length(); i++)
					{
						jobj = (JSONObject) jarr.get(i);
						AirportData tmp = new AirportData(jobj);
						if (tmp.getName() != null && tmp.getFsCode() != null)
							airports.add(tmp);
					}

					// Sort by alphabetic order
					Collections.sort(airports, new Comparator<AirportData>()
					{

						@Override
						public int compare(AirportData lhs, AirportData rhs)
						{
							return lhs.getName().compareTo(rhs.getName());
						}

					});
					
					sendMessage(callback);

				} catch (JSONException e)
				{
					Log.e("AirportDatas", "error on parsing the JSON: " + e.getMessage());
					Log.e("AirportDatas", "error on parsing the JSON: " + response);
					e.printStackTrace();
				}

			}
		}, new ErrorListener()
		{

			@Override
			public void onErrorResponse(VolleyError error)
			{
				Toast.makeText(context, "Error: "+error.getMessage(), Toast.LENGTH_LONG);
			}
		}));
	}

	/**
	 * Load the data from the network if necessary Call the Handler when it can
	 * send the airlines
	 * 
	 * @param context
	 *            The app context, used to download with Volley
	 * @param callback
	 *            Send a message that contain as message.obj an ArrayList<{@link Airports}>
	 * */
	public static void getAirportDatas(Context context, Handler callback)
	{
		if (airports == null)
		{
			downloadAirportDatas(context, callback);
		} else
		{
			sendMessage(callback);
		}
	}

	private static void sendMessage(Handler callback)
	{
		Message m = new Message();
		m.obj = airports;
		callback.sendMessage(m);
	}

}
