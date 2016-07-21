package com.development.transportesejecutivos;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.api_config.ApiConstants;
import com.development.transportesejecutivos.misc.UserSessionManager;
import com.development.transportesejecutivos.models.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverlocationActivity extends ActionBarActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    View container;
    UserSessionManager session;
    User user;
    int idService = 0;

    protected GoogleApiClient mGoogleApiClient = null;
    protected Location mLastLocation;
    protected LocationRequest mLocationRequest = null;
    protected boolean mRequestingLocationUpdates = false;
    protected final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    protected static int UPDATE_INTERVAL = 10000; // 10 sec
    protected static int FATEST_INTERVAL = 5000; // 5 sec
    protected static int DISPLACEMENT = 10; // 10 meters
    Marker marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlocation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();

        if (checkPlayServices()) {
            buildGoogleApiClient();
            createLocationRequest();
        }

        Bundle t = getIntent().getExtras();
        if (t != null) {
            idService = t.getInt(JsonKeys.SERVICE_ID);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        container = findViewById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().isMyLocationButtonEnabled();
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setTrafficEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient.isConnected() && mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public void setMap() {
        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {

                LatLng userLocation = new LatLng( mLastLocation.getLatitude(),  mLastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userLocation).title(getResources().getString(R.string.title_user_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

                refreshMap();
            }
            else {
                mGoogleApiClient.connect();
            }
        }
        catch (SecurityException e) {

        }
    }


    private void getDriverLocation() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                ApiConstants.URL_GET_PRELOCATION + "/" + idService,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resObj = new JSONObject(response);
                            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
                            if (!error) {
                                JSONObject loc = (JSONObject) resObj.get(JsonKeys.DRIVER_LOCATION);

                                int l = loc.getInt(JsonKeys.DRIVER_LOCATION);

                                if (l == 1) {
                                    double lat = loc.getDouble(JsonKeys.DRIVER_LOCATION_LATITUDE);
                                    double lon = loc.getDouble(JsonKeys.DRIVER_LOCATION_LONGITUDE);
                                    LatLng driverLocation = new LatLng(lat, lon);
                                    moveDriverMarker(driverLocation);
                                }
                            }
                            else {
                                setErrorSnackBar(container, getResources().getString(R.string.error_general));
                            }
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setErrorSnackBar(container, getResources().getString(R.string.error_general));
                    }
                }) {

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

    public void moveDriverMarker(LatLng driverLocation) {
        if (this.marker == null) {
            this.marker = mMap.addMarker(new MarkerOptions().position(driverLocation).title(getResources().getString(R.string.title_driver_location)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        }

        this.marker.setPosition(driverLocation);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(driverLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(driverLocation, 16));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
    }

    public void refreshMap() {
        final Handler h = new Handler();
        final int delay = 5000; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                getDriverLocation();
                h.postDelayed(this, delay);
            }
        }, delay);
    }


    public void setErrorSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);

        snackbar.show();

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorError));
        TextView txtv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER);
    }

    public void validateSession() {
        session = new UserSessionManager(getApplicationContext());

        if(session.checkLogin()) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }

        user = session.getUserDetails();
    }

    public boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    public synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    /**
     * Creating location request object
     * */
    protected void createLocationRequest() {
        if (mLocationRequest == null) {
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(UPDATE_INTERVAL);
            mLocationRequest.setFastestInterval(FATEST_INTERVAL);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setSmallestDisplacement(DISPLACEMENT); // 10 meters
        }
    }

    /**
     * Starting the location updates
     * */
    protected void startLocationUpdates() {
        try {
            if (mGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }
        catch (SecurityException e) {

        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        onConnectedBundle(connectionHint);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), R.string.error_general,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        // Assign the new location
        mLastLocation = location;
        // Displaying the new location on UI
        setMap();
    }

    public void onConnectedBundle(Bundle connectionHint) {
        // Once connected with google api, get the location
        setMap();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }
}
