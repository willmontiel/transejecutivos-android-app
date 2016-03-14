package com.development.transejecutivos.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.models.User;

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
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.services_recycler_view);
        layout =  view.findViewById(R.id.services_recycler_view);

        progressBar = view.findViewById(R.id.service_progress);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(getActivity());
        recycler.setAdapter(adapter);

        setupServicesList();

        return view;
    }
}
