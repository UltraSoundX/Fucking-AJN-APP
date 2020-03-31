package com.lzy.imagepicker.ui;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.a;
import com.lzy.imagepicker.adapter.ImagePageAdapter;
import com.lzy.imagepicker.b;
import com.lzy.imagepicker.b.d;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.ViewPagerFixed;
import java.util.ArrayList;

public abstract class ImagePreviewBaseActivity extends ImageBaseActivity {
    protected b b;
    protected ArrayList<ImageItem> c;
    protected int d = 0;
    protected TextView e;
    protected ArrayList<ImageItem> f;
    protected View g;
    protected View h;
    protected ViewPagerFixed i;
    protected ImagePageAdapter j;
    protected boolean k = false;

    public abstract void onImageSingleTap();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_preview);
        this.d = getIntent().getIntExtra("selected_image_position", 0);
        this.k = getIntent().getBooleanExtra("extra_from_items", false);
        if (this.k) {
            this.c = (ArrayList) getIntent().getSerializableExtra("extra_image_items");
        } else {
            this.c = (ArrayList) a.a().a("dh_current_image_folder_items");
        }
        this.b = b.a();
        this.f = this.b.p();
        this.g = findViewById(R.id.content);
        this.h = findViewById(R.id.top_bar);
        if (VERSION.SDK_INT >= 19) {
            LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
            layoutParams.topMargin = d.a((Context) this);
            this.h.setLayoutParams(layoutParams);
        }
        this.h.findViewById(R.id.btn_ok).setVisibility(8);
        this.h.findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImagePreviewBaseActivity.this.finish();
            }
        });
        this.e = (TextView) findViewById(R.id.tv_des);
        this.i = (ViewPagerFixed) findViewById(R.id.viewpager);
        this.j = new ImagePageAdapter(this, this.c);
        this.j.a((ImagePageAdapter.a) new ImagePageAdapter.a() {
            public void a(View view, float f, float f2) {
                ImagePreviewBaseActivity.this.onImageSingleTap();
            }
        });
        this.i.setAdapter(this.j);
        this.i.setCurrentItem(this.d, false);
        this.e.setText(getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(this.d + 1), Integer.valueOf(this.c.size())}));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        b.a().a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.a().b(bundle);
    }
}
