package com.development.transejecutivos.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.models.User;

public class FilterServicesFragment extends FragmentBase {

    public FilterServicesFragment() {
        // Required empty public constructor
    }

    public static FilterServicesFragment newInstance(User user) {
        FilterServicesFragment fragment = new FilterServicesFragment();
        fragment.setUser(user);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filter_services, container, false);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.filter_services_recycler_view);

        layout = view.findViewById(R.id.filter_services_container);

        progressBar = view.findViewById(R.id.filter_service_progress);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        serviceAdapter = new ServiceAdapter(getActivity());
        recycler.setAdapter(serviceAdapter);


        Button btn_search_service = (Button) view.findViewById(R.id.btn_search_service);

        btn_search_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchService();
            }
        });

        return view;
    }

    public void searchService() {
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);

        String date = String.format("%02d", (datePicker.getMonth() + 1)) + "/" + String.format("%02d", (datePicker.getDayOfMonth())) + "/" + datePicker.getYear();

        Log.d("DATE", date);
        setupServiceList(date);
    }
}
