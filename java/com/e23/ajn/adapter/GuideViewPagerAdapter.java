package com.e23.ajn.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class GuideViewPagerAdapter extends PagerAdapter {
    private List<View> a;

    public GuideViewPagerAdapter(List<View> list) {
        this.a = list;
    }

    public int getCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView((View) this.a.get(i));
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ((ViewPager) viewGroup).addView((View) this.a.get(i), 0);
        return this.a.get(i);
    }
}
