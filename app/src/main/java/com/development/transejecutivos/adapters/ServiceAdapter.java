package com.development.transejecutivos.adapters;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.R;
import com.development.transejecutivos.api_config.ApiConstants;
import com.development.transejecutivos.misc.BitmapLruCache;
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
        this.drivers = new ArrayList<>();
    }

    @Override
    public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.services_list, parent, false);

        return new ServiceHolder(itemView, this, this.context);
    }

    @Override
    public void onBindViewHolder(ServiceHolder holder, int position) {
        Service currentService = this.services.get(position);
        Passenger currentPassenger = this.passengers.get(position);
        Driver currentDriver = this.drivers.get(position);

        holder.setService(currentService.getReference(), currentService.getSource(), currentService.getDestiny(), currentService.getStartDate(), currentService.getEndDate(), currentService.getPaxCant(), currentService.getCompany(), currentService.getFly(), currentService.getAeroline(), currentService.getRepresent(), currentService.getObservations());
        holder.setPassenger(currentPassenger.getName(), currentPassenger.getLastName(), currentPassenger.getPhone(), currentPassenger.getEmail(), currentPassenger.getCity(), currentPassenger.getAddress());
        holder.setDriver(currentDriver.getCode(), currentDriver.getName(), currentDriver.getLastName(), currentDriver.getPhone(), currentDriver.getAddress(), currentDriver.getCity(), currentDriver.getEmail(), currentDriver.getCarType(), currentDriver.getCarBrand(), currentDriver.getCarModel(), currentDriver.getCarColor(), currentDriver.getCarriagePlate(), currentDriver.getStatus());
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
        TextView txtview_enddate;
        TextView txtview_reference;
        TextView txtview_paxcant;
        TextView txtview_passgname;
        TextView txtview_passgphone;
        TextView txtview_passgemail;
        TextView txtview_passglocation;
        TextView txtview_company;
        TextView txtview_fly;
        TextView txtview_represent;
        TextView txtview_obs;

        ImageView img_view_driver_photo;
        ImageView imgview_car_photo;

        TextView txtview_driver_name;
        TextView txtview_driver_email;
        TextView txtview_driver_phone;
        TextView txtview_driver_address;
        TextView txtview_car_type;
        TextView txtview_car_detail;
        TextView txtview_carriage_plate;
        TextView txtview_driver_code;

        View cardView;
        View relativeloDetails;
        View relativelayout_driver_details;

        ImageView expander;
        ImageView contracter;
        ImageView driver;

        private int mOriginalHeight = 0;
        private boolean mIsDetailsViewExpanded = false;

        ServiceAdapter adapter;

        Context context;

        public ServiceHolder(View itemView, ServiceAdapter Adapter, Context context) {
            super(itemView);
            txtview_destiny = (TextView) itemView.findViewById(R.id.txtview_destiny);
            txtview_source = (TextView) itemView.findViewById(R.id.txtview_source);
            txtview_datetime = (TextView) itemView.findViewById(R.id.txtview_datetime);
            txtview_enddate = (TextView) itemView.findViewById(R.id.txtview_enddate);
            txtview_reference = (TextView) itemView.findViewById(R.id.txtview_reference);
            txtview_paxcant = (TextView) itemView.findViewById(R.id.txtview_paxcant);
            txtview_company = (TextView) itemView.findViewById(R.id.txtview_company);
            txtview_fly = (TextView) itemView.findViewById(R.id.txtview_fly);
            txtview_represent = (TextView) itemView.findViewById(R.id.txtview_represent);
            txtview_obs = (TextView) itemView.findViewById(R.id.txtview_obs);

            img_view_driver_photo = (ImageView) itemView.findViewById(R.id.img_view_driver_photo);
            imgview_car_photo = (ImageView) itemView.findViewById(R.id.imgview_car_photo);

            txtview_passgphone = (TextView) itemView.findViewById(R.id.txtview_passgphone);
            txtview_passgname = (TextView) itemView.findViewById(R.id.txtview_passgname);
            txtview_passgemail = (TextView) itemView.findViewById(R.id.txtview_passgemail);
            //txtview_passglocation = (TextView) itemView.findViewById(R.id.txtview_passglocation);

            txtview_driver_name = (TextView) itemView.findViewById(R.id.txtview_driver_name);
            txtview_driver_email = (TextView) itemView.findViewById(R.id.txtview_driver_email);
            txtview_driver_phone = (TextView) itemView.findViewById(R.id.txtview_driver_phone);
            txtview_driver_address = (TextView) itemView.findViewById(R.id.txtview_driver_address);
            txtview_car_type = (TextView) itemView.findViewById(R.id.txtview_car_type);
            txtview_car_detail = (TextView) itemView.findViewById(R.id.txtview_car_detail);
            txtview_carriage_plate = (TextView) itemView.findViewById(R.id.txtview_carriage_plate);
            txtview_driver_code = (TextView) itemView.findViewById(R.id.txtview_driver_code);

            cardView =  itemView.findViewById(R.id.card_view_services_list);
            relativeloDetails =  itemView.findViewById(R.id.relativelayout_details);
            relativelayout_driver_details =  itemView.findViewById(R.id.relativelayout_driver_details);

            expander = (ImageView) itemView.findViewById(R.id.imgview_expand_icon);
            contracter = (ImageView) itemView.findViewById(R.id.imgview_contract_icon);

            driver = (ImageView) itemView.findViewById(R.id.img_view_driver_photo);

            adapter = Adapter;
            this.context = context;

            setClickListeners(itemView);
        }

        public void setClickListeners(final View itemView) {
            txtview_destiny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView, relativeloDetails);
                }
            });

            txtview_source.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView, relativeloDetails);
                }
            });

            expander.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView, relativeloDetails);
                }
            });

            contracter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView, relativeloDetails);
                }
            });

            driver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView, relativelayout_driver_details);
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
        public void setService(String reference, String source, String destiny, String startdate, String endDate, String paxCant, String company, String fly, String aeroline, String represent, String obs) {
            txtview_destiny.setText(destiny);
            txtview_source.setText(source);
            txtview_datetime.setText(startdate);
            txtview_enddate.setText("Fecha de finalización: " + endDate);
            txtview_reference.setText(reference);
            txtview_paxcant.setText(paxCant + " Pasajeros");
            txtview_company.setText("Compañía: " + company);
            txtview_fly.setText("Vuelo: " + fly + ", " + aeroline);
            txtview_represent.setText("Representando: " + represent);
            txtview_obs.setText("Observaciones: " + obs);
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
            txtview_passgphone.setText("Teléfono: " + phone);
            txtview_passgemail.setText(email);
            //txtview_passglocation.setText(city + ", " + address);
        }

        /**
         *
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
        public void setDriver(String code, String name, String lastName, String phone, String address, String city, String email, String carType, String carBrand, String carModel, String carColor, String carriagePlate, String status) {
            txtview_driver_name.setText("Conductor: " + name + " " + lastName);
            txtview_driver_email.setText(email);
            txtview_driver_phone.setText(phone);
            txtview_driver_address.setText(city + ", " + address);
            txtview_car_type.setText("Tipo de carro: " + carBrand + ", " + carType);
            txtview_car_detail.setText("Modelo: " + carModel + ", " + carColor);
            txtview_carriage_plate.setText("Placa: " + carriagePlate);
            txtview_driver_code.setText("Código: " + code);

            String durl = ApiConstants.URL_DRIVER_PHOTO + "cara" + code + ".jpg&ancho=100";
            String curl = ApiConstants.URL_CAR_PHOTO + "carro" + code + ".jpg&ancho=100";

            Log.d("DURL: ", durl);
            Log.d("CURL: ", curl);

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            ImageRequest drequest = new ImageRequest(durl,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            img_view_driver_photo.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            img_view_driver_photo.setImageResource(R.drawable.driver);
                        }
                    });

            ImageRequest crequest = new ImageRequest(curl,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            imgview_car_photo.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            imgview_car_photo.setImageResource(R.drawable.car);
                        }
                    });

            requestQueue.add(drequest);
            requestQueue.add(crequest);
        }

        public void collapse(final View view, final View layout) {
            if (mOriginalHeight == 0) {
                mOriginalHeight = view.getHeight();
            }

            ValueAnimator valueAnimator;


            if (!mIsDetailsViewExpanded) {
                mIsDetailsViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.3));

                relativeloDetails.setVisibility(View.GONE);
                relativelayout_driver_details.setVisibility(View.GONE);

                layout.setVisibility(View.VISIBLE);
                expander.setVisibility(View.GONE);
                contracter.setVisibility(View.VISIBLE);
            }
            else  {
                mIsDetailsViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.3), mOriginalHeight);

                relativeloDetails.setVisibility(View.GONE);
                relativelayout_driver_details.setVisibility(View.GONE);

                layout.setVisibility(View.GONE);
                expander.setVisibility(View.VISIBLE);
                contracter.setVisibility(View.GONE);
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
