package com.example.mcastanys.repte.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mcastanys.repte.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by mcastanys on 14/07/15.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    ArrayList<BaseFragment> fragments;
    public PageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(BaseFragment.newInstance("1"));
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
