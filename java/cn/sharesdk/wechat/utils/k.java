package cn.sharesdk.wechat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.e;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.utils.h.a;
import com.mob.MobSDK;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

/* compiled from: WechatHelper */
public class k {
    private static k a;
    private i b = new i();
    private j c;
    private String d;
    private String e;
    private boolean f;
    private int g;

    public static k a() {
        if (a == null) {
            a = new k();
        }
        return a;
    }

    public void a(String str) {
        this.e = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(int i) {
        this.g = i;
    }

    private k() {
    }

    public void a(j jVar) throws Throwable {
        this.c = jVar;
        a aVar = new a();
        aVar.a = "snsapi_userinfo";
        aVar.b = "sharesdk_wechat_auth";
        this.b.a((l) aVar, false);
    }

    public boolean b() {
        return this.b.a();
    }

    public void a(j jVar, ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        Platform b2 = jVar.b();
        String str = TbsConfig.APP_WX;
        String str2 = ((Integer) shareParams.get("scene", Integer.class)).intValue() == 1 ? "com.tencent.mm.ui.tools.ShareToTimeLineUI" : "com.tencent.mm.ui.tools.ShareImgUI";
        e eVar = new e();
        eVar.a(str, str2);
        eVar.a(shareParams, b2);
        HashMap hashMap = new HashMap();
        hashMap.put("ShareParams", shareParams);
        platformActionListener.onComplete(b2, 9, hashMap);
    }

    public void b(j jVar) throws Throwable {
        Platform b2 = jVar.b();
        ShareParams a2 = jVar.a();
        PlatformActionListener c2 = jVar.c();
        int shareType = a2.getShareType();
        if (shareType == 11 && e() < 620756993) {
            shareType = 4;
        }
        String title = a2.getTitle();
        String text = a2.getText();
        int scence = a2.getScence();
        String imagePath = a2.getImagePath();
        String imageUrl = a2.getImageUrl();
        Bitmap imageData = a2.getImageData();
        String musicUrl = a2.getMusicUrl();
        String url = a2.getUrl();
        String filePath = a2.getFilePath();
        String extInfo = a2.getExtInfo();
        switch (shareType) {
            case 1:
                a(title, text, scence, jVar);
                return;
            case 2:
                if (imagePath != null && imagePath.length() > 0) {
                    a(MobSDK.getContext(), title, text, imagePath, scence, jVar);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    a(MobSDK.getContext(), title, text, imageData, scence, jVar);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    a(MobSDK.getContext(), title, text, "", scence, jVar);
                    return;
                } else {
                    String downloadBitmap = BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl);
                    try {
                        if (!TextUtils.isEmpty(downloadBitmap)) {
                            File file = new File(downloadBitmap);
                            if (file != null) {
                                File parentFile = file.getParentFile();
                                if (parentFile != null && parentFile.isDirectory()) {
                                    File file2 = new File(parentFile.getAbsolutePath(), ".nomedia");
                                    if (file2 != null && (!file2.exists() || !file2.isFile())) {
                                        file2.createNewFile();
                                    }
                                }
                            }
                        }
                    } catch (Throwable th) {
                        SSDKLog.b().d("when share iamge wechat that create nomedia catch " + th, new Object[0]);
                    }
                    a(MobSDK.getContext(), title, text, downloadBitmap, scence, jVar);
                    return;
                }
            case 4:
                String shortLintk = b2.getShortLintk(url, false);
                jVar.a().setUrl(shortLintk);
                if (imagePath != null && imagePath.length() > 0) {
                    b(MobSDK.getContext(), title, text, shortLintk, imagePath, scence, jVar);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    b(MobSDK.getContext(), title, text, shortLintk, imageData, scence, jVar);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    b(MobSDK.getContext(), title, text, shortLintk, "", scence, jVar);
                    return;
                } else {
                    b(MobSDK.getContext(), title, text, shortLintk, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, jVar);
                    return;
                }
            case 5:
                String shortLintk2 = b2.getShortLintk(musicUrl + " " + url, false);
                String str = shortLintk2.split(" ")[0];
                String str2 = shortLintk2.split(" ")[1];
                if (imagePath != null && imagePath.length() > 0) {
                    a(MobSDK.getContext(), title, text, str, str2, imagePath, scence, jVar);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    a(MobSDK.getContext(), title, text, str, str2, imageData, scence, jVar);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    a(MobSDK.getContext(), title, text, str, str2, "", scence, jVar);
                    return;
                } else {
                    a(MobSDK.getContext(), title, text, str, str2, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, jVar);
                    return;
                }
            case 6:
                String shortLintk3 = b2.getShortLintk(url, false);
                jVar.a().setUrl(shortLintk3);
                if (imagePath != null && imagePath.length() > 0) {
                    a(MobSDK.getContext(), title, text, shortLintk3, imagePath, scence, jVar);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    a(MobSDK.getContext(), title, text, shortLintk3, imageData, scence, jVar);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    a(MobSDK.getContext(), title, text, shortLintk3, "", scence, jVar);
                    return;
                } else {
                    a(MobSDK.getContext(), title, text, shortLintk3, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, jVar);
                    return;
                }
            case 7:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SAHRE_APP");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SAHRE_APP");
                } else if (imagePath != null && imagePath.length() > 0) {
                    b(MobSDK.getContext(), title, text, filePath, extInfo, imagePath, scence, jVar);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    b(MobSDK.getContext(), title, text, filePath, extInfo, imageData, scence, jVar);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    b(MobSDK.getContext(), title, text, filePath, extInfo, "", scence, jVar);
                    return;
                } else {
                    b(MobSDK.getContext(), title, text, filePath, extInfo, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, jVar);
                    return;
                }
            case 8:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_FILE");
                } else if (imagePath != null && imagePath.length() > 0) {
                    c(MobSDK.getContext(), title, text, filePath, imagePath, scence, jVar);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    c(MobSDK.getContext(), title, text, filePath, imageData, scence, jVar);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    c(MobSDK.getContext(), title, text, filePath, "", scence, jVar);
                    return;
                } else {
                    c(MobSDK.getContext(), title, text, filePath, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, jVar);
                    return;
                }
            case 9:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_EMOJI");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SHARE_EMOJI");
                } else if (imagePath != null && imagePath.length() > 0) {
                    b(MobSDK.getContext(), title, text, imagePath, scence, jVar);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    b(MobSDK.getContext(), title, text, new NetworkHelper().downloadCache(MobSDK.getContext(), imageUrl, "images", true, null), scence, jVar);
                    return;
                } else if (imageData == null || imageData.isRecycled()) {
                    b(MobSDK.getContext(), title, text, "", scence, jVar);
                    return;
                } else {
                    b(MobSDK.getContext(), title, text, imageData, scence, jVar);
                    return;
                }
            case 11:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SAHRE_WXMINIPROGRAM");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SAHRE_WXMINIPROGRAM");
                } else if (TextUtils.isEmpty(this.d)) {
                    c2.onError(b2, 9, new Throwable("checkArgs fail, UserName or Path is invalid"));
                    return;
                } else {
                    String shortLintk4 = b2.getShortLintk(url, false);
                    jVar.a().setUrl(shortLintk4);
                    if (imagePath != null && imagePath.length() > 0) {
                        a(MobSDK.getContext(), shortLintk4, this.d, this.e, title, text, imagePath, scence, jVar);
                        return;
                    } else if (imageData != null && !imageData.isRecycled()) {
                        a(MobSDK.getContext(), shortLintk4, this.d, this.e, title, text, imageData, scence, jVar);
                        return;
                    } else if (imageUrl == null || imageUrl.length() <= 0) {
                        a(MobSDK.getContext(), shortLintk4, this.d, this.e, title, text, "", scence, jVar);
                        return;
                    } else {
                        a(MobSDK.getContext(), shortLintk4, this.d, this.e, title, text, BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl), scence, jVar);
                        return;
                    }
                }
            case 12:
                if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.e)) {
                    c2.onError(b2, 9, new Throwable("checkArgs fail, UserName or Path is invalid"));
                    return;
                }
                a(this.d, this.e);
                return;
            default:
                if (c2 != null) {
                    c2.onError(b2, 9, new IllegalArgumentException("shareType = " + shareType));
                    return;
                }
                return;
        }
    }

    private void a(String str, String str2, int i, j jVar) throws Throwable {
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = str2;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = str2;
        a(wXMediaMessage, "text", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, int i, j jVar) throws Throwable {
        WXImageObject wXImageObject = new WXImageObject();
        if (str3.startsWith("/data/")) {
            wXImageObject.imageData = d(str3);
        } else {
            wXImageObject.imagePath = str3;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        if (i != 0) {
            wXMediaMessage.title = str;
            wXMediaMessage.description = str2;
        }
        wXMediaMessage.thumbData = a(context, str3, false);
        a(wXMediaMessage, "img", i, jVar);
    }

    private void a(Context context, String str, String str2, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXImageObject wXImageObject = new WXImageObject();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXImageObject.imageData = byteArrayOutputStream.toByteArray();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXImageObject;
        if (i != 0) {
            wXMediaMessage.title = str;
            wXMediaMessage.description = str2;
        }
        wXMediaMessage.thumbData = a(context, bitmap, false);
        a(wXMediaMessage, "img", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, String str4, String str5, int i, j jVar) throws Throwable {
        WXMusicObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = a(context, str5, false);
        a(wXMediaMessage, "music", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXMusicObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = a(context, bitmap, false);
        a(wXMediaMessage, "music", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, String str4, int i, j jVar) throws Throwable {
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = a(context, str4, false);
        a(wXMediaMessage, "video", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = a(context, bitmap, false);
        a(wXMediaMessage, "video", i, jVar);
    }

    private void b(Context context, String str, String str2, String str3, String str4, int i, j jVar) throws Throwable {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (str4 != null && new File(str4).exists()) {
            wXMediaMessage.thumbData = a(context, str4, false);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > 32768) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + 32768);
            }
        }
        a(wXMediaMessage, "webpage", i, jVar);
    }

    private void b(Context context, String str, String str2, String str3, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (bitmap != null && !bitmap.isRecycled()) {
            wXMediaMessage.thumbData = a(context, bitmap, false);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > 32768) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + 32768);
            }
        }
        a(wXMediaMessage, "webpage", i, jVar);
    }

    private void b(Context context, String str, String str2, String str3, String str4, String str5, int i, j jVar) throws Throwable {
        WXAppExtendObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = a(context, str5, false);
        a(wXMediaMessage, "appdata", i, jVar);
    }

    private void b(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXAppExtendObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = a(context, bitmap, false);
        a(wXMediaMessage, "appdata", i, jVar);
    }

    private void c(Context context, String str, String str2, String str3, String str4, int i, j jVar) throws Throwable {
        WXFileObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        wXMediaMessage.thumbData = a(context, str4, false);
        a(wXMediaMessage, "filedata", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, String str4, String str5, Bitmap bitmap, int i, j jVar) throws Throwable {
        String str6;
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.webpageUrl = str;
        if (TextUtils.isEmpty(str2) || !str2.endsWith("@app")) {
            wXMiniProgramObject.userName = str2 + "@app";
        } else {
            wXMiniProgramObject.userName = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            String[] split = str3.split("\\?");
            if (split.length > 1) {
                str6 = split[0] + ".html?" + split[1];
            } else {
                str6 = split[0] + ".html";
            }
            wXMiniProgramObject.path = str6;
            wXMiniProgramObject.withShareTicket = this.f;
            wXMiniProgramObject.miniprogramType = this.g;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str4;
        wXMediaMessage.mediaObject = wXMiniProgramObject;
        wXMediaMessage.description = str5;
        if (bitmap != null && !bitmap.isRecycled()) {
            wXMediaMessage.thumbData = a(context, bitmap, true);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > 131072) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + 131072);
            }
        }
        a(wXMediaMessage, "webpage", i, jVar);
    }

    private void a(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, j jVar) throws Throwable {
        String str7;
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.miniprogramType = this.g;
        wXMiniProgramObject.webpageUrl = str;
        if (TextUtils.isEmpty(str2) || !str2.endsWith("@app")) {
            wXMiniProgramObject.userName = str2 + "@app";
        } else {
            wXMiniProgramObject.userName = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            String[] split = str3.split("\\?");
            if (split.length > 1) {
                str7 = split[0] + ".html?" + split[1];
            } else {
                str7 = split[0] + ".html";
            }
            wXMiniProgramObject.path = str7;
            wXMiniProgramObject.withShareTicket = this.f;
            wXMiniProgramObject.miniprogramType = this.g;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str4;
        wXMediaMessage.mediaObject = wXMiniProgramObject;
        wXMediaMessage.description = str5;
        wXMediaMessage.thumbData = a(context, str6, true);
        a(wXMediaMessage, "webpage", i, jVar);
    }

    private void a(String str, String str2) throws Throwable {
        a aVar = new a();
        aVar.a = str;
        aVar.b = str2;
        aVar.c = this.g;
        this.b.a((l) aVar);
    }

    private void c(Context context, String str, String str2, String str3, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXFileObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        wXMediaMessage.thumbData = a(context, bitmap, false);
        a(wXMediaMessage, "filedata", i, jVar);
    }

    private void b(Context context, String str, String str2, String str3, int i, j jVar) throws Throwable {
        WXEmojiObject wXEmojiObject = new WXEmojiObject();
        wXEmojiObject.emojiPath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = a(context, str3, false);
        a(wXMediaMessage, "emoji", i, jVar);
    }

    private void b(Context context, String str, String str2, Bitmap bitmap, int i, j jVar) throws Throwable {
        WXEmojiObject wXEmojiObject = new WXEmojiObject();
        byte[] a2 = a(context, bitmap, false);
        wXEmojiObject.emojiData = a2;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = a2;
        a(wXMediaMessage, "emoji", i, jVar);
    }

    private byte[] d(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            fileInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            return null;
        }
    }

    private byte[] a(Context context, String str, boolean z) throws Throwable {
        if (!new File(str).exists()) {
            throw new FileNotFoundException();
        }
        return a(context, BitmapHelper.getBitmap(str), BitmapHelper.getBmpFormat(str), z);
    }

    private byte[] a(Context context, Bitmap bitmap, boolean z) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (!bitmap.isRecycled()) {
            return a(context, bitmap, CompressFormat.PNG, z);
        } else {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
    }

    private byte[] a(Context context, Bitmap bitmap, CompressFormat compressFormat, boolean z) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            int length = byteArray.length;
            int i = 32768;
            if (z) {
                i = 131072;
            }
            while (length > i) {
                bitmap = a(bitmap, ((double) length) / ((double) i));
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmap.compress(compressFormat, 100, byteArrayOutputStream2);
                byteArrayOutputStream2.flush();
                byteArrayOutputStream2.close();
                byteArray = byteArrayOutputStream2.toByteArray();
                length = byteArray.length;
            }
            return byteArray;
        }
    }

    private Bitmap a(Bitmap bitmap, double d2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double sqrt = Math.sqrt(d2);
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) width) / sqrt), (int) (((double) height) / sqrt), true);
    }

    public boolean c(String str) {
        return this.b.a(str);
    }

    public boolean a(WechatHandlerActivity wechatHandlerActivity) {
        return this.b.a(wechatHandlerActivity, this.c);
    }

    public boolean c() {
        return this.b.b();
    }

    public boolean d() {
        return this.b.c();
    }

    public final int e() {
        int i = 0;
        if (!new Wechat().isClientValid()) {
            return i;
        }
        try {
            return MobSDK.getContext().getPackageManager().getApplicationInfo(TbsConfig.APP_WX, 128).metaData.getInt("com.tencent.mm.BuildInfo.OPEN_SDK_VERSION", 0);
        } catch (Exception e2) {
            return i;
        }
    }

    private void a(WXMediaMessage wXMediaMessage, String str, int i, j jVar) throws Throwable {
        Class cls;
        String str2 = DeviceHelper.getInstance(MobSDK.getContext()).getPackageName() + ".wxapi.WXEntryActivity";
        try {
            cls = Class.forName(str2);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            cls = null;
        }
        if (cls != null && !WechatHandlerActivity.class.isAssignableFrom(cls)) {
            new Throwable(str2 + " does not extend from " + WechatHandlerActivity.class.getName()).printStackTrace();
        }
        d dVar = new d();
        dVar.d = str + System.currentTimeMillis();
        dVar.a = wXMediaMessage;
        dVar.b = i;
        this.c = jVar;
        boolean z = false;
        if (wXMediaMessage.mediaObject instanceof WXMiniProgramObject) {
            z = true;
        }
        this.b.a((l) dVar, z);
    }
}
