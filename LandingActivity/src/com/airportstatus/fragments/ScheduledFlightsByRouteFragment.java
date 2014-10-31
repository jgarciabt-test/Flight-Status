package com.airportstatus.fragments;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.airportstatus.R;
import com.airportstatus.interfaces.FlightStatsClient;
import com.airportstatus.models.ScheduleRouteModel;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class ScheduledFlightsByRouteFragment extends Fragment {

	private View parentView;

	Button btnClear, btnSearch;
	EditText txtRouteDeptCode, txtRouteArrCode, txtDepartureDate;
	Spinner _spinnerFlightType;
	private String _jsonURL = null;
	private RequestQueue mRequestQueue;
	private boolean checkMe = true;
	private ArrayList<ScheduleRouteModel> routeList = new ArrayList<ScheduleRouteModel>();
	private ScheduleRouteModel newRoute = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_schedule_route_form,
				container, false);

		initializeView();

		btnClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearFields();
			}
		});

		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				try {

					_jsonURL = getUrlRequest();

					getRouteScheduleJson();

				} catch (Exception e) {
					Toast.makeText(getActivity(),
							"Error Occured:" + e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		return parentView;
	}

	private void initializeView() {
		txtRouteDeptCode = (EditText) parentView
				.findViewById(R.id.routeDepartureCode);
		txtRouteArrCode = (EditText) parentView
				.findViewById(R.id.routeArrivalCode);
		txtDepartureDate = (EditText) parentView.findViewById(R.id.routeDate);
		txtDepartureDate.setText(FlightStatsClient.getDate());
		_spinnerFlightType = (Spinner) parentView
				.findViewById(R.id.rFlightSpinner);

		btnClear = (Button) parentView.findViewById(R.id.rClearBtn);
		btnSearch = (Button) parentView.findViewById(R.id.rSearchBtn);
	}

	private void clearFields() {
		txtRouteDeptCode.setText("");
		txtRouteArrCode.setText("");
		txtDepartureDate.setText("");
	}

	private String _setUrlRequest(String _routeDeptCode, String _routeArrCode,
			String _departureDate, String _flightType) {

		StringTokenizer st = new StringTokenizer(_departureDate, "-");
		int count = 0;
		String day = "", month = "", year = "";
		while (st.hasMoreTokens()) {
			if (count == 0) {
				day = st.nextToken();
			} else if (count == 1) {
				month = st.nextToken();
			} else {
				year = st.nextToken();
			}
			count++;
		}

		String _baseURL = FlightStatsClient.BASE_URL + "schedules/";
		String _apiID = FlightStatsClient.get_apiID();
		String _apiKEY = FlightStatsClient.get_apiKEY();

		String _composedURL = _baseURL + "rest/v1/json/from/";
		_composedURL = _composedURL + _routeDeptCode + "/to/" + _routeArrCode
				+ "/";
		_composedURL = _composedURL + _flightType + "/";
		_composedURL = _composedURL + year + "/";
		_composedURL = _composedURL + month + "/";
		_composedURL = _composedURL + day;
		_composedURL = _composedURL + "?appId=" + _apiID + "&";
		_composedURL = _composedURL + "appKey=" + _apiKEY;

		System.out.println(_composedURL);

		return _composedURL;
	}

	private String getUrlRequest() {
		String routeDeptCode = txtRouteDeptCode.getText().toString();
		String routeArrCode = txtRouteArrCode.getText().toString();
		String date = txtDepartureDate.getText().toString();

		String flightType = _spinnerFlightType.getSelectedItem().toString()
				.toLowerCase();
		String URL = _setUrlRequest(routeDeptCode, routeArrCode, date,
				flightType);
		return URL;
	}

	protected void getRouteScheduleJson() {

		final ProgressDialog dialog = ProgressDialog.show(getActivity(),
				"Loading", "Getting Schedule ... ");
		checkMe = true;
		mRequestQueue = Volley.newRequestQueue(getActivity());

		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				_jsonURL, null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							JSONArray jsonArr = response
									.getJSONArray("scheduledFlights");

							for (int i = 0; i < jsonArr.length(); i++) {

								checkMe = false;

								newRoute = new ScheduleRouteModel();

								JSONObject obj = jsonArr.getJSONObject(i);


								String var1 = obj.getString("carrierFsCode");
								String var2 = obj.getString("flightNumber");
								
								String var21 = obj.getString("departureAirportFsCode");
								String var22 = obj.getString("arrivalAirportFsCode");
								
								String var3 = obj.getString("stops");
								String var4 = obj.getString("departureTime");
								String var5 = obj.getString("arrivalTime");
								String var6 = obj
										.getString("flightEquipmentIataCode");
								String var7 = obj.getString("isCodeshare");
								String var8 = obj.getString("isWetlease");
								String var9 = obj.getString("serviceType");
								String var10 = obj.getString("referenceCode");

								newRoute.setRouteCarrierFsCode(var1);
								newRoute.setRouteFlightNumber(var2);
								newRoute.setRouteDepartureFsCode(var21);
								newRoute.setRouteArrivalFsCode(var22);
								newRoute.setRouteNumberOfStops(var3);
								newRoute.setRouteDepartureTime(var4);
								newRoute.setRouteArrivalTime(var5);
								newRoute.setRouteFlightEquipmentIataCode(var6);
								newRoute.setRouteIsCodeShare(var7);
								newRoute.setRouteIsWetlease(var8);
								newRoute.setRouteServiceType(var9);
								newRoute.setRouteReferenceCode(var10);

								routeList.add(newRoute);
							}
							dialog.dismiss();
							if (checkMe) {
								Toast.makeText(
										getActivity(),
										"Sorry there are no Flight Schedules with such Parameters!",
										Toast.LENGTH_SHORT).show();
							} else {
								FlightStatsClient
										.setRouteScheduleList(routeList);

								ViewRouteScheduleFlightsFragment viewScheduleRouteFragment = new ViewRouteScheduleFlightsFragment();

								getFragmentManager()
										.beginTransaction()
										.replace(
												R.id.flight_information_layout,
												viewScheduleRouteFragment)
										.commit();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						dialog.dismiss();
						Toast.makeText(getActivity(),
								"ERROR: " + error.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
				});
		mRequestQueue.add(jsonObjReq);

	}
}