package com.development.transportesejecutivos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Toast;

import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.fragments.RequestServiceFragment;

public class ServicerequestActivity extends ActivityBase {
    private String placeLatLng = null;
    private String placeName = null;
    private String placeAddress = null;
    private String placeId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicerequest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();

        Bundle b = getIntent().getExtras();
        placeLatLng = b.getString(JsonKeys.REQUEST_SERVICE_LATLNG);
        placeName = b.getString(JsonKeys.REQUEST_SERVICE_NAME);
        placeAddress = b.getString(JsonKeys.REQUEST_SERVICE_ADDRESS);
        placeId = b.getString(JsonKeys.REQUEST_SERVICE_ID);


        if (TextUtils.isEmpty(placeLatLng) || TextUtils.isEmpty(placeName) || TextUtils.isEmpty(placeAddress) || TextUtils.isEmpty(placeId)) {
            Toast.makeText(this, getResources().getString(R.string.error_empty_destiny), Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), DestinyActivity.class);
            startActivity(i);
            finish();
        }

        setFragment();
    }

    protected void setFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RequestServiceFragment requestServiceFragment = RequestServiceFragment.newInstance(placeLatLng, placeName, placeAddress, placeId);
        requestServiceFragment.setContext(getApplicationContext());
        requestServiceFragment.setUser(user);
        fragmentTransaction.add(R.id.fragment_container, requestServiceFragment, "Service Request Fragment Part 1");
        fragmentTransaction.commit();
    }
}

