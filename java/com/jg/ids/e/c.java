package com.jg.ids.e;

import android.os.IBinder;
import android.os.Parcel;

public final class c implements a {
    private IBinder a;

    public c(IBinder iBinder) {
        this.a = iBinder;
    }

    public final String a(String str, String str2, String str3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeString(str3);
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            try {
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Throwable th) {
                return readString;
            }
            return "";
            throw th;
        } catch (Throwable th2) {
        }
    }

    public final IBinder asBinder() {
        return this.a;
    }
}
