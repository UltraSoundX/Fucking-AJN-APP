package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mobstat.Config;
import java.io.File;

public class ImageObject implements Parcelable {
    public static final Creator<ImageObject> CREATOR = new Creator<ImageObject>() {
        public ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    };
    private static final int DATA_SIZE = 2097152;
    public byte[] imageData;
    public String imagePath;

    public ImageObject() {
    }

    public ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002d A[SYNTHETIC, Splitter:B:15:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003e A[SYNTHETIC, Splitter:B:22:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setImageObject(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r2 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0022, all -> 0x003a }
            r1.<init>()     // Catch:{ Throwable -> 0x0022, all -> 0x003a }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Throwable -> 0x004d }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Throwable -> 0x004d }
            byte[] r0 = r1.toByteArray()     // Catch:{ Throwable -> 0x004d }
            r3.imageData = r0     // Catch:{ Throwable -> 0x004d }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ Throwable -> 0x0019 }
        L_0x0018:
            return
        L_0x0019:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
            r1.d(r0)
            goto L_0x0018
        L_0x0022:
            r0 = move-exception
            r1 = r2
        L_0x0024:
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()     // Catch:{ all -> 0x004b }
            r2.d(r0)     // Catch:{ all -> 0x004b }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0018
        L_0x0031:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = cn.sharesdk.framework.utils.SSDKLog.b()
            r1.d(r0)
            goto L_0x0018
        L_0x003a:
            r0 = move-exception
            r1 = r2
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()     // Catch:{ Throwable -> 0x0042 }
        L_0x0041:
            throw r0
        L_0x0042:
            r1 = move-exception
            com.mob.tools.log.NLog r2 = cn.sharesdk.framework.utils.SSDKLog.b()
            r2.d(r1)
            goto L_0x0041
        L_0x004b:
            r0 = move-exception
            goto L_0x003c
        L_0x004d:
            r0 = move-exception
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.ImageObject.setImageObject(android.graphics.Bitmap):void");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }

    public boolean checkArgs() {
        if (this.imageData == null && this.imagePath == null) {
            new Throwable("imageData and imagePath are null").printStackTrace();
            return false;
        } else if (this.imageData != null && this.imageData.length > DATA_SIZE) {
            new Throwable("imageData is too large").printStackTrace();
            return false;
        } else if (this.imagePath == null || this.imagePath.length() <= 512) {
            if (this.imagePath != null) {
                try {
                    File file = new File(this.imagePath);
                    if (!file.exists() || file.length() == 0 || file.length() > Config.FULL_TRACE_LOG_LIMIT) {
                        new Throwable("checkArgs fail, image content is too large or not exists").printStackTrace();
                        return false;
                    }
                } catch (Throwable th) {
                    new Throwable("checkArgs fail, image content is too large or not exists").printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            new Throwable("imagePath is too length").printStackTrace();
            return false;
        }
    }

    public int getObjType() {
        return 2;
    }
}
