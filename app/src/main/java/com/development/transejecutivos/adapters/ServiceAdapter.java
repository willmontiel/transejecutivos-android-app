package com.development.transejecutivos.adapters;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
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
        holder.setService(currentItem.getReference(), currentItem.getSource(), currentItem.getDestiny(), currentItem.getStartDate());
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
        return this.services.size();
    }

    public class ServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text_view_reference;
        TextView text_view_source;
        TextView text_view_destiny;
        TextView text_view_datetime;

        private int mOriginalHeight = 0;
        private boolean mIsViewExpanded = false;

        ServiceAdapter adapter;

        public ServiceHolder(View itemView, ServiceAdapter Adapter) {
            super(itemView);
            text_view_reference = (TextView) itemView.findViewById(R.id.text_view_reference);
            text_view_source = (TextView) itemView.findViewById(R.id.text_view_source);
            text_view_destiny = (TextView) itemView.findViewById(R.id.text_view_destiny);
            text_view_datetime = (TextView) itemView.findViewById(R.id.text_view_datetime);

            adapter = Adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            final View relativeloDetails =  view.findViewById(R.id.relativelayout_details);
            final View cardView =  view.findViewById(R.id.card_view_services_list);

            if (mOriginalHeight == 0) {
                mOriginalHeight = view.getHeight();
            }

            ValueAnimator valueAnimator;
            if (!mIsViewExpanded) {
                mIsViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.5));
                relativeloDetails.setVisibility(View.VISIBLE);
            }
            else {
                mIsViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.5), mOriginalHeight);
                relativeloDetails.setVisibility(View.INVISIBLE);
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

        public void setService(String reference, String source, String destiny, String startdate) {
            text_view_reference.setText(reference);
            text_view_source.setText(source);
            text_view_destiny.setText(destiny);
            text_view_datetime.setText(startdate);
        }

    }
}
