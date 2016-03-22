package com.development.transejecutivos.adapters;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.R;
import com.development.transejecutivos.api_config.ApiConstants;
import com.development.transejecutivos.holders.ServiceHolder;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.User;
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
    public ServiceAdapter(Context context) {
        //this.user = user;
        this.context = context;
        this.services = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    @Override
    public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.services_list, parent, false);

        return new ServiceHolder(itemView, this.context);
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
