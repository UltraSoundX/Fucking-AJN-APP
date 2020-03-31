package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* compiled from: VersionedParcelParcel */
class b extends a {
    private final SparseIntArray a;
    private final Parcel b;
    private final int c;
    private final int d;
    private final String e;
    private int f;
    private int g;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    b(Parcel parcel, int i, int i2, String str) {
        this.a = new SparseIntArray();
        this.f = -1;
        this.g = 0;
        this.b = parcel;
        this.c = i;
        this.d = i2;
        this.g = this.c;
        this.e = str;
    }

    private int d(int i) {
        while (this.g < this.d) {
            this.b.setDataPosition(this.g);
            int readInt = this.b.readInt();
            int readInt2 = this.b.readInt();
            this.g = readInt + this.g;
            if (readInt2 == i) {
                return this.b.dataPosition();
            }
        }
        return -1;
    }

    public boolean b(int i) {
        int d2 = d(i);
        if (d2 == -1) {
            return false;
        }
        this.b.setDataPosition(d2);
        return true;
    }

    public void c(int i) {
        b();
        this.f = i;
        this.a.put(i, this.b.dataPosition());
        a(0);
        a(i);
    }

    public void b() {
        if (this.f >= 0) {
            int i = this.a.get(this.f);
            int dataPosition = this.b.dataPosition();
            int i2 = dataPosition - i;
            this.b.setDataPosition(i);
            this.b.writeInt(i2);
            this.b.setDataPosition(dataPosition);
        }
    }

    /* access modifiers changed from: protected */
    public a c() {
        return new b(this.b, this.b.dataPosition(), this.g == this.c ? this.d : this.g, this.e + "  ");
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.b.writeInt(bArr.length);
            this.b.writeByteArray(bArr);
            return;
        }
        this.b.writeInt(-1);
    }

    public void a(int i) {
        this.b.writeInt(i);
    }

    public void a(String str) {
        this.b.writeString(str);
    }

    public void a(Parcelable parcelable) {
        this.b.writeParcelable(parcelable, 0);
    }

    public int d() {
        return this.b.readInt();
    }

    public String e() {
        return this.b.readString();
    }

    public byte[] f() {
        int readInt = this.b.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.b.readByteArray(bArr);
        return bArr;
    }

    public <T extends Parcelable> T g() {
        return this.b.readParcelable(getClass().getClassLoader());
    }
}
