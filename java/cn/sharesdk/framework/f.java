package cn.sharesdk.framework;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.loopshare.MoblinkActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.commons.ForbThrowable;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Field;
import java.util.HashMap;

/* compiled from: PlatformImpl */
public class f {
    /* access modifiers changed from: private */
    public Platform a;
    private PlatformDb b;
    /* access modifiers changed from: private */
    public d c;
    private int d;
    private int e;
    private boolean f;
    private boolean g = true;
    private boolean h;

    public f(Platform platform) {
        this.a = platform;
        String name = platform.getName();
        this.b = new PlatformDb(name, platform.getVersion());
        a(name);
        this.c = new d();
        c.a();
    }

    public void a(String str) {
        try {
            this.d = ResHelper.parseInt(String.valueOf(ShareSDK.b(str, "Id")).trim());
        } catch (Throwable th) {
            if (!(this.a instanceof CustomPlatform)) {
                SSDKLog.b().d(this.a.getName() + " failed to parse Id, this will cause method getId() always returens 0", new Object[0]);
            }
        }
        try {
            this.e = ResHelper.parseInt(String.valueOf(ShareSDK.b(str, "SortId")).trim());
        } catch (Throwable th2) {
            if (!(this.a instanceof CustomPlatform)) {
                SSDKLog.b().d(this.a.getName() + " failed to parse SortId, this won't cause any problem, don't worry", new Object[0]);
            }
        }
        String b2 = ShareSDK.b(str, "Enable");
        if (b2 == null) {
            this.h = true;
            if (!(this.a instanceof CustomPlatform)) {
                SSDKLog.b().d(this.a.getName() + " failed to parse Enable, this will cause platform always be enable", new Object[0]);
            }
        } else {
            this.h = "true".equals(b2.trim());
        }
        this.a.initDevInfo(str);
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public void a(PlatformActionListener platformActionListener) {
        this.c.a(platformActionListener);
    }

    public PlatformActionListener c() {
        return this.c.a();
    }

    public boolean d() {
        return this.b.isValid();
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean e() {
        return this.f;
    }

    public boolean f() {
        return this.h;
    }

    private String a(int i) {
        return "ShareSDK_" + this.a.getName() + "_" + b(i);
    }

    private String b(int i) {
        switch (i) {
            case 1:
                return "ACTION_AUTHORIZING";
            case 2:
                return "ACTION_GETTING_FRIEND_LIST";
            case 5:
                return "ACTION_SENDING_DIRECT_MESSAGE";
            case 6:
                return "ACTION_FOLLOWING_USER";
            case 7:
                return "ACTION_TIMELINE";
            case 8:
                return "ACTION_USER_INFOR";
            case 9:
                return "ACTION_SHARE";
            case 10:
                return "ACTION_GETTING_BILATERAL_LIST";
            case 11:
                return "ACTION_GETTING_FOLLOWER_LIST";
            default:
                return "ACTION_CUSTOMER";
        }
    }

    /* access modifiers changed from: private */
    public boolean j() {
        boolean z = false;
        if (ShareSDK.a()) {
            String a2 = a(this.a.getPlatformId(), "covert_url", (String) null);
            if (a2 != null) {
                a2.trim();
            }
            if (!"false".equals(a2)) {
                z = true;
            }
            this.g = z;
            this.a.setNetworkDevinfo();
            return true;
        }
        try {
            if (!ShareSDK.b()) {
                return false;
            }
            String a3 = a(this.a.getPlatformId(), "covert_url", (String) null);
            if (a3 != null) {
                a3.trim();
            }
            this.g = !"false".equals(a3);
            this.a.setNetworkDevinfo();
            return true;
        } catch (Throwable th) {
            SSDKLog.b().w(th);
            return false;
        }
    }

    public String a(int i, String str, String str2) {
        String a2 = ShareSDK.a(i, str);
        if (TextUtils.isEmpty(a2) || "null".equals(a2)) {
            return this.a.getDevinfo(this.a.getName(), str2);
        }
        return a2;
    }

    public void a(int i, Object obj) {
        this.c.a(this.a, i, obj);
    }

    /* access modifiers changed from: protected */
    public void b(int i, Object obj) {
        Field[] fields;
        Object obj2;
        switch (i) {
            case 1:
                if (this.c != null) {
                    this.c.onComplete(this.a, 1, null);
                    return;
                }
                return;
            case 2:
                Object[] objArr = (Object[]) obj;
                this.a.getFriendList(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
                return;
            case 6:
                this.a.follow((String) obj);
                return;
            case 7:
                Object[] objArr2 = (Object[]) obj;
                this.a.timeline(((Integer) objArr2[0]).intValue(), ((Integer) objArr2[1]).intValue(), (String) objArr2[2]);
                return;
            case 8:
                this.a.userInfor(obj == null ? null : (String) obj);
                return;
            case 9:
                final ShareParams shareParams = (ShareParams) obj;
                HashMap map = shareParams.toMap();
                for (Field field : shareParams.getClass().getFields()) {
                    if (map.get(field.getName()) == null) {
                        field.setAccessible(true);
                        try {
                            obj2 = field.get(shareParams);
                        } catch (Throwable th) {
                            SSDKLog.b().w(th);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            map.put(field.getName(), obj2);
                        }
                    }
                }
                if (this.c instanceof d) {
                    this.c.a(this.a, shareParams);
                }
                try {
                    if (shareParams.getLoopshareCustomParams().size() <= 0 || shareParams.getLoopshareCustomParams() == null) {
                        this.a.doShare(shareParams);
                        return;
                    } else if (this.a.getName().equals("QQ")) {
                        if (!TextUtils.isEmpty(shareParams.getTitleUrl())) {
                            ShareSDK.mobLinkGetMobID(shareParams.getLoopshareCustomParams(), new MoblinkActionListener() {
                                public void onResult(Object obj) {
                                    if (!TextUtils.isEmpty(Uri.parse(shareParams.getTitleUrl()).getEncodedQuery())) {
                                        shareParams.setTitleUrl(shareParams.getTitleUrl() + "&mobid=" + obj);
                                    } else {
                                        shareParams.setTitleUrl(shareParams.getTitleUrl() + "?mobid=" + obj);
                                    }
                                    new Thread() {
                                        public void run() {
                                            super.run();
                                            f.this.a.doShare(shareParams);
                                        }
                                    }.start();
                                }

                                public void onError(Throwable th) {
                                    if (f.this.c != null) {
                                        f.this.c.onError(f.this.a, 9, th);
                                    }
                                }
                            });
                            return;
                        } else if (this.c != null) {
                            this.c.onError(this.a, 9, new Throwable("TitleUrl cannot be empty if setLoopshareCustomParams is used in QQ"));
                            return;
                        } else {
                            return;
                        }
                    } else if (!TextUtils.isEmpty(shareParams.getUrl())) {
                        if (this.c != null) {
                            ShareSDK.mobLinkGetMobID(shareParams.getLoopshareCustomParams(), new MoblinkActionListener() {
                                public void onResult(Object obj) {
                                    if (!TextUtils.isEmpty(Uri.parse(shareParams.getUrl()).getEncodedQuery())) {
                                        shareParams.setUrl(shareParams.getUrl() + "&mobid=" + obj);
                                    } else {
                                        shareParams.setUrl(shareParams.getUrl() + "?mobid=" + obj);
                                    }
                                    new Thread() {
                                        public void run() {
                                            super.run();
                                            f.this.a.doShare(shareParams);
                                        }
                                    }.start();
                                }

                                public void onError(Throwable th) {
                                    if (f.this.c != null) {
                                        f.this.c.onError(f.this.a, 9, th);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    } else if (this.c != null) {
                        this.c.onError(this.a, 9, new Throwable("SetUrl cannot be empty if setLoopshareCustomParams is used"));
                        return;
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    SSDKLog.b().d("PlatformImpl platform.doshare() " + th2, new Object[0]);
                    return;
                }
            default:
                Object[] objArr3 = (Object[]) obj;
                this.a.doCustomerProtocol(String.valueOf(objArr3[0]), String.valueOf(objArr3[1]), i, (HashMap) objArr3[2], (HashMap) objArr3[3]);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void a(d dVar, int i) {
        if (dVar != null) {
            dVar.onError(this.a, i, new ForbThrowable());
        }
    }

    /* access modifiers changed from: private */
    public void b(d dVar, int i) {
        if (dVar != null) {
            dVar.onError(this.a, i, new Throwable("'appkey' is illegal"));
        }
    }

    /* access modifiers changed from: protected */
    public void c(final int i, final Object obj) {
        new Thread(a(i)) {
            public void run() {
                try {
                    if (!c.a().b() && a.a()) {
                        f.this.j();
                    }
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    if (c.a().b()) {
                        f.this.a(f.this.c, i);
                    } else if (!a.a()) {
                        f.this.b(f.this.c, i);
                    } else if (f.this.a.checkAuthorize(i, obj)) {
                        f.this.b(i, obj);
                    }
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }.start();
    }

    public void a(final String[] strArr) {
        new Thread(a(1)) {
            public void run() {
                try {
                    if (!c.a().b() && a.a()) {
                        f.this.j();
                    }
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }.start();
        new Thread() {
            public void run() {
                try {
                    if (c.a().b()) {
                        f.this.a(f.this.c, 1);
                    } else if (!a.a()) {
                        f.this.b(f.this.c, 1);
                    } else {
                        f.this.a.doAuthorize(strArr);
                    }
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
            }
        }.start();
    }

    public void a(ShareParams shareParams) {
        if (shareParams != null) {
            try {
                if (!shareParams.getOpenCustomEven()) {
                    ShareSDK.logDemoEvent(3, this.a);
                }
            } catch (Throwable th) {
            }
            c(9, shareParams);
        } else if (this.c != null) {
            this.c.onError(this.a, 9, new NullPointerException());
        }
    }

    public void b(String str) {
        c(6, str);
    }

    public void a(String str, int i, int i2) {
        c(7, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    public void c(String str) {
        c(8, str);
    }

    public void a(int i, int i2, String str) {
        c(2, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    public void a(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        c(655360 | s, new Object[]{str, str2, hashMap, hashMap2});
    }

    public PlatformDb g() {
        return this.b;
    }

    public void h() {
        this.b.removeAccount();
    }

    public String a(String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.g) {
            SSDKLog.b().i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else if (TextUtils.isEmpty(str)) {
            SSDKLog.b().i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else {
            String a2 = ShareSDK.a(str, z, this.a.getPlatformId(), k());
            SSDKLog.b().i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return a2;
        }
    }

    private String k() {
        StringBuilder sb = new StringBuilder();
        try {
            if ("TencentWeibo".equals(this.a.getName())) {
                SSDKLog.b().i("user id %s ==>>", g().getUserName());
                sb.append(Data.urlEncode(g().getUserName(), "utf-8"));
            } else {
                sb.append(Data.urlEncode(g().getUserId(), "utf-8"));
            }
            sb.append("|").append(Data.urlEncode(g().get("secretType"), "utf-8"));
            sb.append("|").append(Data.urlEncode(g().get("gender"), "utf-8"));
            sb.append("|").append(Data.urlEncode(g().get("birthday"), "utf-8"));
            sb.append("|").append(Data.urlEncode(g().get("educationJSONArrayStr"), "utf-8"));
            sb.append("|").append(Data.urlEncode(g().get("workJSONArrayStr"), "utf-8"));
        } catch (Throwable th) {
            SSDKLog.b().w(th);
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public PlatformActionListener i() {
        return this.c;
    }

    public String d(String str) {
        return ShareSDK.a(str);
    }

    public String a(Bitmap bitmap) {
        return ShareSDK.a(bitmap);
    }
}
