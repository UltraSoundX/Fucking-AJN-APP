package com.sina.weibo.sdk.api;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class MultiImageObject implements Parcelable {
    public static final Creator<MultiImageObject> CREATOR = new Creator<MultiImageObject>() {
        public MultiImageObject createFromParcel(Parcel parcel) {
            return new MultiImageObject(parcel);
        }

        public MultiImageObject[] newArray(int i) {
            return new MultiImageObject[i];
        }
    };
    public String actionUrl;
    public String defaultText;
    public String description;
    public String identify;
    public ArrayList<Uri> imageList;
    public String schema;
    public byte[] thumbData;
    public String title;

    public void setImageList(ArrayList<Uri> arrayList) {
        this.imageList = arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002d A[SYNTHETIC, Splitter:B:15:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003e A[SYNTHETIC, Splitter:B:22:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setThumbImage(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r2 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0022, all -> 0x003a }
            r1.<init>()     // Catch:{ Throwable -> 0x0022, all -> 0x003a }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Throwable -> 0x004d }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Throwable -> 0x004d }
            byte[] r0 = r1.toByteArray()     // Catch:{ Throwable -> 0x004d }
            r3.thumbData = r0     // Catch:{ Throwable -> 0x004d }
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
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.MultiImageObject.setThumbImage(android.graphics.Bitmap):void");
    }

    public ArrayList<Uri> getImageList() {
        return this.imageList;
    }

    public int getObjType() {
        return 0;
    }

    public MultiImageObject() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.actionUrl);
        parcel.writeString(this.schema);
        parcel.writeString(this.identify);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeByteArray(this.thumbData);
        parcel.writeTypedList(this.imageList);
    }

    protected MultiImageObject(Parcel parcel) {
        this.actionUrl = parcel.readString();
        this.schema = parcel.readString();
        this.identify = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.thumbData = parcel.createByteArray();
        this.imageList = parcel.createTypedArrayList(Uri.CREATOR);
    }
}
