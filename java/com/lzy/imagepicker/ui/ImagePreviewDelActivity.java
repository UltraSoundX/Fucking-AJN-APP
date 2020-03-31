package com.lzy.imagepicker.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.a.C0012a;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import com.lzy.imagepicker.R;
import com.stub.StubApp;

public class ImagePreviewDelActivity extends ImagePreviewBaseActivity implements OnClickListener {
    static {
        StubApp.interface11(4205);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_del) {
            a();
        } else if (id == R.id.btn_back) {
            onBackPressed();
        }
    }

    private void a() {
        C0012a aVar = new C0012a(this);
        aVar.a((CharSequence) "提示");
        aVar.b((CharSequence) "要删除这张照片吗？");
        aVar.b("取消", null);
        aVar.a((CharSequence) "确定", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ImagePreviewDelActivity.this.c.remove(ImagePreviewDelActivity.this.d);
                if (ImagePreviewDelActivity.this.c.size() > 0) {
                    ImagePreviewDelActivity.this.j.a(ImagePreviewDelActivity.this.c);
                    ImagePreviewDelActivity.this.j.notifyDataSetChanged();
                    ImagePreviewDelActivity.this.e.setText(ImagePreviewDelActivity.this.getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(ImagePreviewDelActivity.this.d + 1), Integer.valueOf(ImagePreviewDelActivity.this.c.size())}));
                    return;
                }
                ImagePreviewDelActivity.this.onBackPressed();
            }
        });
        aVar.c();
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("extra_image_items", this.c);
        setResult(1005, intent);
        finish();
        super.onBackPressed();
    }

    public void onImageSingleTap() {
        if (this.h.getVisibility() == 0) {
            this.h.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_out));
            this.h.setVisibility(8);
            this.a.a(0);
            return;
        }
        this.h.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_in));
        this.h.setVisibility(0);
        this.a.a(R.color.ip_color_primary_dark);
    }
}
