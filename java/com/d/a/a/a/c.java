package com.d.a.a.a;

import android.content.Context;
import com.d.a.a.i;
import com.d.a.a.j;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* compiled from: PlatformPageAdapterLand */
public class c extends j {
    public c(i iVar, ArrayList<Object> arrayList) {
        super(iVar, arrayList);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, ArrayList<Object> arrayList) {
        int i = 1;
        int screenWidth = ResHelper.getScreenWidth(context);
        float f = ((float) screenWidth) / 1280.0f;
        this.e = screenWidth / ((int) (160.0f * f));
        this.f = (int) (1.0f * f);
        if (this.f >= 1) {
            i = this.f;
        }
        this.f = i;
        this.h = (int) (76.0f * f);
        this.g = (int) (20.0f * f);
        this.b = (int) (52.0f * f);
        this.d = (screenWidth - (this.f * 3)) / (this.e - 1);
        this.c = this.d + this.f;
    }

    /* access modifiers changed from: protected */
    public void a(ArrayList<Object> arrayList) {
        int size = arrayList.size();
        if (size < this.e) {
            int i = size / this.e;
            if (size % this.e != 0) {
                i++;
            }
            this.a = (Object[][]) Array.newInstance(Object.class, new int[]{1, i * this.e});
        } else {
            int i2 = size / this.e;
            if (size % this.e != 0) {
                i2++;
            }
            this.a = (Object[][]) Array.newInstance(Object.class, new int[]{i2, this.e});
        }
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = i3 / this.e;
            this.a[i4][i3 - (i4 * this.e)] = arrayList.get(i3);
        }
    }
}
