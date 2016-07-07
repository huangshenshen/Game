package com.cninter.a3dgame.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${jacksen-hss} on 2016/7/6 0006.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;



    public MainFragmentPagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
