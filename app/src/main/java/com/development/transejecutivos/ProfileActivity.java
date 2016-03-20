package com.development.transejecutivos;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends ActivityBase implements LoaderManager.LoaderCallbacks<Cursor> {

    private ProfileTask mAuthTask = null;

    // UI references.
    private View mProfileProgress;
    private View mProfileForm;
    private View mProfileContainer;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutLastname;
    private TextInputLayout inputLayoutEmail1;
    private TextInputLayout inputLayoutEmail2;
    private TextInputLayout inputLayoutPhone1;
    private TextInputLayout inputLayoutPhone2;
    private TextInputLayout inputLayoutPassword;

    private EditText txtName;
    private EditText txtLastName;
    private EditText txtEmail1;
    private EditText txtEmail2;
    private EditText txtPhone1;
    private EditText txtPhone2;
    private EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();
        setViews();
        setProfileValues();

        Button mProfileButton = (Button) findViewById(R.id.button_profile);
        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }

    private void setViews() {
        mProfileProgress = findViewById(R.id.profile_progress);
        mProfileForm = findViewById(R.id.profile_form);
        mProfileContainer = findViewById(R.id.profile_container);

        inputLayoutName  = (TextInputLayout) findViewById(R.id.txtinput_layout_name);
        inputLayoutLastname  = (TextInputLayout) findViewById(R.id.txtinput_layout_lastname);
        inputLayoutEmail1  = (TextInputLayout) findViewById(R.id.txtinput_layout_email1);
        inputLayoutEmail2  = (TextInputLayout) findViewById(R.id.txtinput_layout_email2);
        inputLayoutPhone1  = (TextInputLayout) findViewById(R.id.txtinput_layout_phone1);
        inputLayoutPhone2  = (TextInputLayout) findViewById(R.id.txtinput_layout_phone2);
        inputLayoutPassword  = (TextInputLayout) findViewById(R.id.txtinput_layout_password);

        txtName = (EditText) findViewById(R.id.name);
        txtLastName = (EditText) findViewById(R.id.lastname);
        txtEmail1 = (EditText) findViewById(R.id.email1);
        txtEmail2 = (EditText) findViewById(R.id.email2);
        txtPhone1 = (EditText) findViewById(R.id.phone1);
        txtPhone2 = (EditText) findViewById(R.id.phone2);
        txtPassword = (EditText) findViewById(R.id.password);
    }

    private void setProfileValues() {
        txtName.setText(user.getName());
        txtLastName.setText(user.getLastName());
        txtEmail1.setText(user.getEmail1());
        txtEmail2.setText(user.getEmail2());
        txtPhone1.setText(user.getPhone1());
        txtPhone2.setText(user.getPhone2());
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        inputLayoutName.setError(null);
        inputLayoutLastname.setError(null);
        inputLayoutEmail1.setError(null);
        inputLayoutEmail2.setError(null);
        inputLayoutPhone1.setError(null);
        inputLayoutPhone2.setError(null);
        inputLayoutPassword.setError(null);

        // Store values at the time of the login attempt.
        String name = txtName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String email1 = txtEmail1.getText().toString();
        String email2 = txtEmail2.getText().toString();
        String phone1 = txtPhone1.getText().toString();
        String phone2 = txtPhone2.getText().toString();
        String password = txtPassword.getText().toString();

        boolean cancel;

        cancel = validateField(name, inputLayoutName, txtName, getString(R.string.error_empty_name));
        cancel = validateField(lastName, inputLayoutLastname, txtLastName, getString(R.string.error_empty_lastname));
        cancel = isValidEmail(email1, inputLayoutEmail1, txtEmail1, getString(R.string.error_invalid_email));
        cancel = validateField(phone1, inputLayoutPhone1, txtPhone1, getString(R.string.error_empty_phone1));

        if (!TextUtils.isEmpty(email2)) {
            cancel = isValidEmail(email2, inputLayoutEmail2, txtEmail2, getString(R.string.error_invalid_email));
        }

        if (!cancel) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true, mProfileForm, mProfileProgress);
            //mAuthTask = new ProfileTask(name, lastName, email1, email2, phone1, phone2, password);
            //mAuthTask.execute((Void) null);
        }
    }

    /**
     * Validate if a field is empty
     * @param value
     * @param input
     * @param editText
     * @param message
     * @return
     */
    private boolean validateField(String value, TextInputLayout input, EditText editText, String message) {
        if (TextUtils.isEmpty(value)) {
            input.setError(message);
            editText.requestFocus();
            return true;
        }
        return false;
    }

    private boolean isValidEmail(CharSequence email, TextInputLayout input, EditText editText, String message) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input.setError(message);
            editText.requestFocus();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            session.logoutUser();
            return true;
        }
        else if (id == R.id.action_profile) {
            return true;
        }
        else if (id == R.id.action_new_service) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public class ProfileTask extends AsyncTask<Void, Void, Boolean> {
        public ProfileTask() {

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            mAuthTask = null;
            showProgress(false, mProfileForm, mProfileProgress);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false, mProfileForm, mProfileProgress);
        }
    }
}
