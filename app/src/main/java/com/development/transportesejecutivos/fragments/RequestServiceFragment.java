package com.development.transportesejecutivos.fragments;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.development.transportesejecutivos.R;
import com.development.transportesejecutivos.adapters.CarTypeAdapter;
import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.misc.DateDialog;
import com.development.transportesejecutivos.misc.PlaceArrayAdapter;
import com.development.transportesejecutivos.misc.TimeDialog;
import com.development.transportesejecutivos.models.CarType;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestServiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestServiceFragment extends FragmentBase implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{
    private static final String LOG_TAG = "LALA";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private AutoCompleteTextView mAutocompleteTextView;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;

    private Button button_request_service;

    private String sourcePlaceLatLng = null;
    private String sourcePlaceName = null;
    private String sourcePlaceAddress = null;
    private String sourcePlaceId = null;


    private String placeLatLng = null;
    private String placeName = null;
    private String placeAddress = null;
    private String placeId = null;

    TextView txt_place_address;
    TextView txt_place_name;
    Spinner spnn_car_type;
    EditText edittxt_date;
    EditText edittxt_time;

    private OnFragmentInteractionListener mListener;

    public RequestServiceFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param placeLatLng
     * @param placeName
     * @param placeAddress
     * @param placeId
     * @return
     */
    // TODO: Rename and change types and number of parameters
    public static RequestServiceFragment newInstance(String placeLatLng, String placeName, String placeAddress, String placeId) {
        RequestServiceFragment fragment = new RequestServiceFragment();
        Bundle args = new Bundle();
        args.putString(JsonKeys.REQUEST_SERVICE_LATLNG, placeLatLng);
        args.putString(JsonKeys.REQUEST_SERVICE_NAME, placeName);
        args.putString(JsonKeys.REQUEST_SERVICE_ADDRESS, placeAddress);
        args.putString(JsonKeys.REQUEST_SERVICE_ID, placeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            placeName = getArguments().getString(JsonKeys.REQUEST_SERVICE_NAME);
            placeAddress = getArguments().getString(JsonKeys.REQUEST_SERVICE_ADDRESS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_request, container, false);

        txt_place_address = (TextView) view.findViewById(R.id.txt_place_address);
        txt_place_name = (TextView) view.findViewById(R.id.txt_place_name);
        edittxt_date = (EditText) view.findViewById(R.id.edittxt_date);
        edittxt_time = (EditText) view.findViewById(R.id.edittxt_time);

        setTxtData();

        ArrayList<CarType> arrayList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            CarType carType = new CarType();
            carType.setIdCarType(i);
            carType.setName("Tipo de carro " + i);
            arrayList.add(carType);
        }

        setDataOnSpinners(arrayList);

        buildGoogleApiClient();

        mAutocompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTxtView_places);
        mAutocompleteTextView.setThreshold(3);
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(context, android.R.layout.simple_list_item_1, null, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);

        button_request_service = (Button) view.findViewById(R.id.button_request_service);
        button_request_service.setEnabled(false);
        button_request_service.setBackgroundColor(getResources().getColor(R.color.colorSecondaryText));

        return view;
    }

    public synchronized void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
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


            button_request_service.setEnabled(true);
            button_request_service.setBackgroundColor(getResources().getColor(R.color.green));
        }
    };

    public void setTxtData() {
        txt_place_address.setText(placeAddress);
        txt_place_name.setText(placeName);

        edittxt_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    //DateDialog dialog = new DateDialog(edittxt_date);
                    //dialog.show(getFragmentManager(), "date_picker");
                    //edittxt_date.clearFocus();
                }
            }
        });

        edittxt_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    //TimeDialog dialog = new TimeDialog(edittxt_time);
                    //dialog.show(getFragmentManager(), "time_picker");
                    //edittxt_date.clearFocus();
                }
            }
        });
    }

    private void setDataOnSpinners(ArrayList<CarType> clients) {
        CarTypeAdapter carTypeAdapter = new CarTypeAdapter(this.context, android.R.layout.simple_dropdown_item_1line, clients);
        spnn_car_type = (Spinner) view.findViewById(R.id.spnn_car_type);
        spnn_car_type.setAdapter(carTypeAdapter);

        /*
        spnn_car_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                CarType carType = (CarType) arg0.getItemAtPosition(arg2);
                Toast.makeText(context, carType.getName() + ", was selected", Toast.LENGTH_LONG).show();
            }
        });
        */
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
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
