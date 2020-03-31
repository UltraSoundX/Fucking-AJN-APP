package com.jg.ids.c;

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
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new c(iBinder) : (a) queryLocalInterface;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        String str = "com.zui.deviceidservice.IDeviceidInterface";
        switch (i) {
            case 1:
                parcel.enforceInterface(str);
                String a = a();
                parcel2.writeNoException();
                parcel2.writeString(a);
                return true;
            case 2:
                parcel.enforceInterface(str);
                String b = b();
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            case 3:
                parcel.enforceInterface(str);
                boolean c = c();
                parcel2.writeNoException();
                parcel2.writeInt(c ? 1 : 0);
                return true;
            case 4:
                parcel.enforceInterface(str);
                String a2 = a(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(a2);
                return true;
            case 5:
                parcel.enforceInterface(str);
                String b2 = b(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(b2);
                return true;
            case 1598968902:
                parcel2.writeString(str);
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
