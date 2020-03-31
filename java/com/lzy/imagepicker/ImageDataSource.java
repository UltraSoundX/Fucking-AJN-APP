package com.lzy.imagepicker;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import com.baidu.mobstat.Config;
import com.lzy.imagepicker.bean.ImageItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageDataSource implements LoaderCallbacks<Cursor> {
    private final String[] a = {"_display_name", "_data", "_size", "width", "height", "mime_type", "date_added"};
    private FragmentActivity b;
    private a c;
    private ArrayList<com.lzy.imagepicker.bean.a> d = new ArrayList<>();

    public interface a {
        void onImagesLoaded(List<com.lzy.imagepicker.bean.a> list);
    }

    public ImageDataSource(FragmentActivity fragmentActivity, String str, a aVar) {
        this.b = fragmentActivity;
        this.c = aVar;
        LoaderManager supportLoaderManager = fragmentActivity.getSupportLoaderManager();
        if (str == null) {
            supportLoaderManager.initLoader(0, null, this);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(Config.FEED_LIST_ITEM_PATH, str);
        supportLoaderManager.initLoader(1, bundle, this);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Loader<Cursor> loader;
        if (i == 0) {
            loader = new CursorLoader<>(this.b, Media.EXTERNAL_CONTENT_URI, this.a, null, null, this.a[6] + " DESC");
        } else {
            loader = null;
        }
        if (i == 1) {
            return new CursorLoader(this.b, Media.EXTERNAL_CONTENT_URI, this.a, this.a[1] + " like '%" + bundle.getString(Config.FEED_LIST_ITEM_PATH) + "%'", null, this.a[6] + " DESC");
        }
        return loader;
    }

    /* renamed from: a */
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.d.clear();
        if (cursor != null) {
            ArrayList<ImageItem> arrayList = new ArrayList<>();
            while (cursor.moveToNext()) {
                String string = cursor.getString(cursor.getColumnIndexOrThrow(this.a[0]));
                String string2 = cursor.getString(cursor.getColumnIndexOrThrow(this.a[1]));
                File file = new File(string2);
                if (file.exists() && file.length() > 0) {
                    long j = cursor.getLong(cursor.getColumnIndexOrThrow(this.a[2]));
                    int i = cursor.getInt(cursor.getColumnIndexOrThrow(this.a[3]));
                    int i2 = cursor.getInt(cursor.getColumnIndexOrThrow(this.a[4]));
                    String string3 = cursor.getString(cursor.getColumnIndexOrThrow(this.a[5]));
                    long j2 = cursor.getLong(cursor.getColumnIndexOrThrow(this.a[6]));
                    ImageItem imageItem = new ImageItem();
                    imageItem.a = string;
                    imageItem.b = string2;
                    imageItem.c = j;
                    imageItem.d = i;
                    imageItem.e = i2;
                    imageItem.f = string3;
                    imageItem.g = j2;
                    arrayList.add(imageItem);
                    File parentFile = new File(string2).getParentFile();
                    com.lzy.imagepicker.bean.a aVar = new com.lzy.imagepicker.bean.a();
                    aVar.a = parentFile.getName();
                    aVar.b = parentFile.getAbsolutePath();
                    if (!this.d.contains(aVar)) {
                        ArrayList<ImageItem> arrayList2 = new ArrayList<>();
                        arrayList2.add(imageItem);
                        aVar.c = imageItem;
                        aVar.d = arrayList2;
                        this.d.add(aVar);
                    } else {
                        ((com.lzy.imagepicker.bean.a) this.d.get(this.d.indexOf(aVar))).d.add(imageItem);
                    }
                }
            }
            if (cursor.getCount() > 0) {
                com.lzy.imagepicker.bean.a aVar2 = new com.lzy.imagepicker.bean.a();
                aVar2.a = this.b.getResources().getString(R.string.ip_all_images);
                aVar2.b = "/";
                aVar2.c = (ImageItem) arrayList.get(0);
                aVar2.d = arrayList;
                this.d.add(0, aVar2);
            }
        }
        b.a().a((List<com.lzy.imagepicker.bean.a>) this.d);
        this.c.onImagesLoaded(this.d);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        System.out.println("--------");
    }
}
