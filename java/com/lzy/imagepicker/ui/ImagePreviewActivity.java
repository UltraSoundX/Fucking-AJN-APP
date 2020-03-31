package com.lzy.imagepicker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.b.a;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.SuperCheckBox;
import com.stub.StubApp;
import java.util.Iterator;

public class ImagePreviewActivity extends ImagePreviewBaseActivity implements OnClickListener, OnCheckedChangeListener, a {
    public static final String ISORIGIN = "isOrigin";
    private boolean l;
    /* access modifiers changed from: private */
    public SuperCheckBox m;
    private SuperCheckBox n;
    private Button o;
    /* access modifiers changed from: private */
    public View p;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public View f423q;

    static {
        StubApp.interface11(4198);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onImageSelected(int i, ImageItem imageItem, boolean z) {
        if (this.b.o() > 0) {
            this.o.setText(getString(R.string.ip_select_complete, new Object[]{Integer.valueOf(this.b.o()), Integer.valueOf(this.b.c())}));
        } else {
            this.o.setText(getString(R.string.ip_complete));
        }
        if (this.n.isChecked()) {
            long j = 0;
            Iterator it = this.f.iterator();
            while (true) {
                long j2 = j;
                if (it.hasNext()) {
                    j = ((ImageItem) it.next()).c + j2;
                } else {
                    String formatFileSize = Formatter.formatFileSize(this, j2);
                    this.n.setText(getString(R.string.ip_origin_size, new Object[]{formatFileSize}));
                    return;
                }
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            if (this.b.p().size() == 0) {
                this.m.setChecked(true);
                this.b.a(this.d, (ImageItem) this.c.get(this.d), this.m.isChecked());
            }
            Intent intent = new Intent();
            intent.putExtra("extra_result_items", this.b.p());
            setResult(PointerIconCompat.TYPE_WAIT, intent);
            finish();
        } else if (id == R.id.btn_back) {
            Intent intent2 = new Intent();
            intent2.putExtra(ISORIGIN, this.l);
            setResult(1005, intent2);
            finish();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(ISORIGIN, this.l);
        setResult(1005, intent);
        finish();
        super.onBackPressed();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() != R.id.cb_origin) {
            return;
        }
        if (z) {
            long j = 0;
            Iterator it = this.f.iterator();
            while (true) {
                long j2 = j;
                if (it.hasNext()) {
                    j = ((ImageItem) it.next()).c + j2;
                } else {
                    String formatFileSize = Formatter.formatFileSize(this, j2);
                    this.l = true;
                    this.n.setText(getString(R.string.ip_origin_size, new Object[]{formatFileSize}));
                    return;
                }
            }
        } else {
            this.l = false;
            this.n.setText(getString(R.string.ip_origin));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.b.b((a) this);
        super.onDestroy();
    }

    public void onImageSingleTap() {
        if (this.h.getVisibility() == 0) {
            this.h.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_out));
            this.p.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
            this.h.setVisibility(8);
            this.p.setVisibility(8);
            this.a.a(0);
            return;
        }
        this.h.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_in));
        this.p.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        this.h.setVisibility(0);
        this.p.setVisibility(0);
        this.a.a(R.color.ip_color_primary_dark);
    }
}
