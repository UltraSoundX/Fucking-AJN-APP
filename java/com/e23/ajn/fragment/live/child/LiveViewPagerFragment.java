package com.e23.ajn.fragment.live.child;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.mobstat.StatService;
import com.e23.ajn.R;
import com.e23.ajn.adapter.FirstPageAdapter;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.d.e;
import com.e23.ajn.model.CateBean;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.a.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LiveViewPagerFragment extends BaseSupportFragment implements b {
    private ViewPager a;
    private SlidingTabLayout d;
    private FirstPageAdapter e;
    private ArrayList<BaseSupportFragment> f;
    /* access modifiers changed from: private */
    public ArrayList<CateBean> g;
    private CateBean h;
    private String[] i = {"推荐", "政务", "综合", "区县", "社会", "活动", "拍客"};
    private String[] j = {"1", "2", "6", "3", "4", "5", "7"};

    public static LiveViewPagerFragment h() {
        Bundle bundle = new Bundle();
        LiveViewPagerFragment liveViewPagerFragment = new LiveViewPagerFragment();
        liveViewPagerFragment.setArguments(bundle);
        return liveViewPagerFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f136fxdialog, viewGroup, false);
        a(inflate);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.i.length; i2++) {
            this.h = new CateBean();
            this.h.setCatname(this.i[i2]);
            this.h.setCatid(this.j[i2]);
            arrayList.add(this.h);
        }
        a((List<CateBean>) arrayList);
        return inflate;
    }

    private void a(View view) {
        this.a = (ViewPager) view.findViewById(R.id.first_page_viewPager);
        this.d = (SlidingTabLayout) view.findViewById(R.id.fragment_viewpager_program);
        this.d.setOnTabSelectListener(this);
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
    }

    public void a(int i2) {
    }

    public void b(int i2) {
    }

    private void a(List<CateBean> list) {
        this.g.clear();
        this.f.clear();
        for (CateBean cateBean : list) {
            this.g.add(cateBean);
            this.f.add(LiveListFragment.a(cateBean.getCatid()));
        }
        this.e = new FirstPageAdapter(getChildFragmentManager(), this.g, this.f);
        this.a.setAdapter(this.e);
        this.a.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                LiveViewPagerFragment.this.a(((CateBean) LiveViewPagerFragment.this.g.get(i)).getCatname());
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.d.setViewPager(this.a);
        if (e.b((List) this.g)) {
            a(((CateBean) this.g.get(0)).getCatname());
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("cat_name", "直播" + str);
        StatService.onEvent(this.c, "A1", "直播栏目", 1, hashMap);
    }
}
