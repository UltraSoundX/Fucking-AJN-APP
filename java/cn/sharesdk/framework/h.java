package cn.sharesdk.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import cn.sharesdk.framework.authorize.b;
import cn.sharesdk.framework.authorize.f;
import cn.sharesdk.framework.b.b.a;
import cn.sharesdk.framework.b.b.c;
import cn.sharesdk.framework.b.d;
import cn.sharesdk.framework.loopshare.LoopShareResultListener;
import cn.sharesdk.framework.loopshare.MobLinkAPI;
import cn.sharesdk.framework.loopshare.MoblinkActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.i;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: ShareSDKCore */
public class h {
    public static ArrayList<Platform> a() {
        ArrayList<Platform> e = e();
        a(e);
        return e;
    }

    private static ArrayList<Platform> e() {
        String[] strArr = {"cn.sharesdk.douban.Douban", "cn.sharesdk.evernote.Evernote", "cn.sharesdk.facebook.Facebook", "cn.sharesdk.renren.Renren", "cn.sharesdk.sina.weibo.SinaWeibo", "cn.sharesdk.kaixin.KaiXin", "cn.sharesdk.linkedin.LinkedIn", "cn.sharesdk.system.email.Email", "cn.sharesdk.system.text.ShortMessage", "cn.sharesdk.tencent.qq.QQ", "cn.sharesdk.tencent.qzone.QZone", "cn.sharesdk.tencent.weibo.TencentWeibo", "cn.sharesdk.twitter.Twitter", "cn.sharesdk.wechat.friends.Wechat", "cn.sharesdk.wechat.moments.WechatMoments", "cn.sharesdk.wechat.favorite.WechatFavorite", "cn.sharesdk.youdao.YouDao", "cn.sharesdk.google.GooglePlus", "cn.sharesdk.foursquare.FourSquare", "cn.sharesdk.pinterest.Pinterest", "cn.sharesdk.flickr.Flickr", "cn.sharesdk.tumblr.Tumblr", "cn.sharesdk.dropbox.Dropbox", "cn.sharesdk.vkontakte.VKontakte", "cn.sharesdk.instagram.Instagram", "cn.sharesdk.yixin.friends.Yixin", "cn.sharesdk.yixin.moments.YixinMoments", "cn.sharesdk.mingdao.Mingdao", "cn.sharesdk.line.Line", "cn.sharesdk.kakao.story.KakaoStory", "cn.sharesdk.kakao.talk.KakaoTalk", "cn.sharesdk.whatsapp.WhatsApp", "cn.sharesdk.pocket.Pocket", "cn.sharesdk.instapaper.Instapaper", "cn.sharesdk.facebookmessenger.FacebookMessenger", "cn.sharesdk.alipay.friends.Alipay", "cn.sharesdk.alipay.moments.AlipayMoments", "cn.sharesdk.dingding.friends.Dingding", "cn.sharesdk.youtube.Youtube", "cn.sharesdk.meipai.Meipai", "cn.sharesdk.telegram.Telegram", "cn.sharesdk.cmcc.Cmcc", "cn.sharesdk.reddit.Reddit", "cn.sharesdk.telecom.Telecom", "cn.sharesdk.accountkit.Accountkit", "cn.sharesdk.douyin.Douyin", "cn.sharesdk.wework.Wework"};
        ArrayList<Platform> arrayList = new ArrayList<>();
        for (String importClass : strArr) {
            try {
                arrayList.add((Platform) ReflectHelper.newInstance(ReflectHelper.importClass(importClass), new Object[0]));
            } catch (Throwable th) {
            }
        }
        return arrayList;
    }

    public static void a(ArrayList<Platform> arrayList) {
        if (arrayList != null) {
            Collections.sort(arrayList, new Comparator<Platform>() {
                /* renamed from: a */
                public int compare(Platform platform, Platform platform2) {
                    if (platform.getSortId() != platform2.getSortId()) {
                        return platform.getSortId() - platform2.getSortId();
                    }
                    return platform.getPlatformId() - platform2.getPlatformId();
                }
            });
        }
    }

    public static void a(Activity activity) {
        b b = b.b();
        if (b != null) {
            b.a(activity);
        }
    }

    public static Activity b() {
        return b.b().a();
    }

    public static void a(boolean z) {
        f c = f.c();
        if (c != null) {
            c.a(z);
        }
    }

    public static boolean c() {
        return f.c().a();
    }

    public static Bitmap a(String str, int i, int i2) {
        i.a();
        return i.a(str, i, i2, "UTF_8", "H", "2", -16777216, -1);
    }

    public static void a(HashMap<String, Object> hashMap, MoblinkActionListener moblinkActionListener) {
        try {
            MobLinkAPI.a();
            MobLinkAPI.a(hashMap, moblinkActionListener);
        } catch (Throwable th) {
            SSDKLog.b().e("ShareSDKCore mobLinkGetMobID " + th, new Object[0]);
        }
    }

    public static void a(LoopShareResultListener loopShareResultListener) {
        try {
            MobLinkAPI.a();
            MobLinkAPI.b(loopShareResultListener);
        } catch (Throwable th) {
            SSDKLog.b().e("ShareSDKCore prepareLoopShare " + th, new Object[0]);
        }
    }

    public static HashMap<String, Object> d() {
        MobLinkAPI.a();
        return MobLinkAPI.c();
    }

    public static void a(Handler handler) {
        d a = d.a();
        if (a != null) {
            a.a(handler);
            a.d();
        }
    }

    public static void a(int i, Platform platform) {
        cn.sharesdk.framework.b.b.d dVar = new cn.sharesdk.framework.b.b.d();
        switch (i) {
            case 1:
                dVar.a = "SHARESDK_ENTER_SHAREMENU";
                break;
            case 2:
                dVar.a = "SHARESDK_CANCEL_SHAREMENU";
                break;
            case 3:
                dVar.a = "SHARESDK_EDIT_SHARE";
                break;
            case 4:
                dVar.a = "SHARESDK_FAILED_SHARE";
                break;
            case 5:
                dVar.a = "SHARESDK_CANCEL_SHARE";
                break;
        }
        if (platform != null) {
            dVar.b = platform.getPlatformId();
        }
        d a = d.a();
        if (a != null) {
            a.a((c) dVar);
        }
    }

    public static void a(String str, int i) {
        d a = d.a();
        if (a != null) {
            a aVar = new a();
            aVar.b = str;
            aVar.a = i;
            a.a((c) aVar);
        }
    }

    public static HashMap<Integer, HashMap<String, Object>> a(HashMap<String, Object> hashMap) {
        int i;
        if (hashMap == null || hashMap.size() <= 0) {
            return null;
        }
        ArrayList arrayList = (ArrayList) hashMap.get("fakelist");
        if (arrayList == null) {
            return null;
        }
        HashMap hashMap2 = new HashMap();
        EventRecorder.addBegin("ShareSDK", "parseDevInfo");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                try {
                    i = ResHelper.parseInt(String.valueOf(hashMap3.get("snsplat")));
                } catch (Throwable th) {
                    SSDKLog.b().w(th);
                    i = -1;
                }
                if (i != -1) {
                    hashMap2.put(Integer.valueOf(i), hashMap3);
                }
            }
        }
        EventRecorder.addEnd("ShareSDK", "parseDevInfo");
        return hashMap2;
    }
}
