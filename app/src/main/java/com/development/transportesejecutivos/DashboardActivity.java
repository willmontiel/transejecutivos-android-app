package com.development.transportesejecutivos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.development.transportesejecutivos.adapters.DashboardMenuAdapter;
import com.development.transportesejecutivos.misc.DialogCreator;
import com.development.transportesejecutivos.models.DashboardMenu;

public class DashboardActivity extends ActivityBase implements AdapterView.OnItemClickListener{
    private static final int PERMISSION_REQUEST_CODE = 1;
    private GridView gridView;
    private DashboardMenuAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        validateSession();

        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if(!checkPermissions()){
                requestPermissions();
            }
        }

        gridView = (GridView) findViewById(R.id.gridView_menu);
        adaptador = new DashboardMenuAdapter(this);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isLocationServiceEnabled();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DashboardMenu item = (DashboardMenu) parent.getItemAtPosition(position);
        Intent i;
        switch (item.getId()) {
            case 0:
                i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(i);
                break;

            case 1:
                i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("tab", 0);
                startActivity(i);
                break;

            case 2:
                i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("tab", 1);
                startActivity(i);
                break;

            case 3:
                //i = new Intent(getApplicationContext(), RequestserviceActivity.class);
                //startActivity(i);
                break;

            case 4:
                session.logoutUser();
                break;

            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{android.Manifest.permission.CALL_PHONE, android.Manifest.permission.READ_PHONE_STATE, Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.WAKE_LOCK,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE ,Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //finish();
                    //startActivity(getIntent());
                } else {
                    DialogCreator dialogCreator = new DialogCreator(this);
                    dialogCreator.createCustomDialog(getString(R.string.cancel_permission_location), "ACEPTAR");
                }
                break;
        }
    }
}
