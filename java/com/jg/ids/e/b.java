package com.jg.ids.e;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public abstract class b extends Binder implements a {
    public static a a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new c(iBinder);
            }
            return (a) queryLocalInterface;
        } catch (Throwable th) {
            return null;
        }
    }
}
