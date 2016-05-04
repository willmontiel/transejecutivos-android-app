package com.development.transportesejecutivos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.development.transportesejecutivos.adapters.TabPagerAdapter;
import com.development.transportesejecutivos.fragments.FragmentBase;

public class MainActivity extends ActivityBase implements FragmentBase.OnFragmentInteractionListener{

    private TabLayout mainTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validateSession();

        configureTabs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isLocationServiceEnabled();
    }

    private void configureTabs() {
        Bundle t = getIntent().getExtras();
        int tab = 0;
        if (t != null) {
            tab = t.getInt("tab");
        }

        setTabs(tab);
    }

    private void setTabs(int tab) {
        mainTabs = (TabLayout) findViewById(R.id.main_tabs);

        mainTabs.addTab(mainTabs.newTab().setText(getResources().getString(R.string.main_tab)));
        mainTabs.addTab(mainTabs.newTab().setText(getResources().getString(R.string.filter_tab)));

        mainTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        mainTabs.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(),mainTabs.getTabCount(), getApplicationContext(), user);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTabs));

        mainTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setCurrentItem(tab, false);
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
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.action_new_service) {
            return true;
        }
        else if (id == R.id.action_dashboard) {
            Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
