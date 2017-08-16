package com.development.transportesejecutivos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.development.transportesejecutivos.adapters.AerolineAdapter;
import com.development.transportesejecutivos.adapters.CarTypeAdapter;
import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.api_config.ApiConstants;
import com.development.transportesejecutivos.deserializers.AerolineDeserializer;
import com.development.transportesejecutivos.deserializers.CarTypeDeserializer;
import com.development.transportesejecutivos.misc.DialogCreator;
import com.development.transportesejecutivos.misc.VolleyErrorHandler;
import com.development.transportesejecutivos.models.Aeroline;
import com.development.transportesejecutivos.models.CarType;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RequestserviceActivity extends ActivityBase {
    Spinner carType;
    Spinner aeroline;
    EditText passengers;
    TextView start_date;
    TextView start_time;
    TextView fly;
    TextView observations;
    Button request_service;
    ProgressBar progressBar;
    View form;

    TextInputLayout inputLayoutPassengers;
    TextInputLayout inputLayoutFly;

    String start_city;
    String start_address;
    String end_city;
    String end_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestservice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();

        progressBar = (ProgressBar) findViewById(R.id.request_service_progress);
        form = findViewById(R.id.request_service_form);
        passengers = (EditText) findViewById(R.id.passengers);
        passengers.setText("1");
        start_date = (TextView) findViewById(R.id.start_date);
        start_time = (TextView) findViewById(R.id.start_time);
        fly = (TextView) findViewById(R.id.fly);
        observations = (TextView) findViewById(R.id.observations);
        request_service = (Button) findViewById(R.id.request_service);

        inputLayoutPassengers  = (TextInputLayout) findViewById(R.id.number_layout_passengers);
        inputLayoutFly  = (TextInputLayout) findViewById(R.id.txt_layout_fly);
        inputLayoutFly.setVisibility(View.GONE);

        getAerolines();
        getCarTypes();
        setDateAndTimePicker();
        setListeners();
    }


    protected void setCarTypes(ArrayList<CarType> carTypeArrayList) {
        CarTypeAdapter adapter = new CarTypeAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,carTypeArrayList);
        carType = (Spinner) findViewById(R.id.car_type);
        carType.setAdapter(adapter);
    }

    protected void setAerolínes(ArrayList<Aeroline> aerolineArrayList) {
        AerolineAdapter adapter = new AerolineAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, aerolineArrayList);
        aeroline = (Spinner) findViewById(R.id.aeroline);
        aeroline.setAdapter(adapter);

        aeroline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position != 0) {
                    inputLayoutFly.setVisibility(View.VISIBLE);
                } else {
                    inputLayoutFly.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                inputLayoutFly.setVisibility(View.GONE);
            }

        });
    }

    protected void setDateAndTimePicker() {
        final ActivityBase self = this;

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                DatePickerDialog dpd = new DatePickerDialog(self, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        start_date.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);

                dpd.show();
            }
        });


        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(self, 2, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        start_time.setText(formatTime(hourOfDay) + ":" + formatTime(minute));
                    }
                }, mHour, mMinute, true);

                tpd.show();
            }
        });
    }

    public void setListeners() {
        request_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestService();
            }
        });

        PlaceAutocompleteFragment sourceAddress = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_source_address);
        sourceAddress.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                start_city = place.getName().toString();
                start_address = place.getAddress().toString();
                Log.i("LALA", "Name: " + place.getName());
                Log.i("LALA", "Address: " + place.getAddress());
            }

            @Override
            public void onError(Status status) {
                Log.i("LALA", "An error occurred: " + status);
            }
        });

        PlaceAutocompleteFragment destinyAddress = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_destiny_address);
        destinyAddress.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                end_city = place.getName().toString();
                end_address = place.getAddress().toString();
                Log.i("LALA", "Name: " + place.getName());
                Log.i("LALA", "Address: " + place.getAddress());
            }

            @Override
            public void onError(Status status) {
                Log.i("LALA", "An error occurred: " + status);
            }
        });
    }

    public void getCarTypes() {
        showProgress(true, form, progressBar);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                ApiConstants.URL_GET_CAR_TYPES,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resObj = new JSONObject(response);
                            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
                            if (!error) {
                                JSONArray data = (JSONArray) resObj.get(JsonKeys.DATA);
                                CarTypeDeserializer deserializer = new CarTypeDeserializer();
                                deserializer.setJson(data);
                                deserializer.deserialize();
                                setCarTypes(deserializer.getCarTypeArrayList());
                            }
                            else {
                                setErrorSnackBar(form, getResources().getString(R.string.error_invalid_profile_update));
                            }
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                        showProgress(false, form, progressBar);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setErrorSnackBar(form, getResources().getString(R.string.error_general));
                        showProgress(false, form, progressBar);
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

    public void getAerolines() {
        showProgress(true, form, progressBar);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                ApiConstants.URL_GET_AEROLINES,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resObj = new JSONObject(response);
                            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
                            if (!error) {
                                JSONArray data = (JSONArray) resObj.get(JsonKeys.DATA);
                                AerolineDeserializer deserializer = new AerolineDeserializer();
                                deserializer.setJson(data);
                                deserializer.deserialize();
                                setAerolínes(deserializer.getAerolineArrayList());
                            }
                            else {
                                setErrorSnackBar(form, getResources().getString(R.string.error_invalid_profile_update));
                            }
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                        showProgress(false, form, progressBar);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setErrorSnackBar(form, getResources().getString(R.string.error_general));
                        showProgress(false, form, progressBar);
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

    private void requestService() {
        boolean validate = validateInputField(passengers.getText().toString(), inputLayoutPassengers, passengers, getString(R.string.error_empty_passengers));

        TextView ctTextView = (TextView) carType.getSelectedView();
        final String ct = ctTextView.getText().toString();

        TextView aTextView = (TextView) aeroline.getSelectedView();
        final String aer = aTextView.getText().toString();

        if (validate &&
                validateField(start_address, getString(R.string.error_empty_start_address)) &&
                validateField(end_address, getString(R.string.error_empty_start_address)) &&
                validateField(start_date.getText().toString(), getString(R.string.error_empty_start_date)) &&
                validateField(start_time.getText().toString(), getString(R.string.error_empty_start_time))
                ) {


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    ApiConstants.URL_REQUEST_SERVICE,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("LALA", response);
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyErrorHandler voleyErrorHandler = new VolleyErrorHandler();
                            voleyErrorHandler.setVolleyError(error);
                            voleyErrorHandler.process();
                            String msg = voleyErrorHandler.getMessage();
                            String message = (TextUtils.isEmpty(msg) ? getResources().getString(R.string.server_error) : msg);

                            setErrorSnackBar(form, message);
                            showProgress(false, form, progressBar);
                        }
                    }) {

                @Override
                public Map<String, String> getHeaders() {
                    Map<String,String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/x-www-form-urlencoded");
                    headers.put("Authorization", user.getApikey());
                    return headers;
                }

                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(JsonKeys.REQUEST_SERVICE_CAR_TYPE, ct);
                    params.put(JsonKeys.REQUEST_SERVICE_PASSENGERS, passengers.getText().toString());
                    params.put(JsonKeys.REQUEST_SERVICE_DATE, start_date.getText().toString());
                    params.put(JsonKeys.REQUEST_SERVICE_TIME, start_time.getText().toString());
                    params.put(JsonKeys.REQUEST_SERVICE_START_CITY, start_city);
                    params.put(JsonKeys.REQUEST_SERVICE_START_ADDRESS, start_address);
                    params.put(JsonKeys.REQUEST_SERVICE_END_CITY, end_city);
                    params.put(JsonKeys.REQUEST_SERVICE_END_ADDRESS, end_address);
                    params.put(JsonKeys.REQUEST_SERVICE_AEROLINE, aer);
                    params.put(JsonKeys.REQUEST_SERVICE_FLY, fly.getText().toString());
                    params.put(JsonKeys.REQUEST_SERVICE_OBSERVATIONS, observations.getText().toString());

                    return params;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    (int) TimeUnit.SECONDS.toMillis(10),//time out in 10second
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//DEFAULT_MAX_RETRIES = 1;
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(stringRequest);
        }
    }


    protected boolean validateInputField(String value, TextInputLayout input, EditText editText, String message) {
        if (TextUtils.isEmpty(value)) {
            input.setError(message);
            editText.requestFocus();
            return false;
        }

        return true;
    }

    protected boolean validateField(String value, String message) {
        if (TextUtils.isEmpty(value)) {
            DialogCreator dialogCreator = new DialogCreator(this);
            dialogCreator.createCustomDialog(message, getString(R.string.accept_button));
            return false;
        }

        return true;
    }

    private String formatTime(int number) {
        String h;
        if (number < 10) {
            h = "0" + number;
        }
        else {
            h = "" + number;
        }

        return h;
    }
}
