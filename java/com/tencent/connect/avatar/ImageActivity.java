package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.common.a.C0076a;
import com.tencent.open.d.j;
import com.tencent.tauth.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import net.sf.json.util.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class ImageActivity extends Activity {
    RelativeLayout a;
    /* access modifiers changed from: private */
    public com.tencent.connect.b.b b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public Handler d;
    /* access modifiers changed from: private */
    public c e;
    /* access modifiers changed from: private */
    public Button f;
    /* access modifiers changed from: private */
    public Button g;
    /* access modifiers changed from: private */
    public b h;
    private TextView i;
    /* access modifiers changed from: private */
    public ProgressBar j;
    /* access modifiers changed from: private */
    public int k = 0;
    /* access modifiers changed from: private */
    public boolean l = false;
    /* access modifiers changed from: private */
    public long m = 0;
    private int n = 0;
    private final int o = 640;
    private final int p = 640;
    /* access modifiers changed from: private */

    /* renamed from: q reason: collision with root package name */
    public Rect f432q = new Rect();
    private String r;
    private Bitmap s;
    private final OnClickListener t = new OnClickListener() {
        public void onClick(View view) {
            ImageActivity.this.j.setVisibility(0);
            ImageActivity.this.g.setEnabled(false);
            ImageActivity.this.g.setTextColor(Color.rgb(21, 21, 21));
            ImageActivity.this.f.setEnabled(false);
            ImageActivity.this.f.setTextColor(Color.rgb(36, 94, 134));
            new Thread(new Runnable() {
                public void run() {
                    ImageActivity.this.c();
                }
            }).start();
            if (ImageActivity.this.l) {
                ImageActivity.this.a("10657", 0);
                return;
            }
            ImageActivity.this.a("10655", System.currentTimeMillis() - ImageActivity.this.m);
            if (ImageActivity.this.e.b) {
                ImageActivity.this.a("10654", 0);
            }
        }
    };
    private final OnClickListener u = new OnClickListener() {
        public void onClick(View view) {
            ImageActivity.this.a("10656", System.currentTimeMillis() - ImageActivity.this.m);
            ImageActivity.this.setResult(0);
            ImageActivity.this.d();
        }
    };
    private final com.tencent.tauth.b v = new com.tencent.tauth.b() {
        public void onError(d dVar) {
            ImageActivity.this.g.setEnabled(true);
            ImageActivity.this.g.setTextColor(-1);
            ImageActivity.this.f.setEnabled(true);
            ImageActivity.this.f.setTextColor(-1);
            ImageActivity.this.f.setText("重试");
            ImageActivity.this.j.setVisibility(8);
            ImageActivity.this.l = true;
            ImageActivity.this.a(dVar.b, 1);
            ImageActivity.this.a("10660", 0);
        }

        public void onComplete(Object obj) {
            int i;
            ImageActivity.this.g.setEnabled(true);
            ImageActivity.this.g.setTextColor(-1);
            ImageActivity.this.f.setEnabled(true);
            ImageActivity.this.f.setTextColor(-1);
            ImageActivity.this.j.setVisibility(8);
            JSONObject jSONObject = (JSONObject) obj;
            try {
                i = jSONObject.getInt("ret");
            } catch (JSONException e) {
                e.printStackTrace();
                i = -1;
            }
            if (i == 0) {
                ImageActivity.this.a("设置成功", 0);
                ImageActivity.this.a("10658", 0);
                com.tencent.open.b.d.a().a(ImageActivity.this.b.d(), ImageActivity.this.b.b(), "ANDROIDSDK.SETAVATAR.SUCCEED", "12", "3", "0");
                ImageActivity imageActivity = ImageActivity.this;
                if (ImageActivity.this.c != null && !"".equals(ImageActivity.this.c)) {
                    Intent intent = new Intent();
                    intent.setClassName(imageActivity, ImageActivity.this.c);
                    if (imageActivity.getPackageManager().resolveActivity(intent, 0) != null) {
                        imageActivity.startActivity(intent);
                    }
                }
                ImageActivity.this.a(0, jSONObject.toString(), null, null);
                ImageActivity.this.d();
                return;
            }
            ImageActivity.this.a("设置出错了，请重新登录再尝试下呢：）", 1);
            com.tencent.open.b.d.a().a(ImageActivity.this.b.d(), ImageActivity.this.b.b(), "ANDROIDSDK.SETAVATAR.SUCCEED", "12", "19", "1");
        }

        public void onCancel() {
        }
    };
    private final com.tencent.tauth.b w = new com.tencent.tauth.b() {
        public void onError(d dVar) {
            a(0);
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            int i = -1;
            try {
                i = jSONObject.getInt("ret");
                if (i == 0) {
                    final String string = jSONObject.getString("nickname");
                    ImageActivity.this.d.post(new Runnable() {
                        public void run() {
                            ImageActivity.this.c(string);
                        }
                    });
                    ImageActivity.this.a("10659", 0);
                } else {
                    ImageActivity.this.a("10661", 0);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (i != 0) {
                a(i);
            }
        }

        public void onCancel() {
        }

        private void a(int i) {
            if (ImageActivity.this.k < 2) {
                ImageActivity.this.e();
            }
        }
    };

    /* compiled from: ProGuard */
    private class a extends com.tencent.connect.common.a {
        public a(com.tencent.connect.b.b bVar) {
            super(bVar);
        }

        public void a(Bitmap bitmap, com.tencent.tauth.b bVar) {
            Bundle a2 = a();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 40, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bitmap.recycle();
            C0076a aVar = new C0076a(bVar);
            a2.putByteArray("picture", byteArray);
            com.tencent.open.d.a.a(this.d, com.tencent.open.d.d.a(), "user/set_user_face", a2, "POST", aVar);
            com.tencent.open.b.d.a().a(this.d.d(), this.d.b(), "ANDROIDSDK.SETAVATAR.SUCCEED", "12", "19", "0");
        }
    }

    /* compiled from: ProGuard */
    class b extends View {
        public b(Context context) {
            super(context);
        }

        public void a(Button button) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable a2 = ImageActivity.this.b("com.tencent.plus.blue_normal.png");
            Drawable a3 = ImageActivity.this.b("com.tencent.plus.blue_down.png");
            Drawable a4 = ImageActivity.this.b("com.tencent.plus.blue_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a3);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a2);
            stateListDrawable.addState(View.ENABLED_STATE_SET, a2);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, a2);
            stateListDrawable.addState(View.EMPTY_STATE_SET, a4);
            button.setBackgroundDrawable(stateListDrawable);
        }

        public void b(Button button) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable a2 = ImageActivity.this.b("com.tencent.plus.gray_normal.png");
            Drawable a3 = ImageActivity.this.b("com.tencent.plus.gray_down.png");
            Drawable a4 = ImageActivity.this.b("com.tencent.plus.gray_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a3);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a2);
            stateListDrawable.addState(View.ENABLED_STATE_SET, a2);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, a2);
            stateListDrawable.addState(View.EMPTY_STATE_SET, a4);
            button.setBackgroundDrawable(stateListDrawable);
        }
    }

    private Bitmap a(String str) throws IOException {
        int i2 = 1;
        Bitmap bitmap = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Uri parse = Uri.parse(str);
        InputStream openInputStream = getContentResolver().openInputStream(parse);
        if (openInputStream == null) {
            return bitmap;
        }
        try {
            BitmapFactory.decodeStream(openInputStream, null, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
        }
        openInputStream.close();
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        while (i3 * i4 > 4194304) {
            i3 /= 2;
            i4 /= 2;
            i2 *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i2;
        try {
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(parse), null, options);
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            return bitmap;
        }
    }

    /* access modifiers changed from: private */
    public Drawable b(String str) {
        IOException e2;
        Drawable drawable;
        try {
            InputStream open = getAssets().open(str);
            drawable = Drawable.createFromStream(open, str);
            try {
                open.close();
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
                return drawable;
            }
        } catch (IOException e4) {
            IOException iOException = e4;
            drawable = null;
            e2 = iOException;
        }
        return drawable;
    }

    private View a() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        this.a = new RelativeLayout(this);
        this.a.setLayoutParams(layoutParams);
        this.a.setBackgroundColor(-16777216);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams3);
        this.a.addView(relativeLayout);
        this.e = new c(this);
        this.e.setLayoutParams(layoutParams2);
        this.e.setScaleType(ScaleType.MATRIX);
        relativeLayout.addView(this.e);
        this.h = new b(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(layoutParams2);
        layoutParams4.addRule(14, -1);
        layoutParams4.addRule(15, -1);
        this.h.setLayoutParams(layoutParams4);
        relativeLayout.addView(this.h);
        LinearLayout linearLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, a.a(this, 80.0f));
        layoutParams5.addRule(14, -1);
        linearLayout.setLayoutParams(layoutParams5);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        this.a.addView(linearLayout);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 24.0f), a.a(this, 24.0f)));
        imageView.setImageDrawable(b("com.tencent.plus.logo.png"));
        linearLayout.addView(imageView);
        this.i = new TextView(this);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(layoutParams3);
        layoutParams6.leftMargin = a.a(this, 7.0f);
        this.i.setLayoutParams(layoutParams6);
        this.i.setEllipsize(TruncateAt.END);
        this.i.setSingleLine();
        this.i.setTextColor(-1);
        this.i.setTextSize(24.0f);
        this.i.setVisibility(8);
        linearLayout.addView(this.i);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-1, a.a(this, 60.0f));
        layoutParams7.addRule(12, -1);
        layoutParams7.addRule(9, -1);
        relativeLayout2.setLayoutParams(layoutParams7);
        relativeLayout2.setBackgroundDrawable(b("com.tencent.plus.bar.png"));
        int a2 = a.a(this, 10.0f);
        relativeLayout2.setPadding(a2, a2, a2, 0);
        this.a.addView(relativeLayout2);
        b bVar = new b(this);
        int a3 = a.a(this, 14.0f);
        int a4 = a.a(this, 7.0f);
        this.g = new Button(this);
        this.g.setLayoutParams(new RelativeLayout.LayoutParams(a.a(this, 78.0f), a.a(this, 45.0f)));
        this.g.setText("取消");
        this.g.setTextColor(-1);
        this.g.setTextSize(18.0f);
        this.g.setPadding(a3, a4, a3, a4);
        bVar.b(this.g);
        relativeLayout2.addView(this.g);
        this.f = new Button(this);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(a.a(this, 78.0f), a.a(this, 45.0f));
        layoutParams8.addRule(11, -1);
        this.f.setLayoutParams(layoutParams8);
        this.f.setTextColor(-1);
        this.f.setTextSize(18.0f);
        this.f.setPadding(a3, a4, a3, a4);
        this.f.setText("选取");
        bVar.a(this.f);
        relativeLayout2.addView(this.f);
        TextView textView = new TextView(this);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams9.addRule(13, -1);
        textView.setLayoutParams(layoutParams9);
        textView.setText("移动和缩放");
        textView.setPadding(0, a.a(this, 3.0f), 0, 0);
        textView.setTextSize(18.0f);
        textView.setTextColor(-1);
        relativeLayout2.addView(textView);
        this.j = new ProgressBar(this);
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams10.addRule(14, -1);
        layoutParams10.addRule(15, -1);
        this.j.setLayoutParams(layoutParams10);
        this.j.setVisibility(8);
        this.a.addView(this.j);
        return this.a;
    }

    private void b() {
        try {
            this.s = a(this.r);
            if (this.s == null) {
                throw new IOException("cannot read picture: '" + this.r + "'!");
            }
            this.e.setImageBitmap(this.s);
            this.f.setOnClickListener(this.t);
            this.g.setOnClickListener(this.u);
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    ImageActivity.this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    ImageActivity.this.f432q = ImageActivity.this.h.a();
                    ImageActivity.this.e.a(ImageActivity.this.f432q);
                }
            });
        } catch (IOException e2) {
            e2.printStackTrace();
            String str = "图片读取失败，请检查该图片是否有效";
            a(str, 1);
            a(-5, null, str, e2.getMessage());
            d();
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(a());
        this.d = new Handler();
        Bundle bundleExtra = getIntent().getBundleExtra("key_params");
        this.r = bundleExtra.getString("picture");
        this.c = bundleExtra.getString("return_activity");
        String string = bundleExtra.getString("appid");
        String string2 = bundleExtra.getString("access_token");
        long j2 = bundleExtra.getLong("expires_in");
        String string3 = bundleExtra.getString("openid");
        this.n = bundleExtra.getInt("exitAnim");
        this.b = new com.tencent.connect.b.b(string);
        this.b.a(string2, ((j2 - System.currentTimeMillis()) / 1000) + "");
        this.b.a(string3);
        b();
        e();
        this.m = System.currentTimeMillis();
        a("10653", 0);
    }

    public void onBackPressed() {
        setResult(0);
        d();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.e.setImageBitmap(null);
        if (this.s != null && !this.s.isRecycled()) {
            this.s.recycle();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        float width = (float) this.f432q.width();
        Matrix imageMatrix = this.e.getImageMatrix();
        float[] fArr = new float[9];
        imageMatrix.getValues(fArr);
        float f2 = fArr[2];
        float f3 = fArr[5];
        float f4 = fArr[0];
        float f5 = 640.0f / width;
        int i2 = (int) ((((float) this.f432q.left) - f2) / f4);
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = (int) ((((float) this.f432q.top) - f3) / f4);
        if (i3 < 0) {
            i3 = 0;
        }
        Matrix matrix = new Matrix();
        matrix.set(imageMatrix);
        matrix.postScale(f5, f5);
        int i4 = (int) (650.0f / f4);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(this.s, i2, i3, Math.min(this.s.getWidth() - i2, i4), Math.min(this.s.getHeight() - i3, i4), matrix, true);
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, 640, 640);
            createBitmap.recycle();
            a(createBitmap2);
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            String str = "图片读取失败，请检查该图片是否有效";
            a(str, 1);
            a(-5, null, str, e2.getMessage());
            d();
        }
    }

    private void a(Bitmap bitmap) {
        new a(this.b).a(bitmap, this.v);
    }

    /* access modifiers changed from: private */
    public void a(final String str, final int i2) {
        this.d.post(new Runnable() {
            public void run() {
                ImageActivity.this.b(str, i2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(String str, int i2) {
        Toast makeText = Toast.makeText(this, str, 1);
        LinearLayout linearLayout = (LinearLayout) makeText.getView();
        ((TextView) linearLayout.getChildAt(0)).setPadding(8, 0, 0, 0);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 16.0f), a.a(this, 16.0f)));
        if (i2 == 0) {
            imageView.setImageDrawable(b("com.tencent.plus.ic_success.png"));
        } else {
            imageView.setImageDrawable(b("com.tencent.plus.ic_error.png"));
        }
        linearLayout.addView(imageView, 0);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        makeText.setView(linearLayout);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    /* access modifiers changed from: private */
    public void a(int i2, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.putExtra("key_error_code", i2);
        intent.putExtra("key_error_msg", str2);
        intent.putExtra("key_error_detail", str3);
        intent.putExtra("key_response", str);
        setResult(-1, intent);
    }

    /* access modifiers changed from: private */
    public void d() {
        finish();
        if (this.n != 0) {
            overridePendingTransition(0, this.n);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        this.k++;
        new com.tencent.connect.a(this, this.b).a(this.w);
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        String d2 = d(str);
        if (!"".equals(d2)) {
            this.i.setText(d2);
            this.i.setVisibility(0);
        }
    }

    private String d(String str) {
        return str.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", JSONUtils.DOUBLE_QUOTE).replaceAll("&#39;", JSONUtils.SINGLE_QUOTE).replaceAll("&amp;", "&");
    }

    public void a(String str, long j2) {
        j.a((Context) this, str, j2, this.b.b());
    }
}
