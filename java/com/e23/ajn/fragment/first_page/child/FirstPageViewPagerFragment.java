package com.e23.ajn.fragment.first_page.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.baidu.mobstat.StatService;
import com.e23.ajn.R;
import com.e23.ajn.activity.SwipeBackCommonActivity;
import com.e23.ajn.adapter.FirstPageAdapter;
import com.e23.ajn.b.e;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.c.a;
import com.e23.ajn.d.p;
import com.e23.ajn.model.CateBean;
import com.e23.ajn.model.CateListCache;
import com.e23.ajn.model.CateListData;
import com.e23.ajn.model.CateListResponseModel;
import com.e23.ajn.views.k;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.a.b;
import com.tencent.android.tpush.common.MessageKey;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.Call;
import okhttp3.Request;
import org.greenrobot.eventbus.j;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

public class FirstPageViewPagerFragment extends BaseSupportFragment implements OnClickListener, b {
    private ViewPager a;
    /* access modifiers changed from: private */
    public SlidingTabLayout d;
    private ImageView e;
    private RelativeLayout f;
    /* access modifiers changed from: private */
    public FirstPageAdapter g;
    /* access modifiers changed from: private */
    public ArrayList<BaseSupportFragment> h;
    /* access modifiers changed from: private */
    public ArrayList<CateBean> i;
    /* access modifiers changed from: private */
    public boolean j = false;
    private String k;

