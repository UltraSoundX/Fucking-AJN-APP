package com.lzy.imagepicker.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.View.OnClickListener;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.imagepicker.view.CropImageView.b;
import com.stub.StubApp;
import java.io.File;
import java.util.ArrayList;

public class ImageCropActivity extends ImageBaseActivity implements OnClickListener, b {
    private CropImageView b;
    private Bitmap c;
    private boolean d;
    private int e;
    private int f;
    private ArrayList<ImageItem> g;
    private com.lzy.imagepicker.b h;

    static {
        StubApp.interface11(4191);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i4 <= i2 && i3 <= i) {
            return 1;
        }
        if (i3 > i4) {
            return i3 / i;
        }
        return i4 / i2;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            setResult(0);
            finish();
        } else if (id == R.id.btn_ok) {
            this.b.a(this.h.a((Context) this), this.e, this.f, this.d);
        }
    }

    public void onBitmapSaveSuccess(File file) {
        this.g.remove(0);
        ImageItem imageItem = new ImageItem();
        imageItem.b = file.getAbsolutePath();
        this.g.add(imageItem);
        Intent intent = new Intent();
        intent.putExtra("extra_result_items", this.g);
        setResult(PointerIconCompat.TYPE_WAIT, intent);
        finish();
    }

    public void onBitmapSaveError(File file) {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.b.setOnBitmapSaveCompleteListener(null);
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
        }
    }
}
