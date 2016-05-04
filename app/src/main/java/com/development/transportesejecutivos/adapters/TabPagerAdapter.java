package com.development.transportesejecutivos.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;
import com.development.transportesejecutivos.fragments.FilterServicesFragment;
import com.development.transportesejecutivos.fragments.ServicesFragment;
import com.development.transportesejecutivos.models.User;

/**
 * Created by william.montiel on 08/03/2016.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    Context context;
    User user;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs, Context context, User user) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.context = context;
        this.user = user;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ServicesFragment servicesFragment = ServicesFragment.newInstance(this.user);
                return servicesFragment;
            case 1:
                FilterServicesFragment filterServicesFragment = FilterServicesFragment.newInstance(this.user);
                return filterServicesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
