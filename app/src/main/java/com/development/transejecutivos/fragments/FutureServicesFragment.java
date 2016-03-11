package com.development.transejecutivos.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;

import java.util.ArrayList;

public class FutureServicesFragment extends FragmentBase {

    public FutureServicesFragment() {
        // Required empty public constructor
    }

    public static FutureServicesFragment newInstance() {
        FutureServicesFragment fragment = new FutureServicesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future_services, container, false);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.future_services_recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(getActivity());
        recycler.setAdapter(adapter);

        setupServicesList(1,2 ,3);

        return view;
    }
}
