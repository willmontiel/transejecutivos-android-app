package com.development.transportesejecutivos.misc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by william.montiel on 06/03/2016.
 */
public class DialogCreator  {

    public Context context;

    public DialogCreator(Context context) {
        this.context = context;
    }

    public void createCustomDialog(String message, String btnTitle) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.context);
        dialog.setMessage(message);
        dialog.setPositiveButton(btnTitle, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }
}

