package com.development.transejecutivos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.development.transejecutivos.R;
import com.development.transejecutivos.models.DashboarMenu;

/**
 * Created by developer on 3/21/16.
 */
public class DashboardMenuAdapter extends BaseAdapter{
    private Context context;

    public DashboardMenuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return DashboarMenu.ITEMS.length;
    }

    @Override
    public DashboarMenu getItem(int position) {
        return DashboarMenu.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_menu, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.icon_image);
        TextView nombreCoche = (TextView) view.findViewById(R.id.icon_name);

        final DashboarMenu item = getItem(position);
        imagenCoche.setImageResource(item.getIdDrawable());
        nombreCoche.setText(item.getName());

        return view;
    }
}
