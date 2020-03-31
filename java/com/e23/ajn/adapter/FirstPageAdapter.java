package com.e23.ajn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.model.CateBean;
import java.util.ArrayList;

public class FirstPageAdapter extends FragmentStatePagerAdapter {
    private ArrayList<CateBean> a;
    private ArrayList<BaseSupportFragment> b;

    public FirstPageAdapter(FragmentManager fragmentManager, ArrayList<CateBean> arrayList, ArrayList<BaseSupportFragment> arrayList2) {
        super(fragmentManager);
        this.a = arrayList;
        this.b = arrayList2;
    }

    public int getCount() {
        return this.a.size();
    }

    public CharSequence getPageTitle(int i) {
        return ((CateBean) this.a.get(i)).getCatname();
    }

    public Fragment getItem(int i) {
        return (Fragment) this.b.get(i);
    }

    public int getItemPosition(Object obj) {
        return -2;
    }
}
