package com.e23.ajn.d;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.d.b.b;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.g.b.d;
import com.bumptech.glide.i;
import com.e23.ajn.R;
import com.lzy.imagepicker.a.a;
import java.io.File;

/* compiled from: GlideImageLoader */
public class k implements a {
    public void a(Activity activity, String str, ImageView imageView, int i, int i2) {
        com.e23.ajn.views.k.a(activity);
        i.a(activity).a(Uri.fromFile(new File(str))).c((int) R.mipmap.f276plimg_s).b(b.ALL).a(new d(imageView) {
            public void a(com.bumptech.glide.d.d.b.b bVar, c<? super com.bumptech.glide.d.d.b.b> cVar) {
                super.a(bVar, cVar);
                com.e23.ajn.views.k.a();
            }
        });
    }

    public void b(Activity activity, String str, ImageView imageView, int i, int i2) {
        i.a(activity).a(Uri.fromFile(new File(str))).c((int) R.mipmap.f276plimg_s).d((int) R.mipmap.f276plimg_s).b(b.ALL).a(imageView);
    }
}
