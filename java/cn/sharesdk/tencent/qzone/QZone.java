package cn.sharesdk.tencent.qzone;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.e;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.util.HashMap;

public class QZone extends Platform {
    public static final String NAME = QZone.class.getSimpleName();
    private String a;
    private boolean b;

    /* access modifiers changed from: protected */
    public void initDevInfo(String str) {
        this.a = getDevinfo("AppId");
        this.b = "true".equals(getDevinfo("BypassApproval"));
        if (this.a == null || this.a.length() <= 0) {
            this.a = getDevinfo("QQ", "AppId");
            if (this.a != null && this.a.length() > 0) {
                copyDevinfo("QQ", NAME);
                this.a = getDevinfo("AppId");
                SSDKLog.b().d("Try to use the dev info of QQ, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public int getPlatformId() {
        return 6;
    }

    public String getName() {
        return NAME;
    }

    public int getVersion() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public void setNetworkDevinfo() {
        this.a = getNetworkDevinfo("app_id", "AppId");
        if (this.a == null || this.a.length() <= 0) {
            this.a = getNetworkDevinfo(24, "app_id", "AppId");
            if (this.a != null && this.a.length() > 0) {
                copyNetworkDevinfo(24, 6);
                this.a = getNetworkDevinfo("app_id", "AppId");
                SSDKLog.b().d("Try to use the dev info of QQ, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public boolean isClientValid() {
        b a2 = b.a((Platform) this);
        a2.a(this.a);
        return a2.d();
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
        final b a2 = b.a((Platform) this);
        a2.a(this.a);
        a2.a(strArr);
        a2.a((AuthorizeListener) new AuthorizeListener() {
            public void onError(Throwable th) {
                if (QZone.this.listener != null) {
                    QZone.this.listener.onError(QZone.this, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                String string = bundle.getString("open_id");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                QZone.this.db.putToken(string2);
                QZone.this.db.putTokenSecret("");
                try {
                    QZone.this.db.putExpiresIn(ResHelper.parseLong(string3));
                } catch (Throwable th) {
                    SSDKLog.b().d(th);
                }
                QZone.this.db.putUserId(string);
                String string4 = bundle.getString("pf");
                String string5 = bundle.getString("pfkey");
                String string6 = bundle.getString("pay_token");
                QZone.this.db.put("pf", string4);
                QZone.this.db.put("pfkey", string5);
                QZone.this.db.put("pay_token", string6);
                a2.b(string);
                a2.c(string2);
                a2.a();
                QZone.this.afterRegister(1, null);
            }

            public void onCancel() {
                if (QZone.this.listener != null) {
                    QZone.this.listener.onCancel(QZone.this, 1);
                }
            }
        }, isSSODisable());
    }

    /* access modifiers changed from: protected */
    public boolean checkAuthorize(int i, Object obj) {
        b a2 = b.a((Platform) this);
        if (a2.b() && this.b && i == 9) {
            return true;
        }
        if (isAuthValid() || i == 9) {
            a2.a(this.a);
            a2.b(this.db.getUserId());
            a2.c(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    /* access modifiers changed from: protected */
    public void doShare(ShareParams shareParams) {
        if (b.a((Platform) this).b() && this.b) {
            try {
                a(shareParams);
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                }
            }
        } else if (shareParams.isShareTencentWeibo()) {
            b(shareParams);
        } else {
            c(shareParams);
        }
    }

    private void a(ShareParams shareParams) throws Throwable {
        e eVar = new e();
        eVar.a(TbsConfig.APP_QZONE, "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
        eVar.a(shareParams, (Platform) this);
        HashMap hashMap = new HashMap();
        hashMap.put("ShareParams", shareParams);
        this.listener.onComplete(this, 9, hashMap);
    }

    private void b(final ShareParams shareParams) {
        HashMap a2;
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
        try {
            if (TextUtils.isEmpty(imagePath) && !TextUtils.isEmpty(imageUrl)) {
                shareParams.setImagePath(BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl));
                doShare(shareParams);
            } else if (!isAuthValid()) {
                final PlatformActionListener platformActionListener = getPlatformActionListener();
                setPlatformActionListener(new PlatformActionListener() {
                    public void onError(Platform platform, int i, Throwable th) {
                        if (platformActionListener != null) {
                            platformActionListener.onError(platform, 9, th);
                        }
                    }

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        QZone.this.setPlatformActionListener(platformActionListener);
                        QZone.this.doShare(shareParams);
                    }

                    public void onCancel(Platform platform, int i) {
                        if (platformActionListener != null) {
                            platformActionListener.onCancel(platform, 9);
                        }
                    }
                });
                authorize();
            } else {
                String text = shareParams.getText();
                if (!TextUtils.isEmpty(text)) {
                    String shortLintk = getShortLintk(text, false);
                    shareParams.setText(shortLintk);
                    b a3 = b.a((Platform) this);
                    if (isShareTencentWeibo) {
                        a2 = a3.b(imagePath, shortLintk);
                    } else {
                        a2 = a3.a(imagePath, shortLintk);
                    }
                    if (a2 == null && this.listener != null) {
                        this.listener.onError(this, 9, new Throwable("response is empty"));
                    }
                    a2.put("ShareParams", shareParams);
                    if (this.listener != null) {
                        this.listener.onComplete(this, 9, a2);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 9, new Throwable("share params' value of text is empty!"));
                }
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    private void c(final ShareParams shareParams) {
        try {
            String imageUrl = shareParams.getImageUrl();
            String imagePath = shareParams.getImagePath();
            if (isClientValid()) {
                if (TextUtils.isEmpty(imagePath) || !new File(imagePath).exists()) {
                    imagePath = imageUrl;
                }
                String title = shareParams.getTitle();
                String titleUrl = shareParams.getTitleUrl();
                String site = shareParams.getSite();
                String text = shareParams.getText();
                String filePath = shareParams.getFilePath();
                int shareType = shareParams.getShareType();
                if (!TextUtils.isEmpty(text)) {
                    text = getShortLintk(text, false);
                    shareParams.setText(text);
                }
                if (!TextUtils.isEmpty(titleUrl)) {
                    titleUrl = getShortLintk(titleUrl, false);
                    shareParams.setTitleUrl(titleUrl);
                }
                b.a((Platform) this).a(shareType, title, titleUrl, text, imagePath, site, filePath, shareParams.getQQMiniProgramAppid(), shareParams.getQQMiniProgramPath(), shareParams.getQQMiniProgramType(), shareParams.getImageUrl(), new PlatformActionListener() {
                    public void onError(Platform platform, int i, Throwable th) {
                        if (QZone.this.listener != null) {
                            QZone.this.listener.onError(QZone.this, 9, th);
                        }
                    }

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        if (QZone.this.listener != null) {
                            hashMap.put("ShareParams", shareParams);
                            QZone.this.listener.onComplete(QZone.this, 9, hashMap);
                        }
                    }

                    public void onCancel(Platform platform, int i) {
                        if (QZone.this.listener != null) {
                            QZone.this.listener.onCancel(QZone.this, 9);
                        }
                    }
                });
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new QQClientNotExistException());
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    /* access modifiers changed from: protected */
    public void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    /* access modifiers changed from: protected */
    public void userInfor(String str) {
        if (str == null || str.length() < 0) {
            str = this.db.getUserId();
        }
        if (str != null && str.length() >= 0) {
            try {
                HashMap d = b.a((Platform) this).d(str);
                if (d == null || d.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (!d.containsKey("ret")) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (((Integer) d.get("ret")).intValue() == 0) {
                    if (str == this.db.getUserId()) {
                        this.db.put("nickname", String.valueOf(d.get("nickname")));
                        if (d.containsKey("figureurl_qq_2")) {
                            this.db.put("iconQQ", String.valueOf(d.get("figureurl_qq_2")));
                        } else if (d.containsKey("figureurl_qq_1")) {
                            this.db.put("iconQQ", String.valueOf(d.get("figureurl_qq_1")));
                        }
                        if (d.containsKey("figureurl_2")) {
                            this.db.put(MessageKey.MSG_ICON, String.valueOf(d.get("figureurl_2")));
                        } else if (d.containsKey("figureurl_1")) {
                            this.db.put(MessageKey.MSG_ICON, String.valueOf(d.get("figureurl_1")));
                        } else if (d.containsKey("figureurl")) {
                            this.db.put(MessageKey.MSG_ICON, String.valueOf(d.get("figureurl")));
                        }
                        this.db.put("secretType", String.valueOf(d.get("is_yellow_vip")));
                        if (String.valueOf(d.get("is_yellow_vip")).equals("1")) {
                            this.db.put("snsUserLevel", String.valueOf(d.get("level")));
                        }
                        String valueOf = String.valueOf(d.get("gender"));
                        int stringRes = ResHelper.getStringRes(MobSDK.getContext(), "ssdk_gender_male");
                        int stringRes2 = ResHelper.getStringRes(MobSDK.getContext(), "ssdk_gender_female");
                        if (valueOf.equals(MobSDK.getContext().getString(stringRes))) {
                            this.db.put("gender", "0");
                        } else if (valueOf.equals(MobSDK.getContext().getString(stringRes2))) {
                            this.db.put("gender", "1");
                        } else {
                            this.db.put("gender", "2");
                        }
                    }
                    if (this.listener != null) {
                        if (this.db.get("userTags") != null) {
                            d.put("userTags", this.db.get("userTags"));
                        }
                        this.listener.onComplete(this, 8, d);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(d)));
                }
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("qq account is null"));
        }
    }

    /* access modifiers changed from: protected */
    public void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    /* access modifiers changed from: protected */
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        HashMap a2 = b.a((Platform) this).a(str, str2, hashMap, hashMap2);
        if (a2 == null || a2.size() <= 0) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable());
            }
        } else if (!a2.containsKey("ret")) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable());
            }
        } else if (((Integer) a2.get("ret")).intValue() != 0) {
            if (this.listener != null) {
                this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a2)));
            }
        } else if (this.listener != null) {
            this.listener.onComplete(this, i, a2);
        }
    }

    /* access modifiers changed from: protected */
    public a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        aVar.b = shareParams.getText();
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        if (imagePath != null) {
            aVar.e.add(imagePath);
        } else if (hashMap.get("large_url") != null) {
            aVar.d.add(String.valueOf(hashMap.get("large_url")));
        } else if (hashMap.get("small_url") != null) {
            aVar.d.add(String.valueOf(hashMap.get("small_url")));
        } else if (imageUrl != null) {
            aVar.d.add(imageUrl);
        }
        String titleUrl = shareParams.getTitleUrl();
        if (titleUrl != null) {
            aVar.c.add(titleUrl);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("titleUrl", shareParams.getTitleUrl());
        hashMap2.put("site", shareParams.getSite());
        aVar.g = hashMap2;
        return aVar;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    public boolean hasShareCallback() {
        return true;
    }
}
