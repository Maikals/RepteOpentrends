package com.example.mcastanys.repte.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mcastanys.repte.fragment.BaseFragment;
import com.example.mcastanys.repte.fragment.PreferenceFragment;

import java.util.ArrayList;

/**
 * Created by mcastanys on 14/07/15.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragments;
    public PageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(BaseFragment.newInstance("1"));
        fragments.add(BaseFragment.newInstance("2"));
    }

    public void addFragment() {
        fragments.add(BaseFragment.newInstance((Integer.toString(getCount()+1))));
    }

    public void removeFragment() {
        fragments.remove(fragments.size() - 1);
    }

    @Override
    public Fragment getItem(int position) {
        if(position < getCount()) {
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
