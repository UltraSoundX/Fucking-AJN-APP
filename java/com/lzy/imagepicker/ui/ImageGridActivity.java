package com.lzy.imagepicker.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.h;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import com.lzy.imagepicker.ImageDataSource;
import com.lzy.imagepicker.ImageDataSource.a;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.adapter.b.c;
import com.lzy.imagepicker.b;
import com.lzy.imagepicker.b.d;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.a.C0054a;
import com.stub.StubApp;
import java.util.List;

public class ImageGridActivity extends ImageBaseActivity implements OnClickListener, a, c, b.a {
    public static final String EXTRAS_IMAGES = "IMAGES";
    public static final String EXTRAS_TAKE_PICKERS = "TAKE";
    public static final int REQUEST_PERMISSION_CAMERA = 2;
    public static final int REQUEST_PERMISSION_STORAGE = 1;
    /* access modifiers changed from: private */
    public b b;
    private boolean c = false;
    private View d;
    private Button e;
    private View f;
    /* access modifiers changed from: private */
    public TextView g;
    private TextView h;
    /* access modifiers changed from: private */
    public com.lzy.imagepicker.adapter.a i;
    /* access modifiers changed from: private */
    public com.lzy.imagepicker.view.a j;
    private List<com.lzy.imagepicker.bean.a> k;
    private boolean l = false;
    private RecyclerView m;
    /* access modifiers changed from: private */
    public com.lzy.imagepicker.adapter.b n;

