package com.development.transejecutivos;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    private TabLayout mainTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout mainTabs = (TabLayout) findViewById(R.id.main_tabs);

        mainTabs.addTab(mainTabs.newTab().setText("HOY"));
        mainTabs.addTab(mainTabs.newTab().setText("MAÑANA"));
        mainTabs.addTab(mainTabs.newTab().setText("FILTRAR"));

        mainTabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        mainTabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        // ...
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        // ...
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        // ...
                    }
                }
        );
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
