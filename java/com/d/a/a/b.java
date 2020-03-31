package com.d.a.a;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import com.d.a.c;
import com.d.a.e;
import com.mob.MobSDK;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: EditPage */
public class b extends c implements TextWatcher, OnClickListener, Runnable {
    protected Platform a;
    protected ShareParams b;
    protected LinearLayout c;
    protected RelativeLayout d;
    protected ScrollView e;
    protected EditText f;
    protected TextView g;
    protected TextView h;
    protected RelativeLayout i;
    protected AsyncImageView j;
    protected l k;
    protected LinearLayout l;
    protected TextView m;
    protected TextView n;
    /* access modifiers changed from: protected */
    public Bitmap o;
    protected int p;

    /* renamed from: q reason: collision with root package name */
    private e f397q;

    public b(e eVar) {
        super(eVar);
        this.f397q = eVar;
    }

    public void d(Platform platform) {
        this.a = platform;
    }

    public void a(ShareParams shareParams) {
        this.b = shareParams;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (a()) {
        }
        activity.getWindow().setSoftInputMode(37);
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-789517));
    }

    private void h() {
        ShareSDK.logDemoEvent(5, this.a);
        finish();
    }

    private void i() {
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_sharing");
        if (stringRes > 0) {
            Toast.makeText(this.activity, stringRes, 0).show();
        }
        if (g()) {
            this.a.SSOSetting(true);
        }
        this.a.setPlatformActionListener(e());
        this.a.share(this.b);
        finish();
    }

    private void a(Bitmap bitmap) {
        h hVar = new h(this.f397q);
        hVar.a(bitmap);
        hVar.show(this.activity, null);
    }

    private void j() {
        this.b.setImageArray(null);
        this.b.setImageData(null);
        this.b.setImagePath(null);
        this.b.setImageUrl(null);
    }

    private void k() {
        e bVar;
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            bVar = new com.d.a.a.b.b(this.f397q);
        } else {
            bVar = new com.d.a.a.a.b(this.f397q);
        }
        bVar.d(this.a);
        bVar.showForResult(MobSDK.getContext(), null, this);
    }

    public void onResult(HashMap<String, Object> hashMap) {
        String a2 = a(hashMap);
        if (!TextUtils.isEmpty(a2)) {
            this.f.append(a2);
        }
    }

    private String a(HashMap<String, Object> hashMap) {
        if (hashMap == null || !hashMap.containsKey("selected")) {
            return null;
        }
        ArrayList arrayList = (ArrayList) hashMap.get("selected");
        if ("FacebookMessenger".equals(((Platform) hashMap.get("platform")).getName())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append('@').append((String) it.next()).append(' ');
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        return "SinaWeibo".equals(str) || "TencentWeibo".equals(str) || "Facebook".equals(str) || "Twitter".equals(str);
    }

    public void onClick(View view) {
        if (view.equals(this.g)) {
            h();
        } else if (view.equals(this.h)) {
            this.b.setText(this.f.getText().toString().trim());
            i();
        } else if (view.equals(this.j)) {
            a(this.o);
        } else if (view.equals(this.k)) {
            this.p = 0;
            this.i.setVisibility(8);
            this.c.measure(0, 0);
            onTextChanged(this.f.getText(), 0, 0, 0);
            j();
        } else if (view.equals(this.m)) {
            k();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.n.setText(String.valueOf(charSequence.length()));
        if (this.p == 0) {
            this.p = (this.c.getHeight() - this.d.getHeight()) - this.l.getHeight();
        }
        if (this.p > 0) {
            this.e.post(this);
        }
    }

    public void run() {
        int height = this.e.getChildAt(0).getHeight();
        LayoutParams layoutParams = (LayoutParams) ResHelper.forceCast(this.e.getLayoutParams());
        if (height > this.p && layoutParams.height != this.p) {
            layoutParams.height = this.p;
            this.e.setLayoutParams(layoutParams);
        } else if (height < this.p && layoutParams.height == this.p) {
            layoutParams.height = -2;
            this.e.setLayoutParams(layoutParams);
        }
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void onPause() {
        DeviceHelper.getInstance(this.activity).hideSoftInput(getContentView());
        super.onPause();
    }
}
