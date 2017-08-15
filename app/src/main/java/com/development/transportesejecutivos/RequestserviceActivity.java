package com.development.transportesejecutivos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class RequestserviceActivity extends ActivityBase {
    AutoCompleteTextView carType;
    AutoCompleteTextView source_city;
    AutoCompleteTextView destiny_city;
    AutoCompleteTextView aeroline;
    EditText passengers;
    TextView start_date;
    ImageButton start_date_button;
    TextView start_time;
    ImageButton start_time_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestservice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();
        setCarTypes();
        setSourceCities();
        setDestinyities();
        setAerolínes();
        passengers = (EditText) findViewById(R.id.passengers);
        start_date = (TextView) findViewById(R.id.start_date);
        start_date_button = (ImageButton) findViewById(R.id.start_date_button);
        start_time = (TextView) findViewById(R.id.start_time);
        start_time_button = (ImageButton) findViewById(R.id.start_time_button);
        setDateAndTimePicker();
    }


    protected void setCarTypes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        carType = (AutoCompleteTextView) findViewById(R.id.car_type);
        carType.setAdapter(adapter);
    }

    protected void setSourceCities() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        source_city = (AutoCompleteTextView) findViewById(R.id.source_city);
        source_city.setAdapter(adapter);
    }

    protected void setDestinyities() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        destiny_city = (AutoCompleteTextView) findViewById(R.id.destiny_city);
        destiny_city.setAdapter(adapter);
    }

    protected void setAerolínes() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        aeroline = (AutoCompleteTextView) findViewById(R.id.aeroline);
        aeroline.setAdapter(adapter);
    }

    protected void setDateAndTimePicker() {
        final ActivityBase self = this;

        start_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                DatePickerDialog dpd = new DatePickerDialog(self, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        start_date.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);

                dpd.show();
            }
        });

        start_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(self, 2,  new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        start_time.setText(formatTime(hourOfDay) + ":" + formatTime(minute));
                    }
                }, mHour, mMinute, true);

                tpd.show();
            }
        });
    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    private String formatTime(int number) {
        String h;
        if (number < 10) {
            h = "0" + number;
        }
        else {
            h = "" + number;
        }

        return h;
    }
}
