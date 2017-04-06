package com.development.transportesejecutivos.misc;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by kevin.ramirez on 18/07/2016.
 */
@SuppressLint("ValidFragment")
public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    EditText txtdate;
    public TimeDialog(View view){
        txtdate = (EditText)view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), 2, this, 12, 0, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int selectedHour,int selectedMinute) {
        String date = checkDigit(selectedHour) + ":" + checkDigit(selectedMinute);
        txtdate.setText(date);
    }

    public String checkDigit(int number) {
        return number<=9?"0"+number: String.valueOf(number);
    }

}