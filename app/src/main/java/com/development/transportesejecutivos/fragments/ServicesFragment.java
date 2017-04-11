package com.development.transportesejecutivos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.development.transportesejecutivos.R;
import com.development.transportesejecutivos.models.User;

public class ServicesFragment extends FragmentBase {
    public ServicesFragment() {

    }

    public static ServicesFragment newInstance(User user) {
        ServicesFragment fragment = new ServicesFragment();
        fragment.setUser(user);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_services, container, false);

        layout = view.findViewById(R.id.service_expandable_listview);

        //expandableListView = (ExpandableListView) view.findViewById(R.id.service_expandable_listview);

        // Setting group indicator null for custom indicator
        //expandableListView.setGroupIndicator(null);


        progressBar = view.findViewById(R.id.service_progress);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupServicesList();
    }
}
