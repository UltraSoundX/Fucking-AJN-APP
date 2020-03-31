package com.jg.ids.c;

import android.os.IBinder;
import android.os.Parcel;

final class c implements a {
    private IBinder a;

    c(IBinder iBinder) {
        this.a = iBinder;
    }

    public final String a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
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
            throw th;
            return "";
        } catch (Throwable th2) {
        }
    }

    public final String a(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
            obtain.writeString(str);
            this.a.transact(4, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            try {
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Throwable th) {
                return readString;
            }
            throw th;
            return "";
        } catch (Throwable th2) {
        }
    }

    public final IBinder asBinder() {
        return this.a;
    }

    public final String b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            try {
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Throwable th) {
                return readString;
            }
            throw th;
            return "";
        } catch (Throwable th2) {
        }
    }

    public final String b(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
            obtain.writeString(str);
            this.a.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            try {
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Throwable th) {
                return readString;
            }
            throw th;
            return "";
        } catch (Throwable th2) {
        }
    }

    public final boolean c() {
        boolean z = false;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
            this.a.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
        } catch (Throwable th) {
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
        return z;
    }
}
