package com.development.transejecutivos.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.adapters.ServiceExpandableListAdapter;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        expandableListView = (ExpandableListView) view.findViewById(R.id.service_expandable_listview);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);


        progressBar = view.findViewById(R.id.service_progress);

        setupServicesList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupServicesList();
    }
}
