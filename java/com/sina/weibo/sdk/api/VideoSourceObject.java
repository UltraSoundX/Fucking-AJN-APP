package com.sina.weibo.sdk.api;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VideoSourceObject implements Parcelable {
    public static final Creator<VideoSourceObject> CREATOR = new Creator<VideoSourceObject>() {
        public VideoSourceObject createFromParcel(Parcel parcel) {
            return new VideoSourceObject(parcel);
        }

        public VideoSourceObject[] newArray(int i) {
            return new VideoSourceObject[i];
        }
    };
    public String actionUrl;
    public Uri coverPath;
    public String defaultText;
    public String description;
    public long during;
    public String identify;
    public String schema;
    public byte[] thumbData;
    public String title;
    public Uri videoPath;

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.actionUrl);
        parcel.writeString(this.schema);
        parcel.writeString(this.identify);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeByteArray(this.thumbData);
        parcel.writeParcelable(this.coverPath, i);
        parcel.writeParcelable(this.videoPath, i);
        parcel.writeLong(this.during);
    }

    protected VideoSourceObject(Parcel parcel) {
        this.actionUrl = parcel.readString();
        this.schema = parcel.readString();
        this.identify = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.thumbData = parcel.createByteArray();
        this.coverPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.videoPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.during = parcel.readLong();
    }

    public int getObjType() {
        return 0;
    }

    public VideoSourceObject() {
    }

    public int describeContents() {
        return 0;
    }
}