    public static FirstPageViewPagerFragment h() {
        Bundle bundle = new Bundle();
        FirstPageViewPagerFragment firstPageViewPagerFragment = new FirstPageViewPagerFragment();
        firstPageViewPagerFragment.setArguments(bundle);
        return firstPageViewPagerFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.f102emgrid_item, viewGroup, false);
        a(inflate);
        e.a(this.c).a((Object) this);
        i();
        return inflate;
    }

    private void a(View view) {
        this.a = (ViewPager) view.findViewById(R.id.first_page_viewPager);
        this.d = (SlidingTabLayout) view.findViewById(R.id.fragment_viewpager_program);
        this.f = (RelativeLayout) view.findViewById(R.id.top_layout);
        this.d.setOnTabSelectListener(this);
        this.e = (ImageView) view.findViewById(R.id.fragment_viewpager_program_management);
        this.e.setOnClickListener(this);
        this.e.setEnabled(false);
        this.h = new ArrayList<>();
        this.i = new ArrayList<>();
        if (p.a("is_red", false)) {
            this.d.setBackgroundColor(getResources().getColor(R.color.colorRed));
            this.d.setTextSelectColor(getResources().getColor(R.color.colorWhite));
            this.d.setTextUnselectColor(getResources().getColor(R.color.colorWhite));
            this.d.setIndicatorColor(getResources().getColor(R.color.colorWhite));
            this.e.setBackgroundColor(getResources().getColor(R.color.colorRed));
            this.e.setImageResource(R.mipmap.f286rb_db_seleted);
            this.f.setBackgroundColor(getResources().getColor(R.color.colorRed));
            return;
        }
        this.d.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        this.d.setTextSelectColor(getResources().getColor(R.color.colorBlack0));
        this.d.setTextUnselectColor(getResources().getColor(R.color.color_tab_un_select));
        this.d.setIndicatorColor(getResources().getColor(R.color.colorBlue));
        this.e.setImageResource(R.mipmap.f285rb_db);
        this.e.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        this.f.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    public void a(int i2) {
    }

    public void b(int i2) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_viewpager_program_management /*2131820847*/:
                startActivityForResult(new Intent(this.c, SwipeBackCommonActivity.class).putExtra(SwipeBackCommonActivity.TAG, 2), 2);
                return;
            default:
                return;
        }
    }

    private void i() {
        List findAll = DataSupport.findAll(CateListCache.class, true, new long[0]);
        if (com.e23.ajn.d.e.b(findAll)) {
            List list = ((CateListCache) findAll.get(0)).getList();
            if (com.e23.ajn.d.e.b(list)) {
                this.j = true;
                this.k = ((CateListCache) findAll.get(0)).getUpdatetime();
                a(list);
                j();
                return;
            }
            j();
            return;
        }
        j();
    }

    private void j() {
        PostFormBuilder params = ((PostFormBuilder) ((PostFormBuilder) OkHttpUtils.post().url("http://appc.e23.cn/index.php?m=api&c=content&a=cateList_test")).tag(this)).params(com.e23.ajn.c.b.a(null));
        if (this.j && !TextUtils.isEmpty(this.k)) {
            params.addParams("updatetime", this.k);
        }
        params.build().execute(new a<CateListResponseModel>() {
            public void onBefore(Request request, int i) {
                super.onBefore(request, i);
            }

            public void onError(Call call, Exception exc, int i) {
                Log.d("=====", exc.getMessage());
            }

            /* JADX WARNING: Removed duplicated region for block: B:25:0x009c  */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(com.e23.ajn.model.CateListResponseModel r13, int r14) {
                /*
                    r12 = this;
                    r11 = 0
                    r10 = 4
                    r9 = 2
                    r4 = 1
                    r3 = 0
                    if (r13 == 0) goto L_0x0034
                    int r0 = r13.getCode()
                    r1 = 200(0xc8, float:2.8E-43)
                    if (r0 != r1) goto L_0x0034
                    com.e23.ajn.model.CateListData r0 = r13.getData()
                    if (r0 == 0) goto L_0x0034
                    com.e23.ajn.model.CateListData r0 = r13.getData()
                    java.util.List r0 = r0.getList()
                    boolean r0 = com.e23.ajn.d.e.b(r0)
                    if (r0 == 0) goto L_0x0034
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    boolean r0 = r0.j
                    if (r0 != 0) goto L_0x0035
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.e23.ajn.model.CateListData r1 = r13.getData()
                    r0.a(r1)
                L_0x0034:
                    return
                L_0x0035:
                    android.content.ContentValues r0 = new android.content.ContentValues
                    r0.<init>()
                    java.lang.String r1 = "updatetime"
                    com.e23.ajn.model.CateListData r2 = r13.getData()
                    java.lang.String r2 = r2.getUpdatetime()
                    r0.put(r1, r2)
                    java.lang.Class<com.e23.ajn.model.CateListCache> r1 = com.e23.ajn.model.CateListCache.class
                    java.lang.String[] r2 = new java.lang.String[r3]
                    org.litepal.crud.DataSupport.updateAll(r1, r0, r2)
                    com.e23.ajn.model.CateListData r0 = r13.getData()
                    java.util.List r0 = r0.getAddlist()
                    boolean r1 = com.e23.ajn.d.e.b(r0)
                    if (r1 == 0) goto L_0x00ef
                    java.util.Iterator r5 = r0.iterator()
                    r2 = r3
                L_0x0061:
                    boolean r0 = r5.hasNext()
                    if (r0 == 0) goto L_0x00db
                    java.lang.Object r0 = r5.next()
                    com.e23.ajn.model.CateBean r0 = (com.e23.ajn.model.CateBean) r0
                    java.lang.Class<com.e23.ajn.model.CateBean> r1 = com.e23.ajn.model.CateBean.class
                    long[] r6 = new long[r3]
                    java.util.List r1 = org.litepal.crud.DataSupport.findAll(r1, r6)
                    boolean r6 = com.e23.ajn.d.e.b(r1)
                    if (r6 == 0) goto L_0x02e0
                    java.util.Iterator r6 = r1.iterator()
                L_0x007f:
                    boolean r1 = r6.hasNext()
                    if (r1 == 0) goto L_0x02e0
                    java.lang.Object r1 = r6.next()
                    com.e23.ajn.model.CateBean r1 = (com.e23.ajn.model.CateBean) r1
                    java.lang.String r1 = r1.getCatid()
                    java.lang.String r7 = r0.getCatid()
                    boolean r1 = r1.equals(r7)
                    if (r1 == 0) goto L_0x007f
                    r1 = r4
                L_0x009a:
                    if (r1 != 0) goto L_0x02dd
                    r0.save()
                    int r1 = r0.getSelect()
                    if (r1 != r4) goto L_0x02dd
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    r1.add(r0)
                    int r1 = r0.getFtype()
                    if (r1 != r10) goto L_0x00c4
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r0 = r0.h
                    com.e23.ajn.fragment.first_page.child.SubscriptionsListFragment r1 = com.e23.ajn.fragment.first_page.child.SubscriptionsListFragment.r()
                    r0.add(r1)
                    r0 = r4
                L_0x00c2:
                    r2 = r0
                    goto L_0x0061
                L_0x00c4:
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.h
                    java.lang.String r2 = r0.getCatid()
                    int r0 = r0.getFtype()
                    com.e23.ajn.fragment.first_page.child.NewsListFragment r0 = com.e23.ajn.fragment.first_page.child.NewsListFragment.a(r2, r0, r11, r3, r4)
                    r1.add(r0)
                    r0 = r4
                    goto L_0x00c2
                L_0x00db:
                    if (r2 == 0) goto L_0x00ef
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.e23.ajn.adapter.FirstPageAdapter r0 = r0.g
                    r0.notifyDataSetChanged()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.flyco.tablayout.SlidingTabLayout r0 = r0.d
                    r0.a()
                L_0x00ef:
                    com.e23.ajn.model.CateListData r0 = r13.getData()
                    java.util.List r0 = r0.getUplist()
                    boolean r1 = com.e23.ajn.d.e.b(r0)
                    if (r1 == 0) goto L_0x024f
                    java.util.Iterator r6 = r0.iterator()
                    r2 = r3
                L_0x0102:
                    boolean r0 = r6.hasNext()
                    if (r0 == 0) goto L_0x023b
                    java.lang.Object r0 = r6.next()
                    com.e23.ajn.model.CateBean r0 = (com.e23.ajn.model.CateBean) r0
                    android.content.ContentValues r1 = new android.content.ContentValues
                    r1.<init>()
                    java.lang.String r5 = "catname"
                    java.lang.String r7 = r0.getCatname()
                    r1.put(r5, r7)
                    java.lang.String r5 = "ftype"
                    int r7 = r0.getFtype()
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                    r1.put(r5, r7)
                    java.lang.String r5 = "index"
                    int r7 = r0.getIndex()
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                    r1.put(r5, r7)
                    java.lang.String r5 = "fixed"
                    int r7 = r0.getFixed()
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                    r1.put(r5, r7)
                    java.lang.String r5 = "select"
                    int r7 = r0.getSelect()
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                    r1.put(r5, r7)
                    java.lang.Class<com.e23.ajn.model.CateBean> r5 = com.e23.ajn.model.CateBean.class
                    java.lang.String[] r7 = new java.lang.String[r9]
                    java.lang.String r8 = "catid = ?"
                    r7[r3] = r8
                    java.lang.String r8 = r0.getCatid()
                    r7[r4] = r8
                    org.litepal.crud.DataSupport.updateAll(r5, r1, r7)
                    int r1 = r0.getSelect()
                    if (r1 != 0) goto L_0x01a9
                    r5 = r3
                L_0x0168:
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    int r1 = r1.size()
                    if (r5 >= r1) goto L_0x01a1
                    java.lang.String r7 = r0.getCatid()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    java.lang.Object r1 = r1.get(r5)
                    com.e23.ajn.model.CateBean r1 = (com.e23.ajn.model.CateBean) r1
                    java.lang.String r1 = r1.getCatid()
                    boolean r1 = r7.equals(r1)
                    if (r1 == 0) goto L_0x01a5
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r0 = r0.i
                    r0.remove(r5)
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r0 = r0.h
                    r0.remove(r5)
                    r2 = r4
                L_0x01a1:
                    r0 = r2
                L_0x01a2:
                    r2 = r0
                    goto L_0x0102
                L_0x01a5:
                    int r1 = r5 + 1
                    r5 = r1
                    goto L_0x0168
                L_0x01a9:
                    r5 = r3
                L_0x01aa:
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    int r1 = r1.size()
                    if (r5 >= r1) goto L_0x02da
                    java.lang.String r7 = r0.getCatid()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    java.lang.Object r1 = r1.get(r5)
                    com.e23.ajn.model.CateBean r1 = (com.e23.ajn.model.CateBean) r1
                    java.lang.String r1 = r1.getCatid()
                    boolean r1 = r7.equals(r1)
                    if (r1 == 0) goto L_0x021f
                    java.lang.String r7 = r0.getCatname()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    java.lang.Object r1 = r1.get(r5)
                    com.e23.ajn.model.CateBean r1 = (com.e23.ajn.model.CateBean) r1
                    java.lang.String r1 = r1.getCatname()
                    boolean r1 = r7.equals(r1)
                    if (r1 != 0) goto L_0x01fe
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    java.lang.Object r1 = r1.get(r5)
                    com.e23.ajn.model.CateBean r1 = (com.e23.ajn.model.CateBean) r1
                    java.lang.String r2 = r0.getCatname()
                    r1.setCatname(r2)
                    r2 = r4
                L_0x01fe:
                    r1 = r4
                L_0x01ff:
                    if (r1 != 0) goto L_0x02d7
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    r1.add(r0)
                    int r1 = r0.getFtype()
                    if (r1 != r10) goto L_0x0223
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r0 = r0.h
                    com.e23.ajn.fragment.first_page.child.SubscriptionsListFragment r1 = com.e23.ajn.fragment.first_page.child.SubscriptionsListFragment.r()
                    r0.add(r1)
                    r0 = r4
                    goto L_0x01a2
                L_0x021f:
                    int r1 = r5 + 1
                    r5 = r1
                    goto L_0x01aa
                L_0x0223:
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.h
                    java.lang.String r2 = r0.getCatid()
                    int r0 = r0.getFtype()
                    com.e23.ajn.fragment.first_page.child.NewsListFragment r0 = com.e23.ajn.fragment.first_page.child.NewsListFragment.a(r2, r0, r11, r3, r4)
                    r1.add(r0)
                    r0 = r4
                    goto L_0x01a2
                L_0x023b:
                    if (r2 == 0) goto L_0x024f
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.e23.ajn.adapter.FirstPageAdapter r0 = r0.g
                    r0.notifyDataSetChanged()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.flyco.tablayout.SlidingTabLayout r0 = r0.d
                    r0.a()
                L_0x024f:
                    com.e23.ajn.model.CateListData r0 = r13.getData()
                    java.util.List r0 = r0.getDellist()
                    boolean r1 = com.e23.ajn.d.e.b(r0)
                    if (r1 == 0) goto L_0x0034
                    java.util.Iterator r6 = r0.iterator()
                    r2 = r3
                L_0x0262:
                    boolean r0 = r6.hasNext()
                    if (r0 == 0) goto L_0x02bf
                    java.lang.Object r0 = r6.next()
                    com.e23.ajn.model.CateBean r0 = (com.e23.ajn.model.CateBean) r0
                    java.lang.Class<com.e23.ajn.model.CateBean> r1 = com.e23.ajn.model.CateBean.class
                    java.lang.String[] r5 = new java.lang.String[r9]
                    java.lang.String r7 = "catid = ?"
                    r5[r3] = r7
                    java.lang.String r7 = r0.getCatid()
                    r5[r4] = r7
                    org.litepal.crud.DataSupport.deleteAll(r1, r5)
                    r5 = r3
                L_0x0280:
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    int r1 = r1.size()
                    if (r5 >= r1) goto L_0x02d5
                    java.lang.String r7 = r0.getCatid()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r1 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r1 = r1.i
                    java.lang.Object r1 = r1.get(r5)
                    com.e23.ajn.model.CateBean r1 = (com.e23.ajn.model.CateBean) r1
                    java.lang.String r1 = r1.getCatid()
                    boolean r1 = r7.equals(r1)
                    if (r1 == 0) goto L_0x02bb
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r0 = r0.i
                    r0.remove(r5)
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    java.util.ArrayList r0 = r0.h
                    r0.remove(r5)
                    r0 = r4
                L_0x02b9:
                    r2 = r0
                    goto L_0x0262
                L_0x02bb:
                    int r1 = r5 + 1
                    r5 = r1
                    goto L_0x0280
                L_0x02bf:
                    if (r2 == 0) goto L_0x0034
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.e23.ajn.adapter.FirstPageAdapter r0 = r0.g
                    r0.notifyDataSetChanged()
                    com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment r0 = com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.this
                    com.flyco.tablayout.SlidingTabLayout r0 = r0.d
                    r0.a()
                    goto L_0x0034
                L_0x02d5:
                    r0 = r2
                    goto L_0x02b9
                L_0x02d7:
                    r0 = r2
                    goto L_0x01a2
                L_0x02da:
                    r1 = r3
                    goto L_0x01ff
                L_0x02dd:
                    r0 = r2
                    goto L_0x00c2
                L_0x02e0:
                    r1 = r3
                    goto L_0x009a
                */
                throw new UnsupportedOperationException("Method not decompiled: com.e23.ajn.fragment.first_page.child.FirstPageViewPagerFragment.AnonymousClass1.onResponse(com.e23.ajn.model.CateListResponseModel, int):void");
            }

            public void onAfter(int i) {
                super.onAfter(i);
                k.a();
            }
        });
    }

    private void a(List<CateBean> list) {
        this.i.clear();
        this.h.clear();
        for (CateBean cateBean : list) {
            if (cateBean.getSelect() == 1) {
                this.i.add(cateBean);
                if (cateBean.getFtype() == 4) {
                    this.h.add(SubscriptionsListFragment.r());
                } else {
                    this.h.add(NewsListFragment.a(cateBean.getCatid(), cateBean.getFtype(), null, false, true));
                }
            }
        }
        this.g = new FirstPageAdapter(getChildFragmentManager(), this.i, this.h);
        this.a.setAdapter(this.g);
        this.a.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                FirstPageViewPagerFragment.this.a(((CateBean) FirstPageViewPagerFragment.this.i.get(i)).getCatname());
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.d.setViewPager(this.a);
        this.e.setEnabled(true);
        if (com.e23.ajn.d.e.b((List) this.i)) {
            a(((CateBean) this.i.get(0)).getCatname());
        }
    }

    /* access modifiers changed from: private */
    public void a(final CateListData cateListData) {
        final List<CateBean> list = cateListData.getList();
        this.i.clear();
        this.h.clear();
        DataSupport.deleteAllAsync(CateListCache.class, new String[0]).listen(new UpdateOrDeleteCallback() {
            public void onFinish(int i) {
                CateListCache cateListCache = new CateListCache();
                cateListCache.setUpdatetime(cateListData.getUpdatetime());
                for (CateBean cateBean : list) {
                    cateBean.clearSavedState();
                    cateBean.save();
                    cateListCache.getList().add(cateBean);
                }
                cateListCache.save();
            }
        });
        for (CateBean cateBean : list) {
            if (cateBean.getSelect() == 1) {
                this.i.add(cateBean);
                if (cateBean.getFtype() == 4) {
                    this.h.add(SubscriptionsListFragment.r());
                } else {
                    this.h.add(NewsListFragment.a(cateBean.getCatid(), cateBean.getFtype(), null, false, true));
                }
            }
        }
        this.g = new FirstPageAdapter(getChildFragmentManager(), this.i, this.h);
        this.a.setAdapter(this.g);
        this.d.setViewPager(this.a);
        this.e.setEnabled(true);
        if (com.e23.ajn.d.e.b((List) this.i)) {
            a(((CateBean) this.i.get(0)).getCatname());
        }
    }

    @j
    public void onCateChangedEvent(com.e23.ajn.b.a aVar) {
        int i2 = 0;
        switch (aVar.a) {
            case 1:
                if (aVar.b != null) {
                    this.i.add((CateBean) aVar.b);
                    if (((CateBean) aVar.b).getFtype() == 4) {
                        this.h.add(SubscriptionsListFragment.r());
                    } else {
                        this.h.add(NewsListFragment.a(((CateBean) aVar.b).getCatid(), ((CateBean) aVar.b).getFtype(), null, false, true));
                    }
                    this.g.notifyDataSetChanged();
                    this.d.a();
                    ((BaseSupportFragment) this.h.get(this.a.getCurrentItem())).c();
                    return;
                }
                return;
            case 2:
                if (aVar.b != null) {
                    while (true) {
                        if (i2 < this.i.size()) {
                            if (((CateBean) this.i.get(i2)).getCatid().equals(((CateBean) aVar.b).getCatid())) {
                                this.i.remove(i2);
                                this.h.remove(i2);
                            } else {
                                i2++;
                            }
                        }
                    }
                    this.g.notifyDataSetChanged();
                    this.d.a();
                    ((BaseSupportFragment) this.h.get(this.a.getCurrentItem())).c();
                    return;
                }
                return;
            case 3:
                if (aVar.b != null) {
                    int intValue = ((Integer) ((HashMap) aVar.b).get("pre")).intValue();
                    int intValue2 = ((Integer) ((HashMap) aVar.b).get(MessageKey.MSG_ACCEPT_TIME_END)).intValue();
                    if (intValue < intValue2) {
                        this.i.add(intValue2 + 1, this.i.get(intValue));
                        this.i.remove(intValue);
                        this.h.add(intValue2 + 1, this.h.get(intValue));
                        this.h.remove(intValue);
                    } else if (intValue > intValue2) {
                        this.i.add(intValue2, this.i.get(intValue));
                        this.i.remove(intValue + 1);
                        this.h.add(intValue2, this.h.get(intValue));
                        this.h.remove(intValue + 1);
                    }
                    this.g.notifyDataSetChanged();
                    this.d.a();
                    ((BaseSupportFragment) this.h.get(this.a.getCurrentItem())).c();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        e.a(this.c).b(this);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("cat_name", str);
        StatService.onEvent(this.c, "A1", "栏目", 1, hashMap);
    }
}
