package com.e23.ajn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.e23.ajn.base.BaseSupportFragment;
import java.util.ArrayList;

public class ReporterPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseSupportFragment> a;

    public ReporterPagerAdapter(FragmentManager fragmentManager, ArrayList<BaseSupportFragment> arrayList) {
        super(fragmentManager);
        this.a = arrayList;
    }

    public int getCount() {
        return this.a.size();
    }

    public Fragment getItem(int i) {
        return (Fragment) this.a.get(i);
    }

    public int getItemPosition(Object obj) {
        return -2;
    }
}
