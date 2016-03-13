package com.development.transejecutivos.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.development.transejecutivos.fragments.FilterServicesFragment;
import com.development.transejecutivos.fragments.FutureServicesFragment;
import com.development.transejecutivos.fragments.TodayServicesFragment;
import com.development.transejecutivos.models.User;

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
                TodayServicesFragment todayServicesFragment = TodayServicesFragment.newInstance(this.user);
                return todayServicesFragment;
            case 1:
                FutureServicesFragment futureServicesFragment = FutureServicesFragment.newInstance(this.user);
                return futureServicesFragment;
            case 2:
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
