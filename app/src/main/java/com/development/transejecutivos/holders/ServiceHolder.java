package com.development.transejecutivos.holders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.DriverlocationActivity;
import com.development.transejecutivos.ProfileActivity;
import com.development.transejecutivos.R;
import com.development.transejecutivos.adapters.JsonKeys;
import com.development.transejecutivos.api_config.ApiConstants;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.ServiceData;

/**
 * Created by developer on 3/21/16.
 */
public class ServiceHolder extends RecyclerView.ViewHolder {
    TextView txtview_destiny;
    TextView txtview_source;
    TextView txtview_datetime;
    TextView txtview_reference;
    TextView txtview_destiny_detail;
    TextView txtview_source_detail;
    TextView txtview_datetime_detail;
    TextView txtview_paxcant;
    TextView txtview_pax;
    TextView txtview_fly;
    TextView txtview_obs;

    ImageView img_view_driver_photo;
    ImageView imgview_car_driver;
    ImageView imgview_car_photo;

    TextView txtview_driver_name;
    TextView txtview_driver_email;
    TextView txtview_driver_phone;
    TextView txtview_car_type;
    TextView txtview_car_detail;
    TextView txtview_carriage_plate;

    View cardView;
    View relativeloDetails;
    View relativelayout_driver_details;

    ImageView expander;
    ImageView contracter;

    private int mOriginalHeight = 0;
    private boolean mIsDetailsViewExpanded = false;

    Context context;

    TextView txtview_driver_location;

    Service service;

