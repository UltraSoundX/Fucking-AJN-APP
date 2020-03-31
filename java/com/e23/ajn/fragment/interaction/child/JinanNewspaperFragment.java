package com.e23.ajn.fragment.interaction.child;

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
import java.util.Iterator;
import java.util.List;

public class JinanNewspaperFragment extends BaseSupportFragment implements b {
    private ViewPager a;
    private SlidingTabLayout d;
    private FirstPageAdapter e;
    private ArrayList<BaseSupportFragment> f;
    /* access modifiers changed from: private */
    public ArrayList<CateBean> g;
    private String[] h = {"济南日报", "济南时报", "舜网", "爱济南", "健康报", "都市女报", "鲁中分社"};
    private String[] i = {"1", "2", "3", "4", "5", "6", "10"};

    public static JinanNewspaperFragment h() {
        Bundle bundle = new Bundle();
        JinanNewspaperFragment jinanNewspaperFragment = new JinanNewspaperFragment();
        jinanNewspaperFragment.setArguments(bundle);
        return jinanNewspaperFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f169myorder, viewGroup, false);
        a(inflate);
        i();
        return inflate;
    }

    private void a(View view) {
        this.a = (ViewPager) view.findViewById(R.id.first_page_viewPager);
        this.d = (SlidingTabLayout) view.findViewById(R.id.fragment_viewpager_program);
        this.d.setOnTabSelectListener(this);
        this.f = new ArrayList<>();
    }

    public void a(int i2) {
    }

    public void b(int i2) {
    }

    private void i() {
        this.g = new ArrayList<>();
        for (int i2 = 0; i2 < this.h.length; i2++) {
            CateBean cateBean = new CateBean();
            cateBean.setCatid(this.i[i2]);
            cateBean.setCatname(this.h[i2]);
            this.g.add(cateBean);
        }
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            CateBean cateBean2 = (CateBean) it.next();
            this.f.add(ReporterListFragment.a(cateBean2.getCatid(), cateBean2.getCatname()));
        }
        this.e = new FirstPageAdapter(getChildFragmentManager(), this.g, this.f);
        this.a.setAdapter(this.e);
        this.a.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                JinanNewspaperFragment.this.a(((CateBean) JinanNewspaperFragment.this.g.get(i)).getCatname());
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.d.setViewPager(this.a);
        if (e.b((List) this.g)) {
            a(((CateBean) this.g.get(0)).getCatname());
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("cat_name", str);
        StatService.onEvent(this.c, "A1", "栏目", 1, hashMap);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
