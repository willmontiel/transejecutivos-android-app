package com.development.transportesejecutivos.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.transportesejecutivos.R;
import com.development.transportesejecutivos.holders.ServiceHolder;
import com.development.transportesejecutivos.models.Driver;
import com.development.transportesejecutivos.models.Service;
import com.development.transportesejecutivos.models.User;
import java.util.ArrayList;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceHolder> {

    ArrayList<Service> services;
    ArrayList<Driver> drivers;
    Context context;
    User user;

    //public ServiceAdapter(Context context, User user) {
    public ServiceAdapter(Context context, User user) {
        //this.user = user;
        this.context = context;
        this.user = user;
        this.services = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    @Override
    public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.services_list, parent, false);

        return new ServiceHolder(itemView, this.context, this.user);
    }

    @Override
    public void onBindViewHolder(ServiceHolder holder, int position) {
        Service currentService = this.services.get(position);
        Driver currentDriver = this.drivers.get(position);

        holder.setService(currentService);
        holder.setDriver(currentDriver);
    }

    public void addAll(@NonNull ArrayList<Service> services, @NonNull ArrayList<Driver> drivers) {
        if (services == null) {
            throw new NullPointerException("The items cannot be null");
        }

        if (drivers == null) {
            throw new NullPointerException("The items cannot be null");
        }

        this.services.clear();
        this.services.addAll(services);

        this.drivers.clear();
        this.drivers.addAll(drivers);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.services.size();
    }
}
