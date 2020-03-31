package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.l;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.e23.ajn.R;
import com.e23.ajn.adapter.LocationListAdapter;
import java.util.ArrayList;

/* compiled from: LocationDialog */
public class j extends Dialog implements OnClickListener {
    private Context a;
    private RecyclerView b;
    private Button c;
    private RelativeLayout d;
    /* access modifiers changed from: private */
    public ArrayList<String> e;
    /* access modifiers changed from: private */
    public LocationListAdapter f;
    /* access modifiers changed from: private */
    public a g;

    /* compiled from: LocationDialog */
    public interface a {
        void a(String str);
    }

    public j(Context context) {
        super(context, R.style.share_dialog);
        this.a = context;
        setContentView(R.layout.f195pltome_item);
        setCanceledOnTouchOutside(true);
        a();
    }

    public void a(ArrayList<String> arrayList, String str) {
        this.e = arrayList;
        this.f.a(str);
        this.f.setNewData(arrayList);
    }

    private void a() {
        this.d = (RelativeLayout) findViewById(R.id.bg_layout);
        this.b = (RecyclerView) findViewById(R.id.location_list);
        this.c = (Button) findViewById(R.id.location_cancel);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.setFocusableInTouchMode(false);
        this.b.setHasFixedSize(true);
        this.b.setLayoutManager(new WrapContentLinearLayoutManager(this.a));
        this.f = new LocationListAdapter(this.a, null);
        this.b.setAdapter(this.f);
        this.b.a((l) new OnItemClickListener() {
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                j.this.f.a((String) j.this.e.get(i));
                j.this.f.notifyDataSetChanged();
                j.this.g.a((String) j.this.e.get(i));
                j.this.dismiss();
            }
        });
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        Window window = getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.share_dialog_animation);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bg_layout /*2131821126*/:
                dismiss();
                return;
            case R.id.location_cancel /*2131821130*/:
                dismiss();
                return;
            default:
                return;
        }
    }

    public void a(a aVar) {
        this.g = aVar;
    }
}