    static {
        StubApp.interface11(4193);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.l = bundle.getBoolean(EXTRAS_TAKE_PICKERS, false);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRAS_TAKE_PICKERS, this.l);
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                showToast("权限被禁止，无法选择本地图片");
            } else {
                new ImageDataSource(this, null, this);
            }
        } else if (i2 != 2) {
        } else {
            if (iArr.length <= 0 || iArr[0] != 0) {
                showToast("权限被禁止，无法打开相机");
            } else {
                this.b.a((Activity) this, (int) PointerIconCompat.TYPE_CONTEXT_MENU);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.b.b((b.a) this);
        super.onDestroy();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            Intent intent = new Intent();
            intent.putExtra("extra_result_items", this.b.p());
            setResult(PointerIconCompat.TYPE_WAIT, intent);
            finish();
        } else if (id == R.id.ll_dir) {
            if (this.k == null) {
                Log.i("ImageGridActivity", "您的手机没有图片");
                return;
            }
            a();
            this.i.a(this.k);
            if (this.j.isShowing()) {
                this.j.dismiss();
                return;
            }
            this.j.showAtLocation(this.d, 0, 0, 0);
            int a = this.i.a();
            if (a != 0) {
                a--;
            }
            this.j.a(a);
        } else if (id == R.id.btn_preview) {
            Intent intent2 = new Intent(this, ImagePreviewActivity.class);
            intent2.putExtra("selected_image_position", 0);
            intent2.putExtra("extra_image_items", this.b.p());
            intent2.putExtra(ImagePreviewActivity.ISORIGIN, this.c);
            intent2.putExtra("extra_from_items", true);
            startActivityForResult(intent2, PointerIconCompat.TYPE_HELP);
        } else if (id == R.id.btn_back) {
            finish();
        }
    }

    private void a() {
        this.j = new com.lzy.imagepicker.view.a(this, this.i);
        this.j.a((C0054a) new C0054a() {
            public void a(AdapterView<?> adapterView, View view, int i, long j) {
                ImageGridActivity.this.i.b(i);
                ImageGridActivity.this.b.f(i);
                ImageGridActivity.this.j.dismiss();
                com.lzy.imagepicker.bean.a aVar = (com.lzy.imagepicker.bean.a) adapterView.getAdapter().getItem(i);
                if (aVar != null) {
                    ImageGridActivity.this.n.a(aVar.d);
                    ImageGridActivity.this.g.setText(aVar.a);
                }
            }
        });
        this.j.b(this.d.getHeight());
    }

    public void onImagesLoaded(List<com.lzy.imagepicker.bean.a> list) {
        this.k = list;
        this.b.a(list);
        if (list.size() == 0) {
            this.n.a(null);
        } else {
            this.n.a(((com.lzy.imagepicker.bean.a) list.get(0)).d);
        }
        this.n.a((c) this);
        this.m.setLayoutManager(new GridLayoutManager(this, 3));
        this.m.a((h) new com.lzy.imagepicker.view.b(3, d.a(this, 2.0f), false));
        this.m.setAdapter(this.n);
        this.i.a(list);
    }

    public void onImageItemClick(View view, ImageItem imageItem, int i2) {
        if (this.b.e()) {
            i2--;
        }
        if (this.b.b()) {
            Intent intent = new Intent(this, ImagePreviewActivity.class);
            intent.putExtra("selected_image_position", i2);
            com.lzy.imagepicker.a.a().a("dh_current_image_folder_items", this.b.n());
            intent.putExtra(ImagePreviewActivity.ISORIGIN, this.c);
            startActivityForResult(intent, PointerIconCompat.TYPE_HELP);
            return;
        }
        this.b.q();
        this.b.a(i2, (ImageItem) this.b.n().get(i2), true);
        if (this.b.d()) {
            startActivityForResult(new Intent(this, ImageCropActivity.class), PointerIconCompat.TYPE_HAND);
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("extra_result_items", this.b.p());
        setResult(PointerIconCompat.TYPE_WAIT, intent2);
        finish();
    }

    @SuppressLint({"StringFormatMatches"})
    public void onImageSelected(int i2, ImageItem imageItem, boolean z) {
        int i3 = 1;
        if (this.b.o() > 0) {
            this.e.setText(getString(R.string.ip_select_complete, new Object[]{Integer.valueOf(this.b.o()), Integer.valueOf(this.b.c())}));
            this.e.setEnabled(true);
            this.h.setEnabled(true);
            this.h.setText(getResources().getString(R.string.ip_preview_count, new Object[]{Integer.valueOf(this.b.o())}));
            this.h.setTextColor(ContextCompat.getColor(this, R.color.ip_text_primary_inverted));
            this.e.setTextColor(ContextCompat.getColor(this, R.color.ip_text_primary_inverted));
        } else {
            this.e.setText(getString(R.string.ip_complete));
            this.e.setEnabled(false);
            this.h.setEnabled(false);
            this.h.setText(getResources().getString(R.string.ip_preview));
            this.h.setTextColor(ContextCompat.getColor(this, R.color.ip_text_secondary_inverted));
            this.e.setTextColor(ContextCompat.getColor(this, R.color.ip_text_secondary_inverted));
        }
        if (!this.b.e()) {
            i3 = 0;
        }
        while (i3 < this.n.getItemCount()) {
            if (this.n.a(i3).b == null || !this.n.a(i3).b.equals(imageItem.b)) {
                i3++;
            } else {
                this.n.notifyItemChanged(i3);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent == null || intent.getExtras() == null) {
            if (i3 == -1 && i2 == 1001) {
                b.a((Context) this, this.b.k());
                String absolutePath = this.b.k().getAbsolutePath();
                ImageItem imageItem = new ImageItem();
                imageItem.b = absolutePath;
                this.b.q();
                this.b.a(0, imageItem, true);
                if (this.b.d()) {
                    startActivityForResult(new Intent(this, ImageCropActivity.class), PointerIconCompat.TYPE_HAND);
                    return;
                }
                Intent intent2 = new Intent();
                intent2.putExtra("extra_result_items", this.b.p());
                setResult(PointerIconCompat.TYPE_WAIT, intent2);
                finish();
            } else if (this.l) {
                finish();
            }
        } else if (i3 == 1005) {
            this.c = intent.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
        } else {
            if (intent.getSerializableExtra("extra_result_items") != null) {
                setResult(PointerIconCompat.TYPE_WAIT, intent);
            }
            finish();
        }
    }
}
