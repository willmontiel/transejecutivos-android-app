package com.development.transejecutivos.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.development.transejecutivos.adapters.ServiceAdapter;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;
import java.util.ArrayList;

/**
 * Created by william.montiel on 11/03/2016.
 */
public class FragmentBase extends Fragment {
    private OnFragmentInteractionListener mListener;
    ServiceAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setupServicesList(int day, int month, int year) {
        ArrayList<Service> services = new ArrayList<>();
        ArrayList<Passenger> passengers = new ArrayList<>();
        ArrayList<Driver> drivers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Service service =  new Service(i, "ref" + i, "date" + i, "sdate" + i, "edate" + i, "fly" + i, "aeroline" + i, "company" + i, "ptype" + i, "pxcant" + i, "represent" + i, "source" + i, "destiny" + i, "obs" + i);
            Passenger passenger = new Passenger(i, "code" + i, "Name" + i, "lastName" + i, "company" + i, "phone" + i, "email" + i, "address" + i, "city" + i);
            Driver driver = new Driver(i, "code" + i, "name" + i, "lastName" + i, "phone" + i, "address" + i, "city" + i, "email" + i, "carType" + i, "carBrand" + i, "carModel" + i, "carColor" + i, "placa" + i, "status" + i);

            passengers.add(passenger);
            services.add(service);
            drivers.add(driver);
        }

        adapter.addAll(services, passengers, drivers);
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
