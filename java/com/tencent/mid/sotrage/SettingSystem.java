package com.tencent.mid.sotrage;

import android.content.Context;
import com.tencent.mid.core.Constants;
import com.tencent.mid.util.Logger;
import com.tencent.mid.util.SettingsHelper;
import com.tencent.mid.util.Util;

public class SettingSystem extends StorageInterface {
    protected static Logger logger = Util.getLogger();

    public SettingSystem(Context context, int i) {
        super(context, i);
    }

    public int getType() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean checkPermission() {
        return Util.checkPermission(this.context, Constants.PERMISSION_WRITE_SETTINGS);
    }

    /* access modifiers changed from: protected */
    public String read() {
        String string;
        synchronized (this) {
            logger.i("read mid from Settings.System");
            string = SettingsHelper.getInstance(this.context).getString(getStorageKey());
        }
        return string;
    }

    /* access modifiers changed from: protected */
    public void write(String str) {
        synchronized (this) {
            logger.i("write mid to Settings.System");
            SettingsHelper.getInstance(this.context).putString(getStorageKey(), str);
        }
    }

    /* access modifiers changed from: protected */
    public void clear() {
        synchronized (this) {
            SettingsHelper.getInstance(this.context).putString(getStorageKey(), "");
            SettingsHelper.getInstance(this.context).putString(getCheckEntityTag(), "");
        }
    }

    /* access modifiers changed from: protected */
    public CheckEntity readCheckEntityIner() {
        CheckEntity checkEntity;
        synchronized (this) {
            checkEntity = new CheckEntity(SettingsHelper.getInstance(this.context).getString(getCheckEntityTag()));
            logger.i("read readCheckEntity from Settings.System:" + checkEntity.toString());
        }
        return checkEntity;
    }

    /* access modifiers changed from: protected */
    public void writeCheckEntityIner(CheckEntity checkEntity) {
        synchronized (this) {
            logger.i("write CheckEntity to Settings.System:" + checkEntity.toString());
            SettingsHelper.getInstance(this.context).putString(getCheckEntityTag(), checkEntity.toString());
        }
    }
}
