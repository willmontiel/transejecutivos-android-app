package com.development.transportesejecutivos.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.development.transportesejecutivos.R;
import com.development.transportesejecutivos.ServicerequestActivity;
import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.misc.PlaceArrayAdapter;
import com.development.transportesejecutivos.models.User;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;


public class DestinyFragment extends FragmentBase implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{
    private static final String LOG_TAG = "LALA";
    private AutoCompleteTextView mAutocompleteTextView;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;

    private Button button_next;

    private String placeLatLng = null;
    private String placeName = null;
    private String placeAddress = null;
    private String placeId = null;

    private OnFragmentInteractionListener mListener;

    public DestinyFragment() {

    }

    public static DestinyFragment newInstance(Context context, User user, FragmentTransaction fragmentTransaction) {
        DestinyFragment fragment = new DestinyFragment();
        fragment.setUser(user);
        fragment.setContext(context);
        fragment.setFragmentTransaction(fragmentTransaction);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkGooglePlayServices();
        buildGoogleApiClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_destiny, container, false);

        mAutocompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTxtView_places);
        mAutocompleteTextView.setThreshold(3);
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this.context, android.R.layout.simple_list_item_1, null, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);
getFragmentManager().beginTransaction();
        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setEnabled(false);
        button_next.setBackgroundColor(getResources().getColor(R.color.colorSecondaryText));
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    public synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                    .addApi(Places.GEO_DATA_API)
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

            placeLatLng = place.getLatLng() + "";
            placeName = place.getName().toString();
            placeAddress = place.getAddress().toString();
            placeId = place.getId().toString();


            button_next.setEnabled(true);
            button_next.setBackgroundColor(getResources().getColor(R.color.green));
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: " + connectionResult.getErrorCode());
        Toast.makeText(context, "Google Places API connection failed with error code:" + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }

    private void next() {
        if (TextUtils.isEmpty(placeLatLng) || TextUtils.isEmpty(placeName) || TextUtils.isEmpty(placeAddress) || TextUtils.isEmpty(placeId)) {
            //setErrorSnackBar(view.findViewById(R.id.scroll_view), "Por favor selecciona el destino");
        }
        else {
            /*
            Intent i = new Intent(getActivity().getApplicationContext(), ServicerequestActivity.class);

            i.putExtra(JsonKeys.REQUEST_SERVICE_LATLNG, placeLatLng);
            i.putExtra(JsonKeys.REQUEST_SERVICE_NAME, placeName);
            i.putExtra(JsonKeys.REQUEST_SERVICE_ADDRESS, placeAddress);
            i.putExtra(JsonKeys.REQUEST_SERVICE_ID, placeId);


            startActivity(i);
            getActivity().finish();
            */
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
}
