package com.d.a.a.b;

import android.content.Context;
import com.d.a.a.i;
import com.d.a.a.j;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* compiled from: PlatformPageAdapterPort */
public class c extends j {
    public c(i iVar, ArrayList<Object> arrayList) {
        super(iVar, arrayList);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, ArrayList<Object> arrayList) {
        int i = 1;
        int screenWidth = ResHelper.getScreenWidth(context);
        this.e = 4;
        float f = ((float) screenWidth) / 720.0f;
        this.f = (int) (1.0f * f);
        if (this.f >= 1) {
            i = this.f;
        }
        this.f = i;
        this.h = (int) (76.0f * f);
        this.g = (int) (20.0f * f);
        this.b = (int) (52.0f * f);
        this.d = (screenWidth - (this.f * 3)) / 4;
        if (arrayList.size() <= this.e) {
            this.c = this.d + this.f;
        } else if (arrayList.size() <= 12 - this.e) {
            this.c = (this.d + this.f) * 2;
        } else {
            this.c = (this.d + this.f) * 3;
        }
    }

    /* access modifiers changed from: protected */
    public void a(ArrayList<Object> arrayList) {
        int size = arrayList.size();
        if (size < 12) {
            int i = size / this.e;
            if (size % this.e != 0) {
                i++;
            }
            this.a = (Object[][]) Array.newInstance(Object.class, new int[]{1, i * this.e});
        } else {
            int i2 = size / 12;
            if (size % 12 != 0) {
                i2++;
            }
            this.a = (Object[][]) Array.newInstance(Object.class, new int[]{i2, 12});
        }
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = i3 / 12;
            this.a[i4][i3 - (i4 * 12)] = arrayList.get(i3);
        }
    }
}
