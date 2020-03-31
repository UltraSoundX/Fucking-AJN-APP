package com.jg.ids.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class c extends Binder implements b {
    public static b a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        try {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new d(iBinder) : (b) queryLocalInterface;
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        boolean z;
        boolean z2 = true;
        String str = "com.asus.msa.SupplementaryDID.IDidAidlInterface";
        if (i != 1598968902) {
            switch (i) {
                case 1:
                    try {
                        parcel.enforceInterface(str);
                        boolean a = a();
                        parcel2.writeNoException();
                        if (a) {
                            z = z2;
                        } else {
                            z = false;
                        }
                        parcel2.writeInt(z ? 1 : 0);
                        return z2;
                    } catch (Throwable th) {
                        return z2;
                    }
                case 2:
                    parcel.enforceInterface(str);
                    String b = b();
                    parcel2.writeNoException();
                    parcel2.writeString(b);
                    return z2;
                case 3:
                    parcel.enforceInterface(str);
                    String c = c();
                    parcel2.writeNoException();
                    parcel2.writeString(c);
                    return z2;
                case 4:
                    parcel.enforceInterface(str);
                    String d = d();
                    parcel2.writeNoException();
                    parcel2.writeString(d);
                    return z2;
                case 5:
                    parcel.enforceInterface(str);
                    String e = e();
                    parcel2.writeNoException();
                    parcel2.writeString(e);
                    return z2;
                default:
                    try {
                        return super.onTransact(i, parcel, parcel2, i2);
                    } catch (RemoteException e2) {
                        return z2;
                    }
            }
        } else {
            parcel2.writeString(str);
            return z2;
        }
    }
}
