package com.development.transejecutivos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecoverpassActivity extends ActivityBase {
    Button btnRecover;
    private View recoverProgressView;
    private View recoverFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoverpass);

        recoverFormView = findViewById(R.id.recoverpass_form);
        recoverProgressView = findViewById(R.id.recoverpass_progress);

        btnRecover = (Button) findViewById(R.id.button_recoverpass);

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true, recoverFormView, recoverProgressView);
            }
        });
    }
}
