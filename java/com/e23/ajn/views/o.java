package com.e23.ajn.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.e23.ajn.R;
import com.e23.ajn.R.mipmap;
import com.e23.ajn.adapter.c;
import com.e23.ajn.d.r;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ShareDialog */
public class o extends Dialog implements OnClickListener, OnItemClickListener {
    private Context a;
    private GridView b;
    private Button c;
    private RelativeLayout d;
    private RelativeLayout e;
    private String f;
    private String g;
    private String h;
    private String i;

    public o(Context context) {
        super(context, R.style.share_dialog);
        this.a = context;
        setContentView(R.layout.share_dialog);
        setCanceledOnTouchOutside(true);
        a();
    }

    public void a(String str, String str2, String str3, String str4) {
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.i = str4;
    }

    private void a() {
        this.d = (RelativeLayout) findViewById(R.id.bg_layout);
        this.e = (RelativeLayout) findViewById(R.id.share_bg);
        this.b = (GridView) findViewById(R.id.shareGridView);
        this.b.setOnItemClickListener(this);
        this.c = (Button) findViewById(R.id.shareCancel);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        ArrayList arrayList = new ArrayList();
        Class<mipmap> cls = mipmap.class;
        String[] strArr = {"QQ好友", "QQ空间", "微信好友", "微信朋友圈", "新浪微博", "复制链接"};
        int i2 = 0;
        while (i2 < strArr.length) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("ItemImage", Integer.valueOf(cls.getDeclaredField("share_icon_" + i2).getInt(null)));
                hashMap.put("ItemText", strArr[i2]);
                arrayList.add(hashMap);
                i2++;
            } catch (NotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (IllegalArgumentException e4) {
                e4.printStackTrace();
            } catch (NoSuchFieldException e5) {
                e5.printStackTrace();
            }
        }
        this.b.setAdapter(new c(this.a, arrayList));
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        Window window = getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.share_dialog_animation);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        new r(this.a).a(i2, this.f, this.g, this.i, this.h);
        dismiss();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bg_layout /*2131821126*/:
                dismiss();
                return;
            case R.id.shareCancel /*2131821173*/:
                dismiss();
                return;
            default:
                return;
        }
    }
}
