package com.development.transejecutivos;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.development.transejecutivos.misc.UserSessionManager;
import com.development.transejecutivos.models.User;

/**
 * Created by william.montiel on 11/03/2016.
 */
public class ActivityBase extends AppCompatActivity {

    UserSessionManager session;
    User user;

    public void setErrorSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);

        snackbar.show();

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorError));
        TextView txtv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.CENTER);
    }

    public void validateSession() {
        session = new UserSessionManager(getApplicationContext());

        if(session.checkLogin())
            finish();

        user = session.getUserDetails();
    }
}
