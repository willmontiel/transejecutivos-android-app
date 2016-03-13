package com.development.transejecutivos.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.models.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TodayServicesFragment extends FragmentBase {
    public TodayServicesFragment() {

    }

    public static TodayServicesFragment newInstance(User user) {
        TodayServicesFragment fragment = new TodayServicesFragment();
        fragment.setUser(user);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_services, container, false);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.today_services_recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(getActivity());
        recycler.setAdapter(adapter);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String today = df.format(c.getTime());

        setupServicesList(today);

        return view;
    }
}
