package com.development.transportesejecutivos;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ServicerequestActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicerequest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();

        setFragment();
    }

    protected void setFragment() {
        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ServiceRequestPart1Fragment serviceRequestPart1Fragment = new ServiceRequestPart1Fragment();
        ServiceRequestPart1Fragment.setContext(getApplicationContext());
        serviceRequestPart1Fragment.setUser(user);
        fragmentTransaction.add(R.id.fragment_container, serviceRequestPart1Fragment, "Service Request Fragment Part 1");
        fragmentTransaction.commit();
        */
    }
}

