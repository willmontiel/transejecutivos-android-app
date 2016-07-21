package com.development.transportesejecutivos.holders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.development.transportesejecutivos.DriverlocationActivity;
import com.development.transportesejecutivos.R;
import com.development.transportesejecutivos.adapters.JsonKeys;
import com.development.transportesejecutivos.api_config.ApiConstants;
import com.development.transportesejecutivos.models.Driver;
import com.development.transportesejecutivos.models.Service;
import com.development.transportesejecutivos.models.ServiceData;

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
    View relativelayout_general;

    Button btn_see_driver_data;
    Button btn_see_service_details;
    Button btn_back_to_general_from_details;
    Button btn_driver_location;
    Button btn_call_driver;
    Button btn_back_to_general_from_driver;
    Button btn_share_location;

    Context context;

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
        relativelayout_general =  itemView.findViewById(R.id.relativelayout_general);

        btn_see_driver_data = (Button) itemView.findViewById(R.id.btn_see_driver_data);
        btn_see_service_details = (Button) itemView.findViewById(R.id.btn_see_service_details);
        btn_back_to_general_from_details = (Button) itemView.findViewById(R.id.btn_back_to_general_from_details);
        btn_call_driver = (Button) itemView.findViewById(R.id.btn_call_driver);
        btn_driver_location = (Button) itemView.findViewById(R.id.btn_driver_location);
        btn_back_to_general_from_driver = (Button) itemView.findViewById(R.id.btn_back_to_general_from_driver);
        btn_share_location = (Button) itemView.findViewById(R.id.btn_share_location);

        this.context = context;

        setClickListeners(itemView);
    }

    public void setClickListeners(final View itemView) {
        btn_see_service_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapse(relativeloDetails);
            }
        });

        btn_back_to_general_from_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapse(relativelayout_general);
            }
        });

        btn_back_to_general_from_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapse(relativelayout_general);
            }
        });

        btn_see_driver_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapse(relativelayout_driver_details);
            }
        });

        btn_share_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.share_location_subject));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, context.getResources().getString(R.string.share_location_content) + service.getIdService());
                context.startActivity(Intent.createChooser(sharingIntent, context.getResources().getString(R.string.share_location_title)));
            }
        });

        btn_driver_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DriverlocationActivity.class);
                i.putExtra(JsonKeys.SERVICE_ID, service.getIdService());
                context.startActivity(i);
            }
        });
    }

    public void setServiceData(ServiceData serviceData) {
        Service service = new Service(serviceData.getIdService(), serviceData.getReference(), serviceData.getCreateDate(), serviceData.getStartDate(), serviceData.getFly(), serviceData.getAeroline(), serviceData.getCompany(), serviceData.getPaxCant(), serviceData.getPax(), serviceData.getSource(), serviceData.getDestiny(), serviceData.getObservations(), serviceData.getStatus(), serviceData.getShareLocation());
        setService(service);

        Driver driver = new Driver(serviceData.getIdDriver(), serviceData.getCode(), serviceData.getName(), serviceData.getLastName(), serviceData.getPhone1(), serviceData.getPhone2(), serviceData.getAddress(), serviceData.getCity(), serviceData.getEmail(), serviceData.getCarType(), serviceData.getCarBrand(), serviceData.getCarModel(), serviceData.getCarColor(), serviceData.getCarriagePlate(), serviceData.getStatus(), serviceData.getLocation());
        setDriver(driver);
    }

    /**
     *
     * @param service
     */
    public void setService(final Service service) {
        this.service = service;
        txtview_destiny.setText(service.getDestiny());
        txtview_source.setText(service.getSource());
        txtview_datetime.setText(service.getStartDate());
        txtview_reference.setText(service.getReference());
        txtview_paxcant.setText(service.getPaxCant() + " Pasajero(s)");

        txtview_destiny_detail.setText("Destino: " + service.getDestiny());
        txtview_source_detail.setText("Origen: " + service.getSource());
        txtview_datetime_detail.setText(service.getStartDate());
        txtview_pax.setVisibility(View.GONE);
        txtview_obs.setVisibility(View.GONE);
        txtview_fly.setVisibility(View.GONE);
        btn_share_location.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(service.getPax())) {
            int pax = Integer.parseInt(service.getPax());
            if (pax > 1) {
                txtview_pax.setText(service.getPax() + " Pasajeros");
                txtview_pax.setVisibility(View.VISIBLE);
            }
        }

        if (!TextUtils.isEmpty(service.getFly()) && !TextUtils.isEmpty(service.getAeroline())) {
            txtview_fly.setText(Html.fromHtml("Vuelo: <a href=\"" + this.context.getResources().getString(R.string.url_fly) + service.getFly() + "\">" + service.getFly() + ", " + service.getAeroline() + "</a>"));
            txtview_fly.setVisibility(View.VISIBLE);
        }

        txtview_fly.setMovementMethod(LinkMovementMethod.getInstance());

        if (!TextUtils.isEmpty(service.getObservations())) {
            txtview_obs.setText("Observaciones: " + service.getObservations());
            txtview_obs.setVisibility(View.VISIBLE);
        }

        if (service.getShareLocation() == 1) {
            btn_share_location.setVisibility(View.VISIBLE);
        }
    }

    /**
     *
     * @param driver
     */
    public void setDriver(final Driver driver) {
        txtview_driver_name.setText(context.getResources().getString(R.string.not_driver));
        txtview_driver_phone.setText("");
        txtview_car_type.setText("");
        txtview_car_detail.setText("");
        txtview_carriage_plate.setText("");
        btn_call_driver.setVisibility(View.GONE);
        btn_driver_location.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(driver.getName()) || !TextUtils.isEmpty(driver.getLastName())) {
            txtview_driver_name.setText(driver.getName() + " " + driver.getLastName());
        }

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

            btn_call_driver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String tel1 = driver.getPhone1();
                        if (!TextUtils.isEmpty(tel1)) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + tel1));
                            context.startActivity(callIntent);
                        }
                    }
                    catch (SecurityException ex) {
                        Log.d("LALA", ex.toString());
                    }

                }
            });

            btn_call_driver.setVisibility(View.VISIBLE);

            if (driver.getLocation() == 1) {
                btn_driver_location.setVisibility(View.VISIBLE);
            }
        }

        String durl = ApiConstants.URL_DRIVER_PHOTO + "cara" + driver.getCode() + ".jpg&ancho=100";
        String curl = ApiConstants.URL_CAR_PHOTO + "carro" + driver.getCode() + ".jpg&ancho=100";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        ImageRequest drequest = new ImageRequest(durl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imgview_car_driver.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imgview_car_driver.setImageResource(R.drawable.driver);
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

    public void collapse(View layout) {
        relativeloDetails.setVisibility(View.GONE);
        relativelayout_driver_details.setVisibility(View.GONE);
        relativelayout_general.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }

    public View getItemView() {
        return this.itemView;
    }
}
