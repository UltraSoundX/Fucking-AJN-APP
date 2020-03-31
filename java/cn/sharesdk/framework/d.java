package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.f;
import cn.sharesdk.framework.b.b.b;
import cn.sharesdk.framework.b.b.c;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.tencent.android.tpush.common.MessageKey;
import java.util.HashMap;

/* compiled from: InnerPlatformActionListener */
public class d implements PlatformActionListener {
    /* access modifiers changed from: private */
    public PlatformActionListener a;
    private HashMap<Platform, ShareParams> b = new HashMap<>();
    private int c;

    d() {
    }

    /* access modifiers changed from: 0000 */
    public void a(PlatformActionListener platformActionListener) {
        this.a = platformActionListener;
    }

    /* access modifiers changed from: 0000 */
    public PlatformActionListener a() {
        return this.a;
    }

    public void a(Platform platform, ShareParams shareParams) {
        this.b.put(platform, shareParams);
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.a != null) {
            this.a.onError(platform, i, th);
            this.a = null;
            this.c = 0;
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (!(platform instanceof CustomPlatform)) {
            switch (i) {
                case 1:
                    a(platform, i, hashMap);
                    return;
                case 9:
                    b(platform, i, hashMap);
                    return;
                default:
                    if (this.a != null) {
                        this.a.onComplete(platform, i, hashMap);
                        if ("Wechat".equals(platform.getName())) {
                            return;
                        }
                        if (this.c == 0 || this.c == i) {
                            this.a = null;
                            this.c = 0;
                            return;
                        }
                        return;
                    }
                    return;
            }
        } else if (this.a != null) {
            this.a.onComplete(platform, i, hashMap);
            this.a = null;
            this.c = 0;
        }
    }

    public void onCancel(Platform platform, int i) {
        if (this.a != null) {
            this.a.onCancel(platform, i);
            this.a = null;
            this.c = 0;
        }
    }

    private void b() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    f.c().d();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }

    private void a(Platform platform, final int i, final HashMap<String, Object> hashMap) {
        if (f.c().b() == null) {
            b();
        }
        final PlatformActionListener platformActionListener = this.a;
        this.a = new PlatformActionListener() {
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                String userId;
                d.this.a = platformActionListener;
                if (d.this.a != null) {
                    try {
                        if (ShareSDK.getEnableAuthTag()) {
                            String fromHashMap = new Hashon().fromHashMap(f.c().b());
                            if (!TextUtils.isEmpty(fromHashMap)) {
                                platform.getDb().put("userTags", fromHashMap);
                            }
                        }
                    } catch (Exception e) {
                    }
                    d.this.a.onComplete(platform, i, hashMap);
                }
                b bVar = new b();
                bVar.a = platform.getPlatformId();
                if ("TencentWeibo".equals(platform.getName())) {
                    userId = platform.getDb().get("name");
                } else {
                    userId = platform.getDb().getUserId();
                }
                bVar.b = userId;
                bVar.c = new Hashon().fromHashMap(hashMap);
                bVar.d = d.this.a(platform);
                cn.sharesdk.framework.b.d a2 = cn.sharesdk.framework.b.d.a();
                if (a2 != null) {
                    a2.a((c) bVar);
                }
            }

            public void onError(Platform platform, int i, Throwable th) {
                SSDKLog.b().w(th);
                d.this.a = platformActionListener;
                if (d.this.a != null) {
                    d.this.a.onComplete(platform, i, hashMap);
                }
            }

            public void onCancel(Platform platform, int i) {
                d.this.a = platformActionListener;
                if (d.this.a != null) {
                    d.this.a.onComplete(platform, i, hashMap);
                }
            }
        };
        platform.showUser(null);
    }

    private void b(Platform platform, int i, HashMap<String, Object> hashMap) {
        ShareParams shareParams;
        HashMap<String, Object> hashMap2;
        ShareParams shareParams2 = (ShareParams) this.b.remove(platform);
        if (hashMap != null) {
            shareParams = (ShareParams) hashMap.remove("ShareParams");
        } else {
            shareParams = shareParams2;
        }
        try {
            hashMap2 = (HashMap) hashMap.clone();
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            hashMap2 = hashMap;
        }
        if (shareParams != null) {
            cn.sharesdk.framework.b.b.f fVar = new cn.sharesdk.framework.b.b.f();
            fVar.n = shareParams.getCustomFlag();
            String userId = platform.getDb().getUserId();
            if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(userId)) {
                Platform platform2 = ShareSDK.getPlatform("Wechat");
                if (platform2 != null) {
                    userId = platform2.getDb().getUserId();
                }
            } else if ("TencentWeibo".equals(platform.getName())) {
                userId = platform.getDb().get("name");
            }
            fVar.b = userId;
            fVar.a = platform.getPlatformId();
            a filterShareContent = platform.filterShareContent(shareParams, hashMap2);
            if (filterShareContent != null) {
                fVar.c = filterShareContent.a;
                fVar.d = filterShareContent;
            }
            if (platform != null) {
                fVar.m = b(platform);
            }
            cn.sharesdk.framework.b.d a2 = cn.sharesdk.framework.b.d.a();
            if (a2 != null) {
                a2.a((c) fVar);
            }
        }
        if (this.a != null) {
            try {
                this.a.onComplete(platform, i, hashMap);
                this.a = null;
                this.c = 0;
            } catch (Throwable th2) {
                SSDKLog.b().d(th2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Platform platform, final int i, final Object obj) {
        if (f.c().b() == null) {
            b();
        }
        this.c = i;
        final PlatformActionListener platformActionListener = this.a;
        this.a = new PlatformActionListener() {
            public void onError(Platform platform, int i, Throwable th) {
                d.this.a = platformActionListener;
                if (d.this.a != null) {
                    d.this.a.onError(platform, i, th);
                }
            }

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                if (ShareSDK.getEnableAuthTag()) {
                    String fromHashMap = new Hashon().fromHashMap(f.c().b());
                    if (!TextUtils.isEmpty(fromHashMap)) {
                        platform.getDb().put("userTags", fromHashMap);
                    }
                }
                d.this.a = platformActionListener;
                platform.afterRegister(i, obj);
            }

            public void onCancel(Platform platform, int i) {
                d.this.a = platformActionListener;
                if (d.this.a != null) {
                    d.this.a.onCancel(platform, i);
                }
            }
        };
        platform.doAuthorize(null);
    }

    /* access modifiers changed from: private */
    public String a(Platform platform) {
        try {
            return a(platform.getDb(), new String[]{"nickname", MessageKey.MSG_ICON, "gender", "snsUserUrl", "resume", "secretType", "secret", "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            SSDKLog.b().w(th);
            return null;
        }
    }

    private String b(Platform platform) {
        PlatformDb db = platform.getDb();
        if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(db.getUserGender())) {
            Platform platform2 = ShareSDK.getPlatform("Wechat");
            if (platform2 != null) {
                db = platform2.getDb();
            }
        }
        try {
            return a(db, new String[]{"gender", "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            SSDKLog.b().w(th);
            return null;
        }
    }

    private String a(PlatformDb platformDb, String[] strArr) throws Throwable {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                sb2.append('|');
                sb.append('|');
            }
            i++;
            String str2 = platformDb.get(str);
            if (!TextUtils.isEmpty(str2)) {
                sb.append(str2);
                sb2.append(Data.urlEncode(str2, "utf-8"));
            }
        }
        SSDKLog.b().i("======UserData: " + sb.toString(), new Object[0]);
        return sb2.toString();
    }
}
