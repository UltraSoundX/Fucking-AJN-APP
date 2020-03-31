package cn.sharesdk.sina.weibo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MultiImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/* compiled from: SinaActivity */
public class a extends FakeActivity implements Callback {
    private long a = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
    private boolean b;
    private String c;
    private ShareParams d;
    private AuthorizeListener e;

    public void onCreate() {
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e2) {
            SSDKLog.b().d(e2);
        }
        UIHandler.sendEmptyMessageDelayed(1, 700, new Callback() {
            public boolean handleMessage(Message message) {
                a.this.a();
                return true;
            }
        });
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(ShareParams shareParams) {
        this.d = shareParams;
    }

    public void a(AuthorizeListener authorizeListener) {
        this.e = authorizeListener;
    }

    /* access modifiers changed from: private */
    public void a() {
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_command_type", 1);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        bundle.putLong("callbackId", 0);
        if (!TextUtils.isEmpty(this.d.getText())) {
            bundle.putParcelable("_weibo_message_text", c());
            bundle.putString("_weibo_message_text_extra", "");
        }
        if (!TextUtils.isEmpty(this.d.getUrl())) {
            this.a = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            WebpageObject d2 = d();
            if (d2.checkArgs()) {
                bundle.putParcelable("_weibo_message_media", d2);
                String str = "";
                if (!TextUtils.isEmpty(d2.defaultText)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("extra_key_defaulttext", d2.defaultText);
                    str = new Hashon().fromHashMap(hashMap);
                }
                bundle.putString("_weibo_message_media_extra", str);
            }
        } else if (this.d.getImageArray() != null && this.d.getImageArray().length > 0) {
            bundle.putParcelable("_weibo_message_multi_image", f());
        } else if (!TextUtils.isEmpty(this.d.getFilePath())) {
            bundle.putParcelable("_weibo_message_video_source", g());
        } else if (this.d.getImageData() != null || !TextUtils.isEmpty(this.d.getImagePath())) {
            this.a = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            ImageObject e2 = e();
            if (e2.checkArgs()) {
                bundle.putParcelable("_weibo_message_image", e2);
                bundle.putString("_weibo_message_image_extra", "");
            }
        }
        try {
            a(this.activity, i.a().b(), this.c, bundle);
        } catch (Throwable th) {
            if (this.e != null) {
                this.e.onError(new Throwable(th));
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        SSDKLog.b().d("sina activity requestCode = %s, resultCode = %s", Integer.valueOf(i), Integer.valueOf(i));
        finish();
    }

    public void onNewIntent(Intent intent) {
        this.b = true;
        Bundle extras = intent.getExtras();
        SSDKLog.b().i("onNewIntent ==>>", extras.toString());
        String stringExtra = intent.getStringExtra("_weibo_appPackage");
        String stringExtra2 = intent.getStringExtra("_weibo_transaction");
        if (TextUtils.isEmpty(stringExtra)) {
            SSDKLog.b().e("handleWeiboResponse faild appPackage is null", new Object[0]);
            return;
        }
        SSDKLog.b().d("handleWeiboResponse getCallingPackage : " + this.activity.getCallingPackage(), new Object[0]);
        if (TextUtils.isEmpty(stringExtra2)) {
            SSDKLog.b().e("handleWeiboResponse faild intent _weibo_transaction is null", new Object[0]);
        } else if (i.a(stringExtra) || stringExtra.equals(this.activity.getPackageName())) {
            a(extras.getInt("_weibo_resp_errcode"), extras.getString("_weibo_resp_errstr"));
        } else {
            SSDKLog.b().e("handleWeiboResponse faild appPackage validateSign faild", new Object[0]);
        }
    }

    private void a(int i, String str) {
        switch (i) {
            case 0:
                if (this.e != null) {
                    this.e.onComplete(null);
                    break;
                }
                break;
            case 1:
                if (this.e != null) {
                    this.e.onCancel();
                    break;
                }
                break;
            case 2:
                if (this.e != null) {
                    this.e.onError(new Throwable(str));
                    break;
                }
                break;
        }
        finish();
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            if (!this.b && this.e != null) {
                this.e.onCancel();
            }
            finish();
        }
        return false;
    }

    public void onStop() {
        super.onStop();
    }

    private boolean a(Activity activity, String str, String str2, Bundle bundle) {
        String str3 = "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY";
        if (activity == null || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            SSDKLog.b().e("launchWeiboActivity fail, invalid arguments", new Object[0]);
            return false;
        }
        String packageName = activity.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction(str3);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("_weibo_sdkVersion", "0031405000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str2);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", cn.sharesdk.sina.weibo.sdk.a.a((Context) activity, packageName));
        intent.putExtra("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            SSDKLog.b().d("launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
            startActivityForResult(intent, 765);
            return true;
        } catch (ActivityNotFoundException e2) {
            SSDKLog.b().e(e2.getMessage(), new Object[0]);
            return false;
        }
    }

    private String b() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private TextObject c() {
        TextObject textObject = new TextObject();
        textObject.text = this.d.getText();
        return textObject;
    }

    private WebpageObject d() {
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.identify = b();
        webpageObject.title = this.d.getTitle();
        webpageObject.description = this.d.getText();
        webpageObject.actionUrl = this.d.getUrl();
        webpageObject.defaultText = this.d.getText();
        try {
            if (this.d.getImageData() != null) {
                webpageObject.thumbData = a((Context) this.activity, this.d.getImageData());
            } else if (!TextUtils.isEmpty(this.d.getImagePath())) {
                webpageObject.thumbData = a((Context) this.activity, this.d.getImagePath());
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            webpageObject.setThumbImage(null);
        }
        return webpageObject;
    }

    private ImageObject e() {
        ImageObject imageObject = new ImageObject();
        try {
            if (this.d.getImageData() != null) {
                imageObject.imageData = a((Context) this.activity, this.d.getImageData());
            } else if (!TextUtils.isEmpty(this.d.getImagePath())) {
                DeviceHelper instance = DeviceHelper.getInstance(this.activity);
                if (instance.getSdcardState() && this.d.getImagePath().startsWith(instance.getSdcardPath())) {
                    File file = new File(this.d.getImagePath());
                    if (file.exists() && file.length() != 0 && file.length() < Config.FULL_TRACE_LOG_LIMIT) {
                        imageObject.imagePath = this.d.getImagePath();
                    }
                }
                imageObject.imageData = a((Context) this.activity, this.d.getImagePath());
            }
        } catch (Throwable th) {
            SSDKLog.b().d(th);
        }
        return imageObject;
    }

    private MultiImageObject f() {
        MultiImageObject multiImageObject = new MultiImageObject();
        multiImageObject.identify = b();
        multiImageObject.title = this.d.getTitle();
        multiImageObject.description = this.d.getText();
        multiImageObject.actionUrl = this.d.getUrl();
        multiImageObject.defaultText = this.d.getText();
        try {
            if (this.d.getImageData() != null) {
                multiImageObject.thumbData = a((Context) this.activity, this.d.getImageData());
            } else if (!TextUtils.isEmpty(this.d.getImagePath())) {
                multiImageObject.thumbData = a((Context) this.activity, this.d.getImagePath());
            }
            List<String> asList = Arrays.asList(this.d.getImageArray());
            ArrayList arrayList = new ArrayList();
            for (String file : asList) {
                File file2 = new File(file);
                if (file2.exists()) {
                    if (VERSION.SDK_INT >= 24) {
                        arrayList.add(ResHelper.pathToContentUri(MobSDK.getContext(), file2.getAbsolutePath()));
                    } else {
                        arrayList.add(Uri.fromFile(file2));
                    }
                }
            }
            multiImageObject.setImageList(arrayList);
        } catch (Throwable th) {
            SSDKLog.b().d(th);
            multiImageObject.setThumbImage(null);
        }
        return multiImageObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x008e, code lost:
        if (com.mob.tools.utils.ResHelper.copyFile(r3, r4) != false) goto L_0x0090;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.sina.weibo.sdk.api.VideoSourceObject g() {
        /*
            r8 = this;
            com.sina.weibo.sdk.api.VideoSourceObject r2 = new com.sina.weibo.sdk.api.VideoSourceObject
            r2.<init>()
            java.lang.String r0 = r8.b()
            r2.identify = r0
            cn.sharesdk.framework.Platform$ShareParams r0 = r8.d
            java.lang.String r0 = r0.getTitle()
            r2.title = r0
            cn.sharesdk.framework.Platform$ShareParams r0 = r8.d
            java.lang.String r0 = r0.getText()
            r2.description = r0
            cn.sharesdk.framework.Platform$ShareParams r0 = r8.d
            java.lang.String r0 = r0.getUrl()
            r2.actionUrl = r0
            cn.sharesdk.framework.Platform$ShareParams r0 = r8.d
            java.lang.String r0 = r0.getText()
            r2.defaultText = r0
            cn.sharesdk.framework.Platform$ShareParams r0 = r8.d     // Catch:{ Throwable -> 0x00b2 }
            android.graphics.Bitmap r0 = r0.getImageData()     // Catch:{ Throwable -> 0x00b2 }
            if (r0 == 0) goto L_0x0097
            android.app.Activity r0 = r8.activity     // Catch:{ Throwable -> 0x00b2 }
            cn.sharesdk.framework.Platform$ShareParams r1 = r8.d     // Catch:{ Throwable -> 0x00b2 }
            android.graphics.Bitmap r1 = r1.getImageData()     // Catch:{ Throwable -> 0x00b2 }
            byte[] r0 = r8.a(r0, r1)     // Catch:{ Throwable -> 0x00b2 }
            r2.thumbData = r0     // Catch:{ Throwable -> 0x00b2 }
        L_0x0041:
            r0 = 0
            cn.sharesdk.framework.Platform$ShareParams r1 = r8.d     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r3 = r1.getFilePath()     // Catch:{ Throwable -> 0x00b2 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x00b2 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x00b2 }
            boolean r4 = r1.exists()     // Catch:{ Throwable -> 0x00b2 }
            if (r4 == 0) goto L_0x0094
            java.lang.String r0 = "/data/"
            boolean r0 = r3.startsWith(r0)     // Catch:{ Throwable -> 0x00b2 }
            if (r0 == 0) goto L_0x00bb
            android.content.Context r0 = com.mob.MobSDK.getContext()     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r4 = "videos"
            java.lang.String r4 = com.mob.tools.utils.ResHelper.getCachePath(r0, r4)     // Catch:{ Throwable -> 0x00b2 }
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00b2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00b2 }
            r5.<init>()     // Catch:{ Throwable -> 0x00b2 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00b2 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r6 = r1.getName()     // Catch:{ Throwable -> 0x00b2 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x00b2 }
            r0.<init>(r4, r5)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r4 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x00b2 }
            r0.createNewFile()     // Catch:{ Throwable -> 0x00b2 }
            boolean r3 = com.mob.tools.utils.ResHelper.copyFile(r3, r4)     // Catch:{ Throwable -> 0x00b2 }
            if (r3 == 0) goto L_0x00bb
        L_0x0090:
            android.net.Uri r0 = android.net.Uri.fromFile(r0)     // Catch:{ Throwable -> 0x00b2 }
        L_0x0094:
            r2.videoPath = r0     // Catch:{ Throwable -> 0x00b2 }
        L_0x0096:
            return r2
        L_0x0097:
            cn.sharesdk.framework.Platform$ShareParams r0 = r8.d     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r0 = r0.getImagePath()     // Catch:{ Throwable -> 0x00b2 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x00b2 }
            if (r0 != 0) goto L_0x0041
            android.app.Activity r0 = r8.activity     // Catch:{ Throwable -> 0x00b2 }
            cn.sharesdk.framework.Platform$ShareParams r1 = r8.d     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r1 = r1.getImagePath()     // Catch:{ Throwable -> 0x00b2 }
            byte[] r0 = r8.a(r0, r1)     // Catch:{ Throwable -> 0x00b2 }
            r2.thumbData = r0     // Catch:{ Throwable -> 0x00b2 }
            goto L_0x0041
        L_0x00b2:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
            r1.d(r0)
            goto L_0x0096
        L_0x00bb:
            r0 = r1
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.a.g():com.sina.weibo.sdk.api.VideoSourceObject");
    }

    private byte[] a(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (!bitmap.isRecycled()) {
            return b(context, bitmap);
        } else {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
    }

    private byte[] a(Context context, String str) throws Throwable {
        if (new File(str).exists()) {
            return b(context, BitmapHelper.getBitmap(str));
        }
        throw new FileNotFoundException();
    }

    private byte[] b(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            int length = byteArray.length;
            while (((long) length) > this.a) {
                bitmap = a(bitmap, ((double) length) / ((double) this.a));
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream2);
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
}
