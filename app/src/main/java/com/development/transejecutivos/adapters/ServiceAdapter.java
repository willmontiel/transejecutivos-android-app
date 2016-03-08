package com.development.transejecutivos.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.development.transejecutivos.R;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.User;
import java.util.ArrayList;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder> {

    ArrayList<Service> services;
    Context context;
    User user;

    //public ServiceAdapter(Context context, User user) {
    public ServiceAdapter(Context context) {
        //this.user = user;
        this.context = context;
        this.services = new ArrayList<>();
    }

    @Override
    public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.services_list, parent, false);

        return new ServiceHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ServiceHolder holder, int position) {
        Service currentItem = this.services.get(position);
        holder.setService(currentItem.getReference());
    }

    public void addAll(@NonNull ArrayList<Service> service) {
        if (service == null) {
            throw new NullPointerException("The items cannot be null");
        }
        this.services.clear();
        this.services.addAll(service);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ServiceHolder extends RecyclerView.ViewHolder {

        TextView text_view_reference;
        ServiceAdapter adapter;

        public ServiceHolder(View itemView, ServiceAdapter Adapter) {
            super(itemView);
            text_view_reference = (TextView) itemView.findViewById(R.id.text_view_reference);
            adapter = Adapter;
        }

        public void setService(String reference) {
            text_view_reference.setText(reference);
        }

    }
}
