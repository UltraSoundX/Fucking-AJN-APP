package cn.sharesdk.tencent.qq;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.b.b.f.a;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.tencent.android.tpush.common.MessageKey;
import java.util.HashMap;

public class QQ extends Platform {
    public static final String NAME = QQ.class.getSimpleName();
    private String a;
    private boolean b = true;
    private boolean c;

    /* access modifiers changed from: protected */
    public void initDevInfo(String str) {
        this.a = getDevinfo("AppId");
        this.c = "true".equals(getDevinfo("BypassApproval"));
        if (this.a == null || this.a.length() <= 0) {
            this.a = getDevinfo("QZone", "AppId");
            if (this.a != null && this.a.length() > 0) {
                copyDevinfo("QZone", NAME);
                this.a = getDevinfo("AppId");
                SSDKLog.b().d("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public int getPlatformId() {
        return 24;
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
            this.a = getNetworkDevinfo(6, "app_id", "AppId");
            if (this.a != null && this.a.length() > 0) {
                copyNetworkDevinfo(6, 24);
                this.a = getNetworkDevinfo("app_id", "AppId");
                SSDKLog.b().d("Try to use the dev info of QZone, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
    }

    public boolean isClientValid() {
        c a2 = c.a((Platform) this);
        a2.a(this.a);
        return a2.b();
    }

    /* access modifiers changed from: protected */
    public void doAuthorize(String[] strArr) {
        final c a2 = c.a((Platform) this);
        a2.a(this.a);
        a2.a(strArr);
        a2.a((AuthorizeListener) new AuthorizeListener() {
            public void onError(Throwable th) {
                if (QQ.this.listener != null) {
                    QQ.this.listener.onError(QQ.this, 1, th);
                }
            }

            public void onComplete(Bundle bundle) {
                String string = bundle.getString("open_id");
                String string2 = bundle.getString("access_token");
                String string3 = bundle.getString("expires_in");
                QQ.this.db.putToken(string2);
                QQ.this.db.putTokenSecret("");
                try {
                    QQ.this.db.putExpiresIn(ResHelper.parseLong(string3));
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                }
                QQ.this.db.putUserId(string);
                String string4 = bundle.getString("pf");
                String string5 = bundle.getString("pfkey");
                String string6 = bundle.getString("pay_token");
                QQ.this.db.put("pf", string4);
                QQ.this.db.put("pfkey", string5);
                QQ.this.db.put("pay_token", string6);
                a2.b(string);
                a2.d(string2);
                a2.a();
                QQ.this.afterRegister(1, null);
            }

            public void onCancel() {
                if (QQ.this.listener != null) {
                    QQ.this.listener.onCancel(QQ.this, 1);
                }
            }
        }, isSSODisable());
    }

    /* access modifiers changed from: protected */
    public boolean checkAuthorize(int i, Object obj) {
        c a2 = c.a((Platform) this);
        if (a2.b() && this.c && i == 9) {
            return true;
        }
        if (isAuthValid() || (i == 9 && obj != null && (obj instanceof ShareParams) && !((ShareParams) obj).isShareTencentWeibo())) {
            a2.a(this.a);
            a2.b(this.db.getUserId());
            a2.d(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    /* access modifiers changed from: protected */
    public void doShare(ShareParams shareParams) {
        c a2 = c.a((Platform) this);
        if (!this.c || !a2.b()) {
            String title = shareParams.getTitle();
            String text = shareParams.getText();
            String imagePath = shareParams.getImagePath();
            String imageUrl = shareParams.getImageUrl();
            String musicUrl = shareParams.getMusicUrl();
            String titleUrl = shareParams.getTitleUrl();
            boolean isShareTencentWeibo = shareParams.isShareTencentWeibo();
            int hidden = shareParams.getHidden();
            String qQMiniProgramAppid = shareParams.getQQMiniProgramAppid();
            String qQMiniProgramPath = shareParams.getQQMiniProgramPath();
            String qQMiniProgramType = shareParams.getQQMiniProgramType();
            int shareType = shareParams.getShareType();
            if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(text) || !TextUtils.isEmpty(imagePath) || !TextUtils.isEmpty(imageUrl) || !TextUtils.isEmpty(musicUrl) || !TextUtils.isEmpty(qQMiniProgramAppid)) {
                if (!TextUtils.isEmpty(titleUrl)) {
                    titleUrl = getShortLintk(titleUrl, false);
                    shareParams.setTitleUrl(titleUrl);
                }
                if (!TextUtils.isEmpty(text)) {
                    text = getShortLintk(text, false);
                    shareParams.setText(text);
                }
                a2.a(title, titleUrl, text, imagePath, imageUrl, musicUrl, new PlatformActionListener() {
                    public void onError(Platform platform, int i, Throwable th) {
                        if (QQ.this.listener != null) {
                            QQ.this.listener.onError(QQ.this, 9, th);
                        }
                    }

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        HashMap hashMap2 = new HashMap();
                        if (hashMap != null) {
                            hashMap2.putAll(hashMap);
                        }
                        if (QQ.this.listener != null) {
                            QQ.this.listener.onComplete(QQ.this, 9, hashMap2);
                        }
                    }

                    public void onCancel(Platform platform, int i) {
                        if (QQ.this.listener != null) {
                            QQ.this.listener.onCancel(QQ.this, 9);
                        }
                    }
                }, isShareTencentWeibo, hidden, qQMiniProgramAppid, qQMiniProgramPath, qQMiniProgramType, shareType);
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new Throwable("qq share must have one param at least"));
            }
        } else {
            try {
                a2.a(this, shareParams, this.listener);
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                }
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
                HashMap e = c.a((Platform) this).e(str);
                if (e == null || e.size() <= 0) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (!e.containsKey("ret")) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (((Integer) e.get("ret")).intValue() == 0) {
                    if (str == this.db.getUserId()) {
                        this.db.put("nickname", String.valueOf(e.get("nickname")));
                        if (e.containsKey("figureurl_qq_2")) {
                            this.db.put(MessageKey.MSG_ICON, String.valueOf(e.get("figureurl_qq_2")));
                        } else if (e.containsKey("figureurl_qq_1")) {
                            this.db.put(MessageKey.MSG_ICON, String.valueOf(e.get("figureurl_qq_1")));
                        }
                        if (e.containsKey("figureurl_2")) {
                            this.db.put("iconQzone", String.valueOf(e.get("figureurl_2")));
                        } else if (e.containsKey("figureurl_1")) {
                            this.db.put("iconQzone", String.valueOf(e.get("figureurl_1")));
                        } else if (e.containsKey("figureurl")) {
                            this.db.put("iconQzone", String.valueOf(e.get("figureurl")));
                        }
                        this.db.put("secretType", String.valueOf(e.get("is_yellow_vip")));
                        if (String.valueOf(e.get("is_yellow_vip")).equals("1")) {
                            this.db.put("snsUserLevel", String.valueOf(e.get("level")));
                        }
                        String valueOf = String.valueOf(e.get("gender"));
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
                            e.put("userTags", this.db.get("userTags"));
                        }
                        this.listener.onComplete(this, 8, e);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(e)));
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
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    /* access modifiers changed from: protected */
    public a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap) {
        a aVar = new a();
        String titleUrl = shareParams.getTitleUrl();
        aVar.c.add(titleUrl);
        aVar.a = this.a;
        String text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            aVar.b = text;
        }
        String imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        if (!TextUtils.isEmpty(imagePath)) {
            aVar.e.add(imagePath);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            aVar.d.add(imageUrl);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("title", shareParams.getTitle());
        hashMap2.put("url", titleUrl);
        hashMap2.put("imageLocalUrl", imagePath);
        hashMap2.put("summary", text);
        hashMap2.put("appName", DeviceHelper.getInstance(MobSDK.getContext()).getAppName());
        aVar.g = hashMap2;
        return aVar;
    }

    /* access modifiers changed from: protected */
    public String uploadImageToFileServer(String str) {
        return super.uploadImageToFileServer(str);
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
        return this.b;
    }
}
