package com.jg.ids.f;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class b extends Binder implements a {
    public static a a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.deviceidservice.IDeviceIdService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new c(iBinder) : (a) queryLocalInterface;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("com.samsung.android.deviceidservice.IDeviceIdService");
                String a = a();
                parcel2.writeNoException();
                parcel2.writeString(a);
                return true;
            case 2:
                parcel.enforceInterface("com.samsung.android.deviceidservice.IDeviceIdService");
                String a2 = a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(a2);
                return true;
            case 3:
                parcel.enforceInterface("com.samsung.android.deviceidservice.IDeviceIdService");
                String b = b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            case 1598968902:
                parcel2.writeString("com.samsung.android.deviceidservice.IDeviceIdService");
                return true;
            default:
                try {
                    return super.onTransact(i, parcel, parcel2, i2);
                } catch (Throwable th) {
                    return true;
                }
        }
    }
}
