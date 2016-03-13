package com.development.transejecutivos.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.JsonKeys;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.api_config.ApiConstants;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by william.montiel on 11/03/2016.
 */
public class FragmentBase extends Fragment {
    private OnFragmentInteractionListener mListener;
    ServiceAdapter adapter;
    View view;
    User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setupServicesList(final String date) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ApiConstants.URL_SERVICES,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processData(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setErrorSnackBar(getResources().getString(R.string.error_general));
                    }
                }) {

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("Authorization", user.getApikey());
                params.put(JsonKeys.DATE, date);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void processData(String response) {
        ArrayList<Service> services = new ArrayList<>();
        ArrayList<Passenger> passengers = new ArrayList<>();
        ArrayList<Driver> drivers = new ArrayList<>();

        try {
            JSONObject resObj = new JSONObject(response);
            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
            if (!error) {
                for (int i = 0; i < 20; i++) {
                    Service service =  new Service(i, "ref" + i, "date" + i, "sdate" + i, "edate" + i, "fly" + i, "aeroline" + i, "company" + i, "ptype" + i, "pxcant" + i, "represent" + i, "source" + i, "destiny" + i, "obs" + i);
                    Passenger passenger = new Passenger(i, "code" + i, "Name" + i, "lastName" + i, "company" + i, "phone" + i, "email" + i, "address" + i, "city" + i);
                    Driver driver = new Driver(i, "code" + i, "name" + i, "lastName" + i, "phone" + i, "address" + i, "city" + i, "email" + i, "carType" + i, "carBrand" + i, "carModel" + i, "carColor" + i, "placa" + i, "status" + i);

                    passengers.add(passenger);
                    services.add(service);
                    drivers.add(driver);
                }

                adapter.addAll(services, passengers, drivers);
            }
            else {
                setErrorSnackBar(getResources().getString(R.string.error_invalid_login));
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public void setErrorSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);

        snackbar.show();

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorError));
        TextView txtv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
