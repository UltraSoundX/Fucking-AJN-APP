package com.e23.ajn.d;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import com.d.a.b;
import com.d.a.f;
import com.e23.ajn.R;
import com.e23.ajn.b.e;
import com.e23.ajn.b.i;
import com.e23.ajn.views.k;
import java.io.File;
import java.util.HashMap;

/* compiled from: ShareUtil */
public class r {
    private Context a;

    public r(Context context) {
        this.a = context;
    }

    public void a(int i, String str, String str2, String str3, String str4) {
        if (str != null) {
            str = str.replaceAll("<br>", "");
        }
        Platform platform = null;
        switch (i) {
            case 0:
                platform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case 1:
                platform = ShareSDK.getPlatform(QZone.NAME);
                break;
            case 2:
                platform = ShareSDK.getPlatform(Wechat.NAME);
                break;
            case 3:
                platform = ShareSDK.getPlatform(WechatMoments.NAME);
                break;
            case 4:
                platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                break;
        }
        if (i != 5) {
            b bVar = new b();
            bVar.a("");
            bVar.b(str);
            bVar.c(str3);
            if (TextUtils.isEmpty(str2)) {
                bVar.d(str);
            } else {
                bVar.d(str2 + "   ");
            }
            if (TextUtils.isEmpty(str4)) {
                a(bVar, "cn_e23_love_jinan_logo.png");
            } else {
                bVar.f(str4);
            }
            bVar.g(str3);
            bVar.h(this.a.getString(2131362006));
            bVar.i(str3);
            bVar.a(false);
            if (platform != null) {
                bVar.j(platform.getName());
            }
            bVar.a();
            bVar.a((f) new f() {
                public void a(Platform platform, ShareParams shareParams) {
                    Log.d("wdp", "platform" + platform.getName());
                }
            });
            bVar.a((PlatformActionListener) new PlatformActionListener() {
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    r.this.a();
                }

                public void onError(Platform platform, int i, Throwable th) {
                }

                public void onCancel(Platform platform, int i) {
                }
            });
            bVar.a(this.a);
            return;
        }
        ((ClipboardManager) this.a.getSystemService("clipboard")).setText(str3);
        Toast.makeText(this.a, this.a.getString(R.string.copysucc), 0).show();
    }

    public void a(b bVar, String str) {
        if (bVar != null) {
            String g = h.g();
            if (!e.a(g)) {
                bVar.e(g + File.separator + str);
            }
        }
    }

    public void a() {
        if (p.a("is_logined", false)) {
            a.a("forward", this.a);
            e.a((Activity) this.a).c(new i());
            return;
        }
        k.a(this.a, "分享成功");
    }
}
