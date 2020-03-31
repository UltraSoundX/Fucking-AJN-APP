package com.lzy.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import com.lzy.imagepicker.b.d;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.view.CropImageView.c;
import com.tencent.smtt.sdk.TbsMediaPlayer.TbsMediaPlayerListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* compiled from: ImagePicker */
public class b {
    public static final String a = b.class.getSimpleName();
    private static b s;
    private boolean b = true;
    private int c = 9;
    private boolean d = true;
    private boolean e = true;
    private boolean f = false;
    private int g = TbsMediaPlayerListener.MEDIA_INFO_BAD_INTERLEAVING;
    private int h = TbsMediaPlayerListener.MEDIA_INFO_BAD_INTERLEAVING;
    private int i = 280;
    private int j = 280;
    private com.lzy.imagepicker.a.a k;
    private c l = c.RECTANGLE;
    private File m;
    private File n;
    private ArrayList<ImageItem> o = new ArrayList<>();
    private List<com.lzy.imagepicker.bean.a> p;

    /* renamed from: q reason: collision with root package name */
    private int f422q = 0;
    private List<a> r;

    /* compiled from: ImagePicker */
    public interface a {
        void onImageSelected(int i, ImageItem imageItem, boolean z);
    }

    private b() {
    }

    public static b a() {
        if (s == null) {
            synchronized (b.class) {
                if (s == null) {
                    s = new b();
                }
            }
        }
        return s;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public int c() {
        return this.c;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public boolean d() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean e() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public boolean f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public int h() {
        return this.h;
    }

    public void c(int i2) {
        this.h = i2;
    }

    public int i() {
        return this.i;
    }

    public void d(int i2) {
        this.i = i2;
    }

    public int j() {
        return this.j;
    }

    public void e(int i2) {
        this.j = i2;
    }

    public File k() {
        return this.n;
    }

    public File a(Context context) {
        if (this.m == null) {
            this.m = new File(context.getCacheDir() + "/ImagePicker/cropTemp/");
        }
        return this.m;
    }

    public com.lzy.imagepicker.a.a l() {
        return this.k;
    }

    public void a(com.lzy.imagepicker.a.a aVar) {
        this.k = aVar;
    }

    public c m() {
        return this.l;
    }

    public void a(c cVar) {
        this.l = cVar;
    }

    public void a(List<com.lzy.imagepicker.bean.a> list) {
        this.p = list;
    }

    public void f(int i2) {
        this.f422q = i2;
    }

    public ArrayList<ImageItem> n() {
        return ((com.lzy.imagepicker.bean.a) this.p.get(this.f422q)).d;
    }

    public boolean a(ImageItem imageItem) {
        return this.o.contains(imageItem);
    }

    public int o() {
        if (this.o == null) {
            return 0;
        }
        return this.o.size();
    }

    public ArrayList<ImageItem> p() {
        return this.o;
    }

    public void q() {
        if (this.o != null) {
            this.o.clear();
        }
    }

    public void r() {
        if (this.r != null) {
            this.r.clear();
            this.r = null;
        }
        if (this.p != null) {
            this.p.clear();
            this.p = null;
        }
        if (this.o != null) {
            this.o.clear();
        }
        this.f422q = 0;
    }

    public void a(Activity activity, int i2) {
        Uri uri;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(67108864);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            if (d.a()) {
                this.n = new File(Environment.getExternalStorageDirectory(), "/DCIM/camera/");
            } else {
                this.n = Environment.getDataDirectory();
            }
            this.n = a(this.n, "IMG_", ".jpg");
            if (this.n != null) {
                if (VERSION.SDK_INT <= 23) {
                    uri = Uri.fromFile(this.n);
                } else {
                    Uri uriForFile = FileProvider.getUriForFile(activity, com.lzy.imagepicker.b.c.a(activity), this.n);
                    for (ResolveInfo resolveInfo : activity.getPackageManager().queryIntentActivities(intent, 65536)) {
                        activity.grantUriPermission(resolveInfo.activityInfo.packageName, uriForFile, 3);
                    }
                    uri = uriForFile;
                }
                Log.e("nanchen", com.lzy.imagepicker.b.c.a(activity));
                intent.putExtra("output", uri);
            }
        }
        activity.startActivityForResult(intent, i2);
    }

    public static File a(File file, String str, String str2) {
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return new File(file, str + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + str2);
    }

    public static void a(Context context, File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

    public void a(a aVar) {
        if (this.r == null) {
            this.r = new ArrayList();
        }
        this.r.add(aVar);
    }

    public void b(a aVar) {
        if (this.r != null) {
            this.r.remove(aVar);
        }
    }

    public void a(int i2, ImageItem imageItem, boolean z) {
        if (z) {
            this.o.add(imageItem);
        } else {
            this.o.remove(imageItem);
        }
        b(i2, imageItem, z);
    }

    public void a(ArrayList<ImageItem> arrayList) {
        if (arrayList != null) {
            this.o = arrayList;
        }
    }

    private void b(int i2, ImageItem imageItem, boolean z) {
        if (this.r != null) {
            for (a onImageSelected : this.r) {
                onImageSelected.onImageSelected(i2, imageItem, z);
            }
        }
    }

    public void a(Bundle bundle) {
        this.m = (File) bundle.getSerializable("cropCacheFolder");
        this.n = (File) bundle.getSerializable("takeImageFile");
        this.k = (com.lzy.imagepicker.a.a) bundle.getSerializable("imageLoader");
        this.l = (c) bundle.getSerializable("style");
        this.b = bundle.getBoolean("multiMode");
        this.d = bundle.getBoolean("crop");
        this.e = bundle.getBoolean("showCamera");
        this.f = bundle.getBoolean("isSaveRectangle");
        this.c = bundle.getInt("selectLimit");
        this.g = bundle.getInt("outPutX");
        this.h = bundle.getInt("outPutY");
        this.i = bundle.getInt("focusWidth");
        this.j = bundle.getInt("focusHeight");
    }

    public void b(Bundle bundle) {
        bundle.putSerializable("cropCacheFolder", this.m);
        bundle.putSerializable("takeImageFile", this.n);
        bundle.putSerializable("imageLoader", this.k);
        bundle.putSerializable("style", this.l);
        bundle.putBoolean("multiMode", this.b);
        bundle.putBoolean("crop", this.d);
        bundle.putBoolean("showCamera", this.e);
        bundle.putBoolean("isSaveRectangle", this.f);
        bundle.putInt("selectLimit", this.c);
        bundle.putInt("outPutX", this.g);
        bundle.putInt("outPutY", this.h);
        bundle.putInt("focusWidth", this.i);
        bundle.putInt("focusHeight", this.j);
    }
}
