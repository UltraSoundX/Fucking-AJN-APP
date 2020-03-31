package com.jg.ids.b;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class c implements IInterface {
    private IBinder a;

    public c(IBinder iBinder) {
        this.a = iBinder;
    }

    public String a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
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
        } catch (Throwable th2) {
        }
        return "";
    }

    public boolean b() {
        boolean z = false;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            try {
                obtain2.recycle();
                obtain.recycle();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
        }
        return z;
    }

    public IBinder asBinder() {
        return this.a;
    }
}
