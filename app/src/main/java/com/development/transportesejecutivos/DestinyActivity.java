package com.development.transportesejecutivos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.misc.PlaceArrayAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;


public class DestinyActivity extends ActivityBase implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    private static final String LOG_TAG = "LALA";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private AutoCompleteTextView mAutocompleteTextView;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;

    private Button button_next;

    private String placeLatLng = null;
    private String placeName = null;
    private String placeAddress = null;
    private String placeId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny);

        validateSession();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkGooglePlayServices();
        buildGoogleApiClient();

        mAutocompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTxtView_places);
        mAutocompleteTextView.setThreshold(3);
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1, null, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);

        button_next = (Button) findViewById(R.id.button_next);
        button_next.setEnabled(false);
        button_next.setBackgroundColor(getResources().getColor(R.color.colorSecondaryText));
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(DestinyActivity.this)
                    .addApi(Places.GEO_DATA_API)
                    .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                    .addConnectionCallbacks(this)
                    .build();
        }
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                return;
            }
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();

            placeLatLng = place.getLatLng() + "";
            placeName = place.getName().toString();
            placeAddress = place.getAddress().toString();
            placeId = place.getId().toString();


            button_next.setEnabled(true);
            button_next.setBackgroundColor(getResources().getColor(R.color.green));

            /*
            mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            }
            */
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: " + connectionResult.getErrorCode());
        Toast.makeText(this, "Google Places API connection failed with error code:" + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }

    private void next() {
        if (TextUtils.isEmpty(placeLatLng) || TextUtils.isEmpty(placeName) || TextUtils.isEmpty(placeAddress) || TextUtils.isEmpty(placeId)) {
            setErrorSnackBar(findViewById(R.id.scroll_view), "Por favor selecciona el destino");
        }
        else {
            Intent i = new Intent(getApplicationContext(), ServicerequestActivity.class);

            i.putExtra(JsonKeys.REQUEST_SERVICE_LATLNG, placeLatLng);
            i.putExtra(JsonKeys.REQUEST_SERVICE_NAME, placeName);
            i.putExtra(JsonKeys.REQUEST_SERVICE_ADDRESS, placeAddress);
            i.putExtra(JsonKeys.REQUEST_SERVICE_ID, placeId);


            startActivity(i);
            finish();
        }
    }
}