    public ServiceHolder(View itemView, Context context) {
        super(itemView);
        txtview_destiny = (TextView) itemView.findViewById(R.id.txtview_destiny);
        txtview_source = (TextView) itemView.findViewById(R.id.txtview_source);
        txtview_datetime = (TextView) itemView.findViewById(R.id.txtview_datetime);
        txtview_reference = (TextView) itemView.findViewById(R.id.txtview_reference);

        txtview_destiny_detail = (TextView) itemView.findViewById(R.id.txtview_destiny_detail);
        txtview_source_detail = (TextView) itemView.findViewById(R.id.txtview_source_detail);
        txtview_datetime_detail = (TextView) itemView.findViewById(R.id.txtview_datetime_detail);

        txtview_paxcant = (TextView) itemView.findViewById(R.id.txtview_paxcant);
        txtview_pax = (TextView) itemView.findViewById(R.id.txtview_pax);
        txtview_fly = (TextView) itemView.findViewById(R.id.txtview_fly);
        txtview_obs = (TextView) itemView.findViewById(R.id.txtview_obs);

        img_view_driver_photo = (ImageView) itemView.findViewById(R.id.img_view_driver_photo);
        imgview_car_driver = (ImageView) itemView.findViewById(R.id.imgview_car_driver);
        imgview_car_photo = (ImageView) itemView.findViewById(R.id.imgview_car_photo);

        txtview_driver_name = (TextView) itemView.findViewById(R.id.txtview_driver_name);
        txtview_driver_email = (TextView) itemView.findViewById(R.id.txtview_driver_email);
        txtview_driver_phone = (TextView) itemView.findViewById(R.id.txtview_driver_phone);
        txtview_car_type = (TextView) itemView.findViewById(R.id.txtview_car_type);
        txtview_car_detail = (TextView) itemView.findViewById(R.id.txtview_car_detail);
        txtview_carriage_plate = (TextView) itemView.findViewById(R.id.txtview_carriage_plate);

        cardView =  itemView.findViewById(R.id.card_view_services_list);
        relativeloDetails =  itemView.findViewById(R.id.relativelayout_details);
        relativelayout_driver_details =  itemView.findViewById(R.id.relativelayout_driver_details);

        expander = (ImageView) itemView.findViewById(R.id.imgview_expand_icon);
        contracter = (ImageView) itemView.findViewById(R.id.imgview_contract_icon);

        txtview_driver_location = (TextView) itemView.findViewById(R.id.txtview_driver_location);

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

        img_view_driver_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapse(itemView, relativelayout_driver_details);
            }
        });

        imgview_car_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapse(itemView, relativelayout_driver_details);
            }
        });

        relativeloDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapse(itemView, relativeloDetails);
            }
        });
    }

    public void setServiceData(ServiceData serviceData) {
        Service service = new Service(serviceData.getIdService(), serviceData.getReference(), serviceData.getCreateDate(), serviceData.getStartDate(), serviceData.getFly(), serviceData.getAeroline(), serviceData.getCompany(), serviceData.getPaxCant(), serviceData.getPax(), serviceData.getSource(), serviceData.getDestiny(), serviceData.getObservations(), serviceData.getStatus());
        setService(service);

        Driver driver = new Driver(serviceData.getIdDriver(), serviceData.getCode(), serviceData.getName(), serviceData.getLastName(), serviceData.getPhone1(), serviceData.getPhone2(), serviceData.getAddress(), serviceData.getCity(), serviceData.getEmail(), serviceData.getCarType(), serviceData.getCarBrand(), serviceData.getCarModel(), serviceData.getCarColor(), serviceData.getCarriagePlate(), serviceData.getStatus(), serviceData.getLocation());
        setDriver(driver);
    }

    /**
     *
     * @param service
     */
    public void setService(Service service) {
        this.service = service;
        txtview_destiny.setText(service.getDestiny());
        txtview_source.setText(service.getSource());
        txtview_datetime.setText(service.getStartDate());
        txtview_reference.setText(service.getReference());
        txtview_paxcant.setText(service.getPaxCant() + " Pasajero(s)");

        txtview_destiny_detail.setText("Destino: " + service.getDestiny());
        txtview_source_detail.setText("Origen: " + service.getSource());
        txtview_datetime_detail.setText(service.getStartDate());

        //setServiceStatus(service.getStatus());

        if (!TextUtils.isEmpty(service.getPax())) {
            txtview_pax.setText("Pasajeros: " + service.getPax());
        }

        if (!TextUtils.isEmpty(service.getFly()) && !TextUtils.isEmpty(service.getAeroline())) {
            txtview_fly.setText(Html.fromHtml("Vuelo: <a href=\"" + this.context.getResources().getString(R.string.url_fly) + service.getFly() + "\">" + service.getFly() + ", " + service.getAeroline() + "</a>"));
        }

        txtview_fly.setMovementMethod(LinkMovementMethod.getInstance());
        txtview_obs.setText("Observaciones: " + service.getObservations());
    }

    /**
     *
     * @param driver
     */
    public void setDriver(Driver driver) {
        if (!TextUtils.isEmpty(driver.getName()) || !TextUtils.isEmpty(driver.getLastName())) {
            txtview_driver_name.setText(driver.getName() + " " + driver.getLastName());
        }

        txtview_driver_email.setText(driver.getEmail());

        if (!TextUtils.isEmpty(driver.getPhone1()) || !TextUtils.isEmpty(driver.getPhone2())) {
            txtview_driver_phone.setText(driver.getPhone1() + ", " + driver.getPhone2());
        }

        if (!TextUtils.isEmpty(driver.getCarBrand()) || !TextUtils.isEmpty(driver.getCarType())) {
            txtview_car_type.setText(driver.getCarBrand() + ", " + driver.getCarType());
        }

        if (!TextUtils.isEmpty(driver.getCarModel()) || !TextUtils.isEmpty(driver.getCarColor())) {
            txtview_car_detail.setText("Modelo: " + driver.getCarModel() + ", " + driver.getCarColor());
        }

        if (!TextUtils.isEmpty(driver.getCarriagePlate())) {
            txtview_carriage_plate.setText("Placa: " + driver.getCarriagePlate());
        }

        if (driver.getLocation() == 1) {
            txtview_driver_location.setText(this.context.getResources().getString(R.string.prompt_driver_location));
            txtview_driver_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DriverlocationActivity.class);
                    i.putExtra(JsonKeys.SERVICE_ID, service.getIdService());
                    context.startActivity(i);
                }
            });
        }

        String durl = ApiConstants.URL_DRIVER_PHOTO + "cara" + driver.getCode() + ".jpg&ancho=100";
        String curl = ApiConstants.URL_CAR_PHOTO + "carro" + driver.getCode() + ".jpg&ancho=100";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        ImageRequest drequest = new ImageRequest(durl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        img_view_driver_photo.setImageBitmap(bitmap);
                        imgview_car_driver.setImageBitmap(bitmap);
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

    private void setServiceStatus(String status) {
        int color = context.getResources().getColor(R.color.colorPrimaryText);

        if (status.equals("orden")) {
            color = context.getResources().getColor(R.color.colorPrimary);
        }
        else if (status.equals("cancelar")) {
            color = context.getResources().getColor(R.color.colorError);
        }
        else if (status.equals("cotizacion")) {
            color = context.getResources().getColor(R.color.colorPrimaryDark);
        }

        /*
        txtview_status.setText(status);
        txtview_status.setTextColor(color);
        txtview_status_detail.setText("Estado: " + status);
        txtview_status_detail.setTextColor(color);
        */
    }

    public void collapse(final View view, final View layout) {
        if (mOriginalHeight == 0) {
            mOriginalHeight = view.getHeight();
        }

        ValueAnimator valueAnimator;

        if (!mIsDetailsViewExpanded) {
            mIsDetailsViewExpanded = true;
            //valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.3));

            relativeloDetails.setVisibility(View.GONE);
            relativelayout_driver_details.setVisibility(View.GONE);

            layout.setVisibility(View.VISIBLE);
            expander.setVisibility(View.GONE);
            contracter.setVisibility(View.VISIBLE);

            int heigth = layout.getLayoutParams().height;

            valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + heigth);
        }
        else  {
            mIsDetailsViewExpanded = false;

            int heigth = layout.getHeight();

            valueAnimator = ValueAnimator.ofInt(mOriginalHeight + heigth, mOriginalHeight);

            relativeloDetails.setVisibility(View.GONE);

            relativelayout_driver_details.setVisibility(View.GONE);

            layout.setVisibility(View.GONE);
            expander.setVisibility(View.VISIBLE);
            contracter.setVisibility(View.GONE);
        }

        /**
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
         **/
    }

    public View getItemView() {
        return this.itemView;
    }
}
