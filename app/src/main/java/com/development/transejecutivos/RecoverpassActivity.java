package com.development.transejecutivos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.development.transejecutivos.adapters.JsonKeys;
import com.development.transejecutivos.api_config.ApiConstants;
import com.development.transejecutivos.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecoverpassActivity extends ActivityBase {
    Button btnRecover;
    private View recoverProgressView;
    private View recoverFormView;
    private EditText usernameView;
    private TextInputLayout inputLayoutUsername;
    private View recoverpassLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoverpass);

        recoverpassLayout = findViewById(R.id.recoverpass_layout);

        recoverFormView = findViewById(R.id.recoverpass_form);
        recoverProgressView = findViewById(R.id.recoverpass_progress);
        usernameView = (EditText) findViewById(R.id.username);

        inputLayoutUsername  = (TextInputLayout) findViewById(R.id.txt_input_layout_username);

        btnRecover = (Button) findViewById(R.id.button_recoverpass);

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoverPassword();
            }
        });
    }

    private void recoverPassword() {
        showProgress(true, recoverFormView, recoverProgressView);

        inputLayoutUsername.setError(null);

        final String username = usernameView.getText().toString();

        boolean cancel = false;

        // Check for a valid username, if the user entered one.
        if (TextUtils.isEmpty(username)) {
            inputLayoutUsername.setError(getString(R.string.error_empty_username));
            usernameView.requestFocus();
            cancel = true;
        }

        if (!cancel) {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    ApiConstants.URL_RECOVER_PASSWORD,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            validateResponse(response);
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            showProgress(false, recoverFormView, recoverProgressView);
                            setErrorSnackBar(recoverpassLayout, getResources().getString(R.string.error_general));
                        }
                    }) {

                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    params.put(JsonKeys.USERNAME, username);

                    return params;
                }
            };

            requestQueue.add(stringRequest);
        }
        else {
            showProgress(false, recoverFormView, recoverProgressView);
        }
    }

    protected void validateResponse(String response) {
        showProgress(false, recoverFormView, recoverProgressView);

        try {
            JSONObject resObj = new JSONObject(response);
            Boolean error = (Boolean) resObj.get(JsonKeys.ERROR);
            if (!error) {
                setErrorSnackBar(recoverpassLayout, getResources().getString(R.string.recoverpass_success));

                try {
                   Thread.sleep(4000);
                }
                catch (InterruptedException e) {
                    Log.d("EXCEPTION RECPASS", e.toString());
                }

                // Starting MainActivity
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

                finish();
            }
            else {
                setErrorSnackBar(recoverpassLayout, getResources().getString(R.string.recoverpass_failured));
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);

        finish();
    }
}
