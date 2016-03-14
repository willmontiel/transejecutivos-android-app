package com.development.transejecutivos.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(getActivity());
        recycler.setAdapter(adapter);

        initDatePicker();

        return view;
    }

    public void initDatePicker() {
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.format("%02d", (monthOfYear+1)) + "/" + dayOfMonth + "/" + year;
                setupServicesList(date);
            }
        });
    }
}
