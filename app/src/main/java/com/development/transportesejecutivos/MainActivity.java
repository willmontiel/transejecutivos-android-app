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
    public void onFragmentInteraction(Uri uri) {

    }
}
