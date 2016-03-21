package com.development.transejecutivos.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.R;
import com.development.transejecutivos.api_config.ApiConstants;
import com.development.transejecutivos.models.Driver;
import com.development.transejecutivos.models.Service;
import com.development.transejecutivos.models.ServiceData;


/**
 * Created by developer on 3/21/16.
 */
public class ServiceExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> header; // header titles
    // Child data in format of header title, child title
    private HashMap<String, ArrayList<ServiceData>> child;

    public ServiceExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, ArrayList<ServiceData>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {

        // This will return the child
        return this.child.get(this.header.get(groupPosition)).get(
                childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        // Getting child text
        ServiceData currentService = (ServiceData) getChild(groupPosition, childPosition);

        Log.d("Service", currentService.getReference());

        // Inflating child layout and setting textview
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.service_data_child, parent, false);
        }

        ServiceHolder serviceHolder = new ServiceHolder(convertView, this, this._context);
        serviceHolder.setService(currentService);

        return serviceHolder.getItemView();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        // return children count
        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        // Get header position
        return this.header.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        // Get header size
        return this.header.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        // Getting header title
        String headerTitle = (String) getGroup(groupPosition);

        // Inflating header layout and setting text
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.service_date_header, parent, false);
        }

        TextView header_text = (TextView) convertView.findViewById(R.id.header);

        header_text.setText(headerTitle);

        // If group is expanded then change the text into bold and change the
        // icon
        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.arrow_up, 0);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon

            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.arrow_down, 0);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class ServiceHolder {
        Context context;
        ServiceExpandableListAdapter adapter;
        View itemView;

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
        TextView txtview_status;
        TextView txtview_status_detail;

        ImageView img_view_driver_photo;
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
        ImageView driver;

        private int mOriginalHeight = 0;
        private boolean mIsDetailsViewExpanded = false;

        public ServiceHolder(View itemView, ServiceExpandableListAdapter Adapter, Context context) {
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

            txtview_status = (TextView) itemView.findViewById(R.id.txtview_status);
            txtview_status_detail = (TextView) itemView.findViewById(R.id.txtview_status_detail);

            img_view_driver_photo = (ImageView) itemView.findViewById(R.id.img_view_driver_photo);
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

            driver = (ImageView) itemView.findViewById(R.id.img_view_driver_photo);

            adapter = Adapter;
            this.context = context;
            this.itemView = itemView;

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

            relativeloDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    collapse(itemView, relativeloDetails);
                }
            });

        }

        /**
         *
         * @param service
         */
        public void setService(ServiceData service) {
            txtview_destiny.setText(service.getDestiny());
            txtview_source.setText(service.getSource());
            txtview_datetime.setText(service.getStartDate());
            txtview_reference.setText("Referencia: " + service.getReference());
            txtview_paxcant.setText(service.getPaxCant() + " Pasajero(s)");

            txtview_destiny_detail.setText("Destino: " + service.getDestiny());
            txtview_source_detail.setText("Origen: " + service.getSource());
            txtview_datetime_detail.setText("Fecha: " + service.getStartDate());

            setServiceStatus(service.getStatus());

            if (!TextUtils.isEmpty(service.getPax())) {
                txtview_pax.setText("Pasajeros: " + service.getPax());
            }

            if (!TextUtils.isEmpty(service.getFly()) && !TextUtils.isEmpty(service.getAeroline())) {
                txtview_fly.setText(Html.fromHtml("Vuelo: <a href=\"" + this.context.getResources().getString(R.string.url_fly) + service.getFly() + "\">" + service.getFly() + ", " + service.getAeroline() + "</a>"));
            }

            txtview_fly.setMovementMethod(LinkMovementMethod.getInstance());
            txtview_obs.setText("Observaciones: " + service.getObservations());

            setDriver(service);
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

            txtview_status.setText(status);
            txtview_status.setTextColor(color);
            txtview_status_detail.setText("Estado: " + status);
            txtview_status_detail.setTextColor(color);
        }

        /**
         *
         * @param driver
         */
        public void setDriver(ServiceData driver) {
            txtview_driver_name.setText("Conductor: " + driver.getName() + " " + driver.getLastName());
            txtview_driver_email.setText(driver.getEmail());
            txtview_driver_phone.setText("Telefonos: " + driver.getPhone1() + ", " + driver.getPhone2());
            txtview_car_type.setText("Tipo de carro: " + driver.getCarBrand() + ", " + driver.getCarType());
            txtview_car_detail.setText("Modelo: " + driver.getCarModel() + ", " + driver.getCarColor());
            txtview_carriage_plate.setText("Placa: " + driver.getCarriagePlate());

            String durl = ApiConstants.URL_DRIVER_PHOTO + "cara" + driver.getCode() + ".jpg&ancho=100";
            String curl = ApiConstants.URL_CAR_PHOTO + "carro" + driver.getCode() + ".jpg&ancho=100";

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
}
