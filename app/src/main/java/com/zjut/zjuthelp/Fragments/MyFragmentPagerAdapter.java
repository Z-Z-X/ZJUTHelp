package com.zjut.zjuthelp.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> titles;
    private ArrayList<Fragment> fragmentList;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> titleList) {
        super(fm);
        fragmentList = list;
        titles = titleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

}
