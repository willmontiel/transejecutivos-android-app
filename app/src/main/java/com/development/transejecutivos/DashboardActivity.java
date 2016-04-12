package com.development.transejecutivos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.development.transejecutivos.adapters.DashboardMenuAdapter;
import com.development.transejecutivos.models.DashboardMenu;

public class DashboardActivity extends ActivityBase implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private DashboardMenuAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        validateSession();

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
                break;

            case 4:
                session.logoutUser();
                break;

            default:
                break;
        }

    }
}
