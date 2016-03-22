package com.development.transejecutivos;

import android.os.Bundle;
import android.widget.GridView;
import com.development.transejecutivos.adapters.DashboardMenuAdapter;

public class DashboardActivity extends ActivityBase {
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
    }
}
