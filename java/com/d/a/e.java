package com.d.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import com.mob.MobSDK;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: OnekeyShareThemeImpl */
public abstract class e implements Callback, PlatformActionListener {
    protected boolean a;
    protected HashMap<String, Object> b;
    protected boolean c;
    protected ArrayList<a> d;
    protected HashMap<String, String> e;
    protected PlatformActionListener f = this;
    protected f g;
    protected boolean h;
    protected Context i;

    /* access modifiers changed from: protected */
    public abstract void a(Context context, Platform platform, ShareParams shareParams);

    /* access modifiers changed from: protected */
    public abstract void b(Context context);

    public final void a(boolean z) {
        this.a = z;
    }

    public final void a(HashMap<String, Object> hashMap) {
        this.b = hashMap;
    }

    public final void b(boolean z) {
        this.c = z;
    }

    public final void a(ArrayList<a> arrayList) {
        this.d = arrayList;
    }

    public final void b(HashMap<String, String> hashMap) {
        this.e = hashMap;
    }

    public final void a(PlatformActionListener platformActionListener) {
        if (platformActionListener == 0) {
            platformActionListener = this;
        }
        this.f = platformActionListener;
    }

    public final void a(f fVar) {
        this.g = fVar;
    }

    public final void a() {
        this.h = true;
    }

