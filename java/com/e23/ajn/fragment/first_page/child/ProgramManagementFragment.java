package com.e23.ajn.fragment.first_page.child;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.v;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.a.a;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.e23.ajn.R;
import com.e23.ajn.adapter.ProgramManageAdapter;
import com.e23.ajn.b.e;
import com.e23.ajn.base.BaseSupportFragment;
import com.e23.ajn.model.CateBean;
import com.e23.ajn.model.CateListCache;
import com.tencent.android.tpush.common.MessageKey;
import java.util.HashMap;
import java.util.List;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

public class ProgramManagementFragment extends BaseSupportFragment implements OnClickListener {
    private View a;
    private Toolbar d;
    private TextView e;
    /* access modifiers changed from: private */
    public TextView f;
    private RecyclerView g;
    private RecyclerView h;
    private ImageView i;
    /* access modifiers changed from: private */
    public ProgramManageAdapter j;
    /* access modifiers changed from: private */
    public ProgramManageAdapter k;
    private a l;
    private ItemDragAndSwipeCallback m;
    /* access modifiers changed from: private */
    public List<CateBean> n;
    /* access modifiers changed from: private */
    public List<CateBean> o;

    public static ProgramManagementFragment h() {
        ProgramManagementFragment programManagementFragment = new ProgramManagementFragment();
        programManagementFragment.setArguments(new Bundle());
        return programManagementFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(R.layout.f149item_popup, viewGroup, false);
        j();
        i();
        return this.a;
    }

    private void i() {
        com.jaeger.library.a.b(this.c, 0, null);
        com.jaeger.library.a.a((Activity) this.c);
        this.d = (Toolbar) this.a.findViewById(R.id.toolbar);
        this.i = (ImageView) this.a.findViewById(R.id.toolbar_right_close);
        this.e = (TextView) this.d.findViewById(R.id.toolbar_center_title);
        this.i.setOnClickListener(this);
        this.e.setText(getResources().getString(R.string.all_channel));
        this.f = (TextView) this.a.findViewById(R.id.fragment_program_add_channel);
        this.g = (RecyclerView) this.a.findViewById(R.id.program_management_recyclerView_mine);
        this.g.setLayoutManager(new GridLayoutManager(this.c, 4));
        this.h = (RecyclerView) this.a.findViewById(R.id.program_management_recyclerView_all);
        this.h.setLayoutManager(new GridLayoutManager(this.c, 4));
        this.j = new ProgramManageAdapter(this.c, this.n);
        this.k = new ProgramManageAdapter(this.c, this.o);
        AnonymousClass1 r0 = new OnItemDragListener() {
            int a;
            int b;

            public void onItemDragStart(v vVar, int i) {
                this.a = i;
                ((BaseViewHolder) vVar).setBackgroundRes(R.id.program_management_tv_name, R.drawable.f59ssdk_title_div);
            }

            public void onItemDragMoving(v vVar, int i, v vVar2, int i2) {
            }

            public void onItemDragEnd(v vVar, int i) {
                this.b = i;
                ((BaseViewHolder) vVar).setBackgroundRes(R.id.program_management_tv_name, R.drawable.f59ssdk_title_div);
                if (this.a != this.b) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("pre", Integer.valueOf(this.a));
                    hashMap.put(MessageKey.MSG_ACCEPT_TIME_END, Integer.valueOf(this.b));
                    e.a(ProgramManagementFragment.this.c).c(new com.e23.ajn.b.a(3, hashMap));
                    ProgramManagementFragment.this.k();
                }
            }
        };
        this.m = new ItemDragAndSwipeCallback(this.j);
        this.l = new a(this.m);
        this.l.a(this.g);
        this.j.enableDragItem(this.l);
        this.j.setOnItemDragListener(r0);
        this.g.setAdapter(this.j);
        this.h.setAdapter(this.k);
        if (this.k.getData().size() == 0) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
        }
        this.j.a((ProgramManageAdapter.a) new ProgramManageAdapter.a() {
            public void a(int i) {
                CateBean cateBean = (CateBean) ProgramManagementFragment.this.j.getItem(i);
                cateBean.setSelect(0);
                ProgramManagementFragment.this.n.remove(i);
                ProgramManagementFragment.this.o.add(cateBean);
                ProgramManagementFragment.this.j.notifyDataSetChanged();
                ProgramManagementFragment.this.k.notifyDataSetChanged();
                if (ProgramManagementFragment.this.k.getData().size() == 0) {
                    ProgramManagementFragment.this.f.setVisibility(8);
                } else {
                    ProgramManagementFragment.this.f.setVisibility(0);
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("select", Integer.valueOf(0));
                DataSupport.updateAll(CateBean.class, contentValues, "catid = ?", cateBean.getCatid());
                e.a(ProgramManagementFragment.this.c).c(new com.e23.ajn.b.a(2, cateBean));
            }
        });
        this.h.a((l) new OnItemClickListener() {
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CateBean cateBean = (CateBean) ProgramManagementFragment.this.k.getItem(i);
                cateBean.setSelect(1);
                ProgramManagementFragment.this.n.add(cateBean);
                ProgramManagementFragment.this.o.remove(i);
                ProgramManagementFragment.this.j.notifyDataSetChanged();
                ProgramManagementFragment.this.k.notifyDataSetChanged();
                if (ProgramManagementFragment.this.k.getData().size() == 0) {
                    ProgramManagementFragment.this.f.setVisibility(8);
                } else {
                    ProgramManagementFragment.this.f.setVisibility(0);
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("select", Integer.valueOf(1));
                DataSupport.updateAll(CateBean.class, contentValues, "catid = ?", cateBean.getCatid());
                e.a(ProgramManagementFragment.this.c).c(new com.e23.ajn.b.a(1, cateBean));
            }
        });
    }

    private void j() {
        this.n = DataSupport.where("select = ?", "1").find(CateBean.class);
        this.o = DataSupport.where("select = ?", "0").find(CateBean.class);
    }

    /* access modifiers changed from: private */
    public void k() {
        List findAll = DataSupport.findAll(CateListCache.class, new long[0]);
        final String str = "";
        if (com.e23.ajn.d.e.b(findAll)) {
            str = ((CateListCache) findAll.get(0)).getUpdatetime();
        }
        DataSupport.deleteAllAsync(CateListCache.class, new String[0]).listen(new UpdateOrDeleteCallback() {
            public void onFinish(int i) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        CateListCache cateListCache = new CateListCache();
                        cateListCache.setUpdatetime(str);
                        for (CateBean cateBean : ProgramManagementFragment.this.n) {
                            cateBean.clearSavedState();
                            cateBean.save();
                            cateListCache.getList().add(cateBean);
                        }
                        for (CateBean cateBean2 : ProgramManagementFragment.this.o) {
                            cateBean2.clearSavedState();
                            cateBean2.save();
                            cateListCache.getList().add(cateBean2);
                        }
                        cateListCache.save();
                    }
                }, 1000);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right_close /*2131821165*/:
                this.c.onBackPressed();
                return;
            default:
                return;
        }
    }
}
