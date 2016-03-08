package com.development.transejecutivos.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.deserializers.ServiceDeserializer;
import com.development.transejecutivos.models.Service;

import org.json.JSONObject;

import java.util.ArrayList;

public class TodayServicesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ServiceAdapter adapter;

    public TodayServicesFragment() {

    }

    public static TodayServicesFragment newInstance() {
        TodayServicesFragment fragment = new TodayServicesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Log.d("TS Fragment", "before create view");

        setupServicesList();

        Log.d("TS Fragment", "After create view");

        return view;
    }


    protected void setupServicesList() {
            //ServiceDeserializer serviceDeserializer = new ServiceDeserializer(response);

            ArrayList<Service> data = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Service service =  new Service(i, "ref" + i, "date" + i, "sdate" + i, "edate" + i, "fly" + i, "aeroline" + i, "company" + i, "ptype" + i, "pxcant" + i, "represent" + i, "source" + i, "destiny" + i, "obs" + i);
                data.add(service);
            }

            adapter.addAll(data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
