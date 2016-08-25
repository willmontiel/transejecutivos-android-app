package com.development.transportesejecutivos.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.development.transportesejecutivos.R;
import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.adapters.ServiceAdapter;
import com.development.transportesejecutivos.adapters.ServiceExpandableListAdapter;
import com.development.transportesejecutivos.api_config.ApiConstants;
import com.development.transportesejecutivos.deserializers.ServiceDeserializer;
import com.development.transportesejecutivos.models.Date;
import com.development.transportesejecutivos.models.ServiceData;
import com.development.transportesejecutivos.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by william.montiel on 11/03/2016.
 */
public class FragmentBase extends Fragment {
    private OnFragmentInteractionListener mListener;
    protected static ExpandableListView expandableListView;
    protected static ServiceExpandableListAdapter serviceExpandableListAdapter;
    protected static ServiceAdapter serviceAdapter;
    View view;
    View progressBar;
    View layout;
    User user;
    Context context;

    public void setUser(User user) {
        this.user = user;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null)  {
            savedInstanceState.remove ("android:support:fragments");
        }

        super.onCreate (savedInstanceState);

        setRetainInstance(true);

        return;
    }


    public void setupServiceList(final String date) {
        showProgress(true, layout, progressBar);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ApiConstants.URL_SERVICE,
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
                        showProgress(false, layout, progressBar);
                    }
                }) {

            @Override
            public Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put(JsonKeys.DATE, date);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Authorization", user.getApikey());
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(10),//time out in 10second
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//DEFAULT_MAX_RETRIES = 1;
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(stringRequest);
    }

    public void setupServicesList() {

        showProgress(true, layout, progressBar);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                ApiConstants.URL_SERVICES_GROUPED,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processDataForGroup(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setErrorSnackBar(getResources().getString(R.string.error_general));
                        showProgress(false, layout, progressBar);
                    }
                }) {

            @Override
            public Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Authorization", user.getApikey());
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(10),//time out in 10second
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//DEFAULT_MAX_RETRIES = 1;
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(stringRequest);
    }

    public void processData(String response) {
        showProgress(false, layout, progressBar);
        try {

            JSONObject resObj = new JSONObject(response);
            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
            if (!error) {
                JSONArray servicesData = resObj.getJSONArray(JsonKeys.SERVICES);
                if (servicesData.length() <= 0) {
                    setErrorSnackBar(getResources().getString(R.string.no_services));
                }
                else {
                    ServiceDeserializer serviceDeserializer = new ServiceDeserializer();
                    serviceDeserializer.setResponseJsonArray(servicesData);
                    serviceDeserializer.deserialize();

                    serviceAdapter.addAll(serviceDeserializer.getServicesArrayList(), serviceDeserializer.getDriversArrayList());
                }
            }
            else {
                setErrorSnackBar(getResources().getString(R.string.error_general));
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public void processDataForGroup(String response) {
        showProgress(false, layout, progressBar);
        try {

            JSONObject resObj = new JSONObject(response);
            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
            if (!error) {
                JSONArray servicesData = resObj.getJSONArray(JsonKeys.SERVICES);
                JSONArray datesData = resObj.getJSONArray(JsonKeys.DATES);
                if (servicesData.length() <= 0) {
                    setErrorSnackBar(getResources().getString(R.string.no_services));
                }
                else {
                    ServiceDeserializer serviceDeserializer = new ServiceDeserializer();
                    serviceDeserializer.setDatesJsonArray(datesData);
                    serviceDeserializer.setServicesJsonArray(servicesData);
                    serviceDeserializer.deserializeByGroup();

                    setItems(serviceDeserializer.getServices(), serviceDeserializer.getDatesArray());
                }
            }
            else {
                setErrorSnackBar(getResources().getString(R.string.error_general));
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    // Setting headers and childs to expandable listview
    void setItems(ArrayList<ArrayList<ServiceData>> servicesData, List<Date> dates) {

        // Array list for header
        ArrayList<String> header = new ArrayList<>();

        // Hash map for both header and child
        HashMap<String, ArrayList<ServiceData>> hashMap = new HashMap<>();

        // Adding headers to list
        for (int i = 0; i < dates.size(); i++) {
            header.add(dates.get(i).getDate());
            hashMap.put(header.get(i), servicesData.get(i));
        }

        serviceExpandableListAdapter = new ServiceExpandableListAdapter(getActivity(), header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(serviceExpandableListAdapter);

        setListener();
    }

    // Setting different listeners to expandablelistview
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {

                return false;
            }
        });
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

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show, final View formView, final View progressView) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            formView.setVisibility(show ? View.GONE : View.VISIBLE);
            formView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    formView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            formView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
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