    public final void a(Context context) {
        this.i = context;
        if (this.b.containsKey("platform")) {
            Platform platform = ShareSDK.getPlatform(String.valueOf(this.b.get("platform")));
            boolean z = platform instanceof CustomPlatform;
            boolean a2 = a(platform);
            if (this.c || z || a2) {
                b(platform);
            } else {
                e(platform);
            }
        } else {
            b(context);
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(Platform platform) {
        String name = platform.getName();
        if ("Wechat".equals(name) || "WechatMoments".equals(name) || "WechatFavorite".equals(name) || "ShortMessage".equals(name) || "Email".equals(name) || "Qzone".equals(name) || "QQ".equals(name) || "Pinterest".equals(name) || "Instagram".equals(name) || "Yixin".equals(name) || "YixinMoments".equals(name) || "QZone".equals(name) || "Mingdao".equals(name) || "Line".equals(name) || "KakaoStory".equals(name) || "KakaoTalk".equals(name) || "Bluetooth".equals(name) || "WhatsApp".equals(name) || "BaiduTieba".equals(name) || "Laiwang".equals(name) || "LaiwangMoments".equals(name) || "Alipay".equals(name) || "AlipayMoments".equals(name) || "FacebookMessenger".equals(name) || "GooglePlus".equals(name) || "Dingding".equals(name) || "Youtube".equals(name) || "Meipai".equals(name)) {
            return true;
        }
        if ("Evernote".equals(name)) {
            if ("true".equals(platform.getDevinfo("ShareByAppClient"))) {
                return true;
            }
        } else if ("SinaWeibo".equals(name) && "true".equals(platform.getDevinfo("ShareByAppClient"))) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setPackage("com.sina.weibo");
            intent.setType("image/*");
            ResolveInfo resolveActivity = MobSDK.getContext().getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setPackage("com.sina.weibog3");
                intent2.setType("image/*");
                resolveActivity = MobSDK.getContext().getPackageManager().resolveActivity(intent2, 0);
            }
            if (resolveActivity == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final void b(Platform platform) {
        if (c(platform)) {
            ShareParams d2 = d(platform);
            if (d2 != null) {
                a("ssdk_oks_sharing");
                if (this.g != null) {
                    this.g.a(platform, d2);
                }
                if (this.h) {
                    platform.SSOSetting(this.h);
                }
                platform.setPlatformActionListener(this.f);
                platform.share(d2);
            }
        }
    }

    private void e(Platform platform) {
        if (c(platform)) {
            ShareParams d2 = d(platform);
            if (d2 != null) {
                ShareSDK.logDemoEvent(3, null);
                if (this.g != null) {
                    this.g.a(platform, d2);
                }
                a(this.i, platform, d2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean c(Platform platform) {
        boolean z;
        boolean z2;
        int i2;
        String name = platform.getName();
        if (("Alipay".equals(name) || "AlipayMoments".equals(name)) && !platform.isClientValid()) {
            a("ssdk_alipay_client_inavailable");
            return false;
        } else if ("KakaoTalk".equals(name) && !platform.isClientValid()) {
            a("ssdk_kakaotalk_client_inavailable");
            return false;
        } else if ("KakaoStory".equals(name) && !platform.isClientValid()) {
            a("ssdk_kakaostory_client_inavailable");
            return false;
        } else if ("Line".equals(name) && !platform.isClientValid()) {
            a("ssdk_line_client_inavailable");
            return false;
        } else if ("WhatsApp".equals(name) && !platform.isClientValid()) {
            a("ssdk_whatsapp_client_inavailable");
            return false;
        } else if ("Pinterest".equals(name) && !platform.isClientValid()) {
            a("ssdk_pinterest_client_inavailable");
            return false;
        } else if ("Instagram".equals(name) && !platform.isClientValid()) {
            a("ssdk_instagram_client_inavailable");
            return false;
        } else if (!"QZone".equals(name) || platform.isClientValid()) {
            boolean equals = "Laiwang".equals(name);
            boolean equals2 = "LaiwangMoments".equals(name);
            if ((equals || equals2) && !platform.isClientValid()) {
                a("ssdk_laiwang_client_inavailable");
                return false;
            }
            if ("YixinMoments".equals(name) || "Yixin".equals(name)) {
                z = true;
            } else {
                z = false;
            }
            if (!z || platform.isClientValid()) {
                if ("WechatFavorite".equals(name) || "Wechat".equals(name) || "WechatMoments".equals(name)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2 && !platform.isClientValid()) {
                    a("ssdk_wechat_client_inavailable");
                    return false;
                } else if ("FacebookMessenger".equals(name) && !platform.isClientValid()) {
                    a("ssdk_facebookmessenger_client_inavailable");
                    return false;
                } else if (this.b.containsKey("shareType")) {
                    return true;
                } else {
                    String valueOf = String.valueOf(this.b.get("imagePath"));
                    if (valueOf == null || !new File(valueOf).exists()) {
                        Bitmap bitmap = (Bitmap) ResHelper.forceCast(this.b.get("viewToShare"));
                        if (bitmap == null || bitmap.isRecycled()) {
                            Object obj = this.b.get("imageUrl");
                            if (obj == null || TextUtils.isEmpty(String.valueOf(obj))) {
                                i2 = 1;
                            } else if (!String.valueOf(obj).endsWith(".gif") || !z2) {
                                if (this.b.containsKey("url") && !TextUtils.isEmpty(this.b.get("url").toString())) {
                                    if (this.b.containsKey("musicUrl") && !TextUtils.isEmpty(this.b.get("musicUrl").toString()) && z2) {
                                        i2 = 5;
                                    }
                                    i2 = 4;
                                }
                                i2 = 2;
                            } else {
                                i2 = 9;
                            }
                        } else {
                            if (this.b.containsKey("url") && !TextUtils.isEmpty(this.b.get("url").toString())) {
                                if (this.b.containsKey("musicUrl") && !TextUtils.isEmpty(this.b.get("musicUrl").toString()) && z2) {
                                    i2 = 5;
                                }
                                i2 = 4;
                            }
                            i2 = 2;
                        }
                    } else if (!valueOf.endsWith(".gif") || !z2) {
                        if (this.b.containsKey("url") && !TextUtils.isEmpty(this.b.get("url").toString())) {
                            if (this.b.containsKey("musicUrl") && !TextUtils.isEmpty(this.b.get("musicUrl").toString()) && z2) {
                                i2 = 5;
                            }
                            i2 = 4;
                        }
                        i2 = 2;
                    } else {
                        i2 = 9;
                    }
                    this.b.put("shareType", Integer.valueOf(i2));
                    return true;
                }
            } else {
                a("ssdk_yixin_client_inavailable");
                return false;
            }
        } else {
            a("ssdk_qq_client_inavailable");
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final ShareParams d(Platform platform) {
        if (platform == null || this.b == null) {
            a("ssdk_oks_share_failed");
            return null;
        }
        try {
            Bitmap bitmap = (Bitmap) ResHelper.forceCast(this.b.get("viewToShare"));
            if (TextUtils.isEmpty((String) ResHelper.forceCast(this.b.get("imagePath"))) && bitmap != null && !bitmap.isRecycled()) {
                File file = new File(ResHelper.getCachePath(MobSDK.getContext(), "screenshot"), String.valueOf(System.currentTimeMillis()) + ".jpg");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.b.put("imagePath", file.getAbsolutePath());
            }
            return new ShareParams(this.b);
        } catch (Throwable th) {
            th.printStackTrace();
            a("ssdk_oks_share_failed");
            return null;
        }
    }

    private void a(final String str) {
        UIHandler.sendEmptyMessage(0, new Callback() {
            public boolean handleMessage(Message message) {
                int stringRes = ResHelper.getStringRes(e.this.i, str);
                if (stringRes > 0) {
                    Toast.makeText(e.this.i, stringRes, 0).show();
                } else {
                    Toast.makeText(e.this.i, str, 0).show();
                }
                return false;
            }
        });
    }

    public final void onComplete(Platform platform, int i2, HashMap<String, Object> hashMap) {
        Message message = new Message();
        message.arg1 = 1;
        message.arg2 = i2;
        message.obj = platform;
        UIHandler.sendMessage(message, this);
    }

    public final void onError(Platform platform, int i2, Throwable th) {
        th.printStackTrace();
        Message message = new Message();
        message.arg1 = 2;
        message.arg2 = i2;
        message.obj = th;
        UIHandler.sendMessage(message, this);
        ShareSDK.logDemoEvent(4, platform);
    }

    public final void onCancel(Platform platform, int i2) {
        Message message = new Message();
        message.arg1 = 3;
        message.arg2 = i2;
        message.obj = platform;
        UIHandler.sendMessage(message, this);
        ShareSDK.logDemoEvent(5, platform);
    }

    public final boolean handleMessage(Message message) {
        switch (message.arg1) {
            case 1:
                int stringRes = ResHelper.getStringRes(this.i, "ssdk_oks_share_completed");
                if (stringRes > 0) {
                    a(this.i.getString(stringRes));
                    break;
                }
                break;
            case 2:
                String simpleName = message.obj.getClass().getSimpleName();
                if (!"WechatClientNotExistException".equals(simpleName) && !"WechatTimelineNotSupportedException".equals(simpleName) && !"WechatFavoriteNotSupportedException".equals(simpleName)) {
                    if (!"GooglePlusClientNotExistException".equals(simpleName)) {
                        if (!"QQClientNotExistException".equals(simpleName)) {
                            if (!"YixinClientNotExistException".equals(simpleName) && !"YixinTimelineNotSupportedException".equals(simpleName)) {
                                if (!"KakaoTalkClientNotExistException".equals(simpleName)) {
                                    if (!"KakaoStoryClientNotExistException".equals(simpleName)) {
                                        if (!"WhatsAppClientNotExistException".equals(simpleName)) {
                                            if (!"FacebookMessengerClientNotExistException".equals(simpleName)) {
                                                a("ssdk_oks_share_failed");
                                                break;
                                            } else {
                                                a("ssdk_facebookmessenger_client_inavailable");
                                                break;
                                            }
                                        } else {
                                            a("ssdk_whatsapp_client_inavailable");
                                            break;
                                        }
                                    } else {
                                        a("ssdk_kakaostory_client_inavailable");
                                        break;
                                    }
                                } else {
                                    a("ssdk_kakaotalk_client_inavailable");
                                    break;
                                }
                            } else {
                                a("ssdk_yixin_client_inavailable");
                                break;
                            }
                        } else {
                            a("ssdk_qq_client_inavailable");
                            break;
                        }
                    } else {
                        a("ssdk_google_plus_client_inavailable");
                        break;
                    }
                } else {
                    a("ssdk_wechat_client_inavailable");
                    break;
                }
                break;
            case 3:
                a("ssdk_oks_share_canceled");
                break;
        }
        return false;
    }
}
