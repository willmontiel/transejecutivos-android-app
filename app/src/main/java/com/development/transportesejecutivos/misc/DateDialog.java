package com.development.transportesejecutivos.misc;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

/**
 * Created by kevin.ramirez on 01/07/2016.
 */
@SuppressLint("ValidFragment")
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText txtdate;
    public DateDialog(View view){
        txtdate = (EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(),this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //show to the selected date in the text box

        String date = year + "-" + checkDigit((month+1)) + "-" + checkDigit(day);
        txtdate.setText(date);
    }

    public String checkDigit(int number)
    {
        return number<=9?"0"+number: String.valueOf(number);
    }

}
