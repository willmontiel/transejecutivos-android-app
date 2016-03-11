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

public class FilterServicesFragment extends FragmentBase {
    public FilterServicesFragment() {
        // Required empty public constructor
    }

    public static FilterServicesFragment newInstance() {
        FilterServicesFragment fragment = new FilterServicesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_services, container, false);
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.filter_services_recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(getActivity());
        recycler.setAdapter(adapter);

        initDatePicker(view);

        return view;
    }

    public void initDatePicker(View view) {
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        datePicker.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                setupServicesList(dayOfMonth, month, year);
            }
        });
    }
}
