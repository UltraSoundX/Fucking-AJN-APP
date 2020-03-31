package com.tencent.mid.sotrage;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Perference extends StorageInterface {
    public Perference(Context context, int i) {
        super(context, i);
    }

    public int getType() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public String read() {
        return read(getStorageKey());
    }

    public String read(String str) {
        String string;
        synchronized (this) {
            logger.i("read mid from sharedPreferences， key=" + str);
            string = PreferenceManager.getDefaultSharedPreferences(this.context).getString(str, null);
        }
        return string;
    }

    /* access modifiers changed from: protected */
    public void write(String str) {
        write(getStorageKey(), str);
    }

    public void write(String str, String str2) {
        synchronized (this) {
            logger.i("write mid to sharedPreferences");
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
            edit.putString(str, str2);
            edit.commit();
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkPermission() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void clear() {
        synchronized (this) {
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
            edit.putString(getStorageKey(), "");
            edit.putString(getCheckEntityTag(), "");
            edit.commit();
        }
    }

    /* access modifiers changed from: protected */
    public CheckEntity readCheckEntityIner() {
        CheckEntity checkEntity;
        synchronized (this) {
            checkEntity = new CheckEntity(PreferenceManager.getDefaultSharedPreferences(this.context).getString(getCheckEntityTag(), null));
            logger.i("read CheckEntity from sharedPreferences:" + checkEntity.toString());
        }
        return checkEntity;
    }

    /* access modifiers changed from: protected */
    public void writeCheckEntityIner(CheckEntity checkEntity) {
        synchronized (this) {
            logger.i("write CheckEntity to sharedPreferences:" + checkEntity.toString());
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
            edit.putString(getCheckEntityTag(), checkEntity.toString());
            edit.commit();
        }
    }
}
