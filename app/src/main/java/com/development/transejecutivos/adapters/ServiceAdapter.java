package com.development.transejecutivos.adapters;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.development.transejecutivos.R;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Passenger;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.User;
import java.util.ArrayList;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder> {

    ArrayList<Service> services;
    ArrayList<Passenger> passengers;
    ArrayList<Driver> drivers;
    Context context;
    User user;

    //public ServiceAdapter(Context context, User user) {
    public ServiceAdapter(Context context) {
        //this.user = user;
        this.context = context;
        this.services = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    @Override
    public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.services_list, parent, false);

        return new ServiceHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ServiceHolder holder, int position) {
        Service currentService = this.services.get(position);
        Passenger currentPassenger = this.passengers.get(position);
        Driver currentDriver = this.drivers.get(position);
        holder.setService(currentService.getReference(), currentService.getSource(), currentService.getDestiny(), currentService.getStartDate(), currentService.getPaxCant(), currentService.getCompany(), currentService.getFly(), currentService.getAeroline());
        holder.setPassenger(currentPassenger.getName(), currentPassenger.getLastName(), currentPassenger.getPhone(), currentPassenger.getEmail(), currentPassenger.getCity(), currentPassenger.getAddress());
        holder.setDriver(currentDriver.getIdDriver(), currentDriver.getCode(), currentDriver.getName(), currentDriver.getLastName(), currentDriver.getPhone(), currentDriver.getAddress(), currentDriver.getCity(), currentDriver.getEmail(), currentDriver.getCarType(), currentDriver.getCarBrand(), currentDriver.getCarModel(), currentDriver.getCarColor(), currentDriver.getCarriagePlate(), currentDriver.getStatus());
    }

    public void addAll(@NonNull ArrayList<Service> services, @NonNull ArrayList<Passenger> passengers, @NonNull ArrayList<Driver> drivers) {
        if (services == null) {
            throw new NullPointerException("The items cannot be null");
        }

        if (passengers == null) {
            throw new NullPointerException("The items cannot be null");
        }

        this.services.clear();
        this.services.addAll(services);

        this.passengers.clear();
        this.passengers.addAll(passengers);

        this.drivers.clear();
        this.drivers.addAll(drivers);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.services.size();
    }

    public class ServiceHolder extends RecyclerView.ViewHolder {
        TextView txtview_destiny;
        TextView txtview_source;
        TextView txtview_datetime;
        TextView txtview_reference;
        TextView txtview_paxcant;
        TextView txtview_passgname;
        TextView txtview_passgphone;
        TextView txtview_passgemail;
        TextView txtview_passglocation;
        TextView txtview_company;
        TextView txtview_fly;

        View cardView;
        View relativeloDetails;
        ImageView expander;
        ImageView contracter;
        ImageView driver;

        private int mOriginalHeight = 0;
        private boolean mIsViewExpanded = false;

        ServiceAdapter adapter;

        public ServiceHolder(View itemView, ServiceAdapter Adapter) {
            super(itemView);
            txtview_destiny = (TextView) itemView.findViewById(R.id.txtview_destiny);
            txtview_source = (TextView) itemView.findViewById(R.id.txtview_source);
            txtview_datetime = (TextView) itemView.findViewById(R.id.txtview_datetime);
            txtview_reference = (TextView) itemView.findViewById(R.id.txtview_reference);
            txtview_paxcant = (TextView) itemView.findViewById(R.id.txtview_paxcant);
            txtview_company = (TextView) itemView.findViewById(R.id.txtview_company);
            txtview_fly = (TextView) itemView.findViewById(R.id.txtview_fly);

            txtview_passgphone = (TextView) itemView.findViewById(R.id.txtview_passgphone);
            txtview_passgname = (TextView) itemView.findViewById(R.id.txtview_passgname);
            txtview_passgemail = (TextView) itemView.findViewById(R.id.txtview_passgemail);
            txtview_passglocation = (TextView) itemView.findViewById(R.id.txtview_passglocation);

            cardView =  itemView.findViewById(R.id.card_view_services_list);
            relativeloDetails =  itemView.findViewById(R.id.relativelayout_details);

            expander = (ImageView) itemView.findViewById(R.id.imgview_expand_icon);
            contracter = (ImageView) itemView.findViewById(R.id.imgview_contract_icon);

            driver = (ImageView) itemView.findViewById(R.id.img_view_driver_photo);

            adapter = Adapter;

            setClickListeners(itemView);
        }

        public void setClickListeners(final View itemView) {
            txtview_destiny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView);
                }
            });

            txtview_source.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView);
                }
            });

            expander.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView);
                }
            });

            contracter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView);
                }
            });

            driver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView);
                }
            });
        }

        /**
         *
         * @param reference
         * @param source
         * @param destiny
         * @param startdate
         * @param paxCant
         * @param company
         * @param fly
         * @param aeroline
         */
        public void setService(String reference, String source, String destiny, String startdate, String paxCant, String company, String fly, String aeroline) {
            txtview_destiny.setText(destiny);
            txtview_source.setText(source);
            txtview_datetime.setText(startdate);
            txtview_reference.setText(reference);
            txtview_paxcant.setText(paxCant);
            txtview_company.setText(company);
            txtview_fly.setText(fly + ", " + aeroline);
        }

        /**
         *
         * @param name
         * @param lastName
         * @param phone
         * @param email
         * @param city
         * @param address
         */
        public void setPassenger(String name, String lastName, String phone, String email, String city, String address) {
            txtview_passgname.setText(name + " " + lastName);
            txtview_passgphone.setText(phone);
            txtview_passgemail.setText(email);
            txtview_passglocation.setText(city + ", " + address);
        }

        /**
         *
         * @param idDriver
         * @param code
         * @param name
         * @param lastName
         * @param phone
         * @param address
         * @param city
         * @param email
         * @param carType
         * @param carBrand
         * @param carModel
         * @param carColor
         * @param carriagePlate
         * @param status
         */
        public void setDriver(int idDriver, String code, String name, String lastName, String phone, String address, String city, String email, String carType, String carBrand, String carModel, String carColor, String carriagePlate, String status) {

        }

        public void collapse(final View view) {
            if (mOriginalHeight == 0) {
                mOriginalHeight = view.getHeight();
            }

            ValueAnimator valueAnimator;

            if (!mIsViewExpanded) {
                mIsViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 0.8));
                relativeloDetails.setVisibility(View.VISIBLE);
                expander.setVisibility(View.INVISIBLE);
                contracter.setVisibility(View.VISIBLE);
            }
            else {
                mIsViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 0.8), mOriginalHeight);
                relativeloDetails.setVisibility(View.INVISIBLE);
                expander.setVisibility(View.VISIBLE);
                contracter.setVisibility(View.INVISIBLE);
            }

            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();

                    cardView.getLayoutParams().height = value.intValue();
                    cardView.requestLayout();
                }
            });

            valueAnimator.start();
        }
    }
}
