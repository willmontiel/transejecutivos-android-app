package com.development.transportesejecutivos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.development.transportesejecutivos.models.Aeroline;
import java.util.ArrayList;

/**
 * Created by Will Montiel on 08/15/2017.
 */

public class AerolineAdapter extends ArrayAdapter<Aeroline> {

    private ArrayList<Aeroline> values;
    private ArrayList<Aeroline> itemsAll;
    private ArrayList<Aeroline> suggestions;

    public AerolineAdapter(Context context, int viewResourceId, ArrayList<Aeroline> values) {
        super(context, viewResourceId, values);
        this.values = values;
        this.itemsAll = (ArrayList<Aeroline>) values.clone();
        this.suggestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Aeroline getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getName());

        return label;
    }
}